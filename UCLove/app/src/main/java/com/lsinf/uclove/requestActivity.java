package com.lsinf.uclove;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by damien on 28/04/16.
 * Affiche les demande d'amis de l'utilisateur
 */
public class requestActivity extends baseActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    ArrayList<String> names = new ArrayList<>();
    ArrayList<Integer> ids = new ArrayList<>();
    ArrayList<User> listUsers = new ArrayList<>();

    int done = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        names = new ArrayList<>();
        ids = new ArrayList<>();
        listUsers = new ArrayList<>();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.request_activity);
        createNavigationMenu();
        new DownloadRelation().execute();
    }

    public void display()
    {
        if(done == ids.size())
        {
            for(int i = 0;i<ids.size();i++)names.add(listUsers.get(i).getPrenom()+" "+listUsers.get(i).getNom());

            Log.e("dodormeur","displaying"+ids.size());
            findViewById(R.id.loading).setVisibility(View.GONE);

            mRecyclerView = (RecyclerView) findViewById(R.id.list_request);

            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);

            mAdapter = new requestAdapter(names,ids,this);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    int working = 0;
    public void block(View v) {
        if (working == 0) {
            working = 1;
            new changeRelation().execute(""+ids.get(Integer.parseInt(v.getTag().toString())),""+Relation.BLOQUED);
        }
    }
    public void accept(View v)
    {
        if (working == 0) {
            working = 1;
            new changeRelation().execute(""+ids.get(Integer.parseInt(v.getTag().toString())),""+Relation.FRIEND);

        }
    }//recreate ()

    private class changeRelation extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls)
        {
            return ""+DatabaseHelper.setRelation(urls[0],""+DatabaseHelper.idMain,urls[1],requestActivity.this)
                    +DatabaseHelper.setRelation(""+DatabaseHelper.idMain,urls[0],urls[1],requestActivity.this);
        }
        @Override
        protected void onPostExecute(String result)
        {
            if(result.equals("11"))
            {
                working = 0;
                recreate();
            }
        }
    }

    private class DownloadRelation extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls)
        {
            return ""+DatabaseHelper.getRelation(requestActivity.this);
        }
        @Override
        protected void onPostExecute(String result)
        {
            Relation r = baseActivity.relation;
            for(int i = 0;i<r.getSize();i++) {
                if (r.getRelation(i) == Relation.DEMAND && r.getAsker(i) != DatabaseHelper.idMain && r.getReceiver(i) == DatabaseHelper.idMain) {
                    ids.add(r.getAsker(i));
                    listUsers.add(new User());
                    new DownloadUsers().execute((listUsers.size()-1)+"",r.getAsker(i)+"");
                }
            }
            if(ids.size() == 0)
            {
                findViewById(R.id.alone).setVisibility(View.VISIBLE);
                findViewById(R.id.loading).setVisibility(View.GONE);
            }
        }
    }

    private class DownloadUsers extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls)
        {
            return ""+DatabaseHelper.getUser(listUsers.get(Integer.parseInt(urls[0])),Integer.parseInt(urls[1]),requestActivity.this);
        }
        @Override
        protected void onPostExecute(String result)
        {
            if(result.equals("1"))
            {
                done++;
                display();
            }
        }
    }
}
