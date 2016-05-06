package com.lsinf.uclove;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by damien on 28/04/16.
 * Permet de voir les disponibilité de l'autre utilisateur, après chargement de ses données
 */
public class askRdvActivity extends baseActivity {

    int id = 0;
    User other = null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        id = getIntent().getIntExtra("id",0);

        setContentView(R.layout.ask_rdv_activity);

       createNavigationMenu();
        new DownloadUser().execute();
    }
    public void message(View v)
    {
        Intent i = new Intent(this,messageActivity.class);
        Log.e("dodormeur", "" + id);
        i.putExtra("id", id);
        startActivity(i);
    }


    public String format(String s)
    {
        return Integer.parseInt(s)/100+":"+Integer.parseInt(s)%100;
    }
    private class DownloadUser extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls)
        {
            other = new User();
            return ""+DatabaseHelper.getUser(other,id,askRdvActivity.this);
        }
        @Override
        protected void onPostExecute(String result)
        {
            if(result.equals("1"))
            {
                Log.e("dodormeur","okokok");
                if(baseActivity.mainUser.getPrenom() != null)((TextView)findViewById(R.id.firstUser)).setText(baseActivity.mainUser.getPrenom());


                if(other.getPrenom() != null)((TextView)findViewById(R.id.secondUser)).setText(other.getPrenom());

                int ids[] = {R.id.monday1,R.id.monday2,R.id.tuesday1,R.id.tuesday2,R.id.wednesday1,R.id.wednesday2,R.id.thursday1,R.id.thursday2,R.id.friday1,R.id.friday2,R.id.saturday1,R.id.saturday2,R.id.sunday1,R.id.sunday2};
                String tab1[] = mainUser.getDisponibilite();
                String tab2[] = other.getDisponibilite();
                String to = " "+getResources().getString(R.string.to)+" ";//TODO

                for(int i = 0;i<ids.length/2;i++)
                {
                    ((TextView)findViewById(ids[i*2])).setText(format(tab1[i*2])+to+format(tab1[i*2+1]));
                    ((TextView)findViewById(ids[i*2+1])).setText(format(tab2[i*2])+to+format(tab2[i*2+1]));
                }
            }
        }
    }
}

