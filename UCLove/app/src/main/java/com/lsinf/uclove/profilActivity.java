package com.lsinf.uclove;

import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by damien on 28/04/16.
 */
public class profilActivity extends baseActivity {

    User user = new User();
    int id;
    int state = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        id = getIntent().getIntExtra("id",-1);
        setContentView(R.layout.profile_activity);
        createNavigationMenu();

        if(id != -1)
        {
            new DownloadUser().execute();
        }
    }


    public void display()
    {
        Resources r = getResources();
        state = relation.getRelationByAskerId(id);
        ((TextView)findViewById(R.id.text_relation)).setText(r.getString(R.string.profile_actual_relation)+" "+r.getStringArray(R.array.profile_relation)[state]);

        if(state == Relation.NOTHING)
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
            return ""+DatabaseHelper.getUser(user,id,profilActivity.this);
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
}
