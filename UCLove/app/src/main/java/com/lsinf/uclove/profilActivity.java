package com.lsinf.uclove;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by damien on 28/04/16.
 */
public class profilActivity extends baseActivity {

    User user = new User();
    int idFiltre;
    int id;
    int display = 0;
    int state = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        int temp = getIntent().getIntExtra("refresh",-1);
        display = getIntent().getIntExtra("dontdisplay",0);
        idFiltre = getIntent().getIntExtra("idFiltre",-1);
        if(temp != -1)
        {
            id = temp;
            new DownloadUser().execute(temp+"");

        }
        else if(idFiltre != -1)
        {
            new SearchFriend().execute(mainUser.getFiltres().get(idFiltre).getData());
        }
    }


    public void click(View v)
    {
        String t = v.getTag().toString();
        if(t.equals("favorite"))new setRelation().execute(""+Relation.FAVORITE);
        if(t.equals("unfavorite"))new setRelation().execute(""+Relation.FRIEND);
        if(t.equals("friend"))new setRelation().execute(""+Relation.FRIEND);
        if(t.equals("ask"))new setRelation().execute(""+Relation.DEMAND);
        if(t.equals("block"))new setRelation().execute(""+Relation.BLOQUED);
        if(t.equals("unblock"))new setRelation().execute(""+Relation.FRIEND);
    }
    public void disp()
    {
        setContentView(R.layout.profile_activity);
        createNavigationMenu();
        if(display != 0)findViewById(R.id.tuto).setVisibility(View.GONE);
        else
        {
            findViewById(R.id.wraperTel).setVisibility(View.GONE);
            findViewById(R.id.wraperDispo).setVisibility(View.GONE);
            findViewById(R.id.wraperMail).setVisibility(View.GONE);
            findViewById(R.id.wraperHobby).setVisibility(View.GONE);
            findViewById(R.id.llprofile).setOnTouchListener(new OnSwipeTouchListener(profilActivity.this) {


                public void onSwipeRight() {
                    Intent i = getIntent();
                    i.putExtra("refresh",-1);
                    startActivity(i);
                    finish();

                }

                public void onSwipeLeft() {

                    Intent i = getIntent();
                    i.putExtra("refresh",-1);
                    startActivity(i);
                    finish();
                }
            });
        }
        new loadImageWeb((ImageView) findViewById(R.id.profile_picture)).execute(mainUser.getPhoto());
        ((TextView)findViewById(R.id.prenom)).setText(user.getPrenom()+" "+user.getNom());
        ((TextView)findViewById(R.id.sexe)).setText(user.getSexe(this));
        ((TextView)findViewById(R.id.attirance)).setText(user.getAttirance(this));
        ((TextView)findViewById(R.id.naissance)).setText(user.getNaissance());
        ((TextView)findViewById(R.id.mail)).setText(user.getMail());
        ((TextView)findViewById(R.id.tel)).setText(user.getTel());
        ((TextView)findViewById(R.id.ville)).setText(user.getVille());
        ((TextView)findViewById(R.id.yeux)).setText(user.getYeux(this));
        ((TextView)findViewById(R.id.cheveux)).setText(user.getCheveux(this));
        ((TextView)findViewById(R.id.hobby)).setText(user.getHobby());
        ((TextView)findViewById(R.id.description)).setText(user.getDescription());
        ((TextView)findViewById(R.id.langue)).setText(user.getLangue());


    }

    public void display()
    {
        disp();
        Resources r = getResources();
        state = relation.getRelationByAskerId(id);
        Log.e("dodormeur",state+" relation "+id);
        ((TextView)findViewById(R.id.text_relation)).setText(r.getString(R.string.profile_actual_relation) + " " + r.getStringArray(R.array.profile_relation)[relation.getRelationByReceiverId(id)]);

        if(state == Relation.NOTHING)
        {
            findViewById(R.id.block).setVisibility(View.VISIBLE);
            findViewById(R.id.ask).setVisibility(View.VISIBLE);
        }
        if(state == Relation.DEMAND)
        {
            findViewById(R.id.block).setVisibility(View.VISIBLE);
            findViewById(R.id.friend).setVisibility(View.VISIBLE);
        }
        else if(state == Relation.BLOQUED)findViewById(R.id.unblock).setVisibility(View.VISIBLE);
        else if(state == Relation.FRIEND)
        {
            findViewById(R.id.block).setVisibility(View.VISIBLE);
            findViewById(R.id.favorite).setVisibility(View.VISIBLE);
        }
        else if(state == Relation.FAVORITE)
        {
            findViewById(R.id.block).setVisibility(View.VISIBLE);
            findViewById(R.id.unfavorite).setVisibility(View.VISIBLE);
        }
    }


    private class setRelation extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls)
        {
            //baseActivity.relation = new Relation();
            if(urls[0].equals(""+Relation.DEMAND))DatabaseHelper.setRelation(""+id,DatabaseHelper.idMain+"",""+Relation.ISDEMANDED,profilActivity.this);
            return ""+DatabaseHelper.setRelation(DatabaseHelper.idMain+"",""+id,urls[0],profilActivity.this);
        }
        @Override
        protected void onPostExecute(String result)
        {
            if(result.equals("1"))
            {
                Intent i = new Intent(profilActivity.this,profilActivity.class);
                i.putExtra("refresh",id);i.putExtra("dontdisplay",display);
                i.putExtra("idFiltre",idFiltre);
                startActivity(i);
                finish();
            }
        }
    }


    private class DownloadRelations extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls)
        {
            baseActivity.relation = new Relation();
            return ""+DatabaseHelper.getRelation(profilActivity.this);
        }
        @Override
        protected void onPostExecute(String result)
        {
            if(result.equals("1"))
            {
                display();
            }
        }
    }


    private class DownloadUser extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls)
        {
            return ""+DatabaseHelper.getUser(user, Integer.parseInt(urls[0]), profilActivity.this);
        }
        @Override
        protected void onPostExecute(String result)
        {
            if(result.equals("1"))
            {
                new DownloadRelations().execute();
            }
        }
    }

    private class SearchFriend extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls)
        {
            return ""+DatabaseHelper.searchUser(urls[0], profilActivity.this);
        }
        @Override
        protected void onPostExecute(String result)
        {
            int r = Integer.parseInt(result);
            id = r;
            Log.e("dodormeur", "result =" + r);
            if(r<=0){ Toast.makeText(profilActivity.this, R.string.error_no_matching, Toast.LENGTH_LONG).show();finish();}
            else
            {
                new DownloadUser().execute(r+"");
            }
        }
    }
}
