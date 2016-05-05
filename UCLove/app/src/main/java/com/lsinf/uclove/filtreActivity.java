package com.lsinf.uclove;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by damien on 28/04/16.
 */
public class filtreActivity extends baseActivity {

    ArrayList<String> listFiltre = new ArrayList<>();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private int actualFiltre = 0;

    public void create(View v)
    {
        Intent i = new Intent(this,modifyFiltreActivity.class);
        i.putExtra("pos", "-1");
        startActivityForResult(i,1);
    }


    public void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        Log.e("LSINF","result");
        if (requestCode == 1 && resultCode == RESULT_OK) {
            recreate();
        }
        }
    public void search(View v)
    {
        actualFiltre = Integer.parseInt(v.getTag().toString());

        Intent i = new Intent(filtreActivity.this,profilActivity.class);
        i.putExtra("idFiltre",actualFiltre);
        startActivity(i);
       // new SearchFriend().execute(mainUser.getFiltres().get(actualFiltre).getData());
}

    public void modify(View v)
    {
        Intent i = new Intent(this,modifyFiltreActivity.class);
        i.putExtra("pos",v.getTag().toString());
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.filtre_activity);
        createNavigationMenu();
        new DownloadFiltres().execute();
    }

    public void display()
    {
        //listFiltre.add(getResources().getString(R.string.filtre_no_filtre));
        if(mainUser.getFiltres() == null) Log.e("dodormeur","pas de filtre");
        Log.e("dodormeur","filtre");
        for(int i = 0;i<mainUser.getFiltres().size();i++) {
            listFiltre.add(mainUser.getFiltres().get(i).name);
        }
        findViewById(R.id.loading).setVisibility(View.GONE);
        mRecyclerView = (RecyclerView) findViewById(R.id.list_filtre);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new filtreAdapter(listFiltre,this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private class DownloadFiltres extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls)
        {
            baseActivity.mainUser.filtres = new ArrayList<Filtre>();
            return ""+DatabaseHelper.getFiltres(filtreActivity.this);
        }
        @Override
        protected void onPostExecute(String result)
        {
            if(result.equals("1"))display();
        }
    }
/*
    private class SearchFriend extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls)
        {
            return ""+DatabaseHelper.searchUser(urls[0],filtreActivity.this);
        }
        @Override
        protected void onPostExecute(String result)
        {
            int r = Integer.parseInt(result);
            Log.e("dodormeur","result ="+r);
            if(r<=0) Toast.makeText(filtreActivity.this, R.string.error_no_matching, Toast.LENGTH_LONG).show();
            else
            {
                Intent i = new Intent(filtreActivity.this,profilActivity.class);
                i.putExtra("id",r);
                startActivityForResult(i,1);
            }
        }
    }*/
}
