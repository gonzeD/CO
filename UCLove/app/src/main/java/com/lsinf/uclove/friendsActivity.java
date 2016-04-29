package com.lsinf.uclove;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by damien on 28/04/16.
 */
public class friendsActivity extends baseActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public ArrayList<User> listUsers = new ArrayList<User>();
    ArrayList<Friend> friendList = new ArrayList<Friend>();
    private int done = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.friend_activity);
        createNavigationMenu();
        for(int i = 0;i<baseActivity.relation.getSize();i++)
        {
            if(baseActivity.relation.getRelation(i) >= relation.FRIEND)
            {
                listUsers.add(new User());
                new DownloadUsers().execute(""+i,""+baseActivity.relation.getId(i));
            }
        }

        if(baseActivity.relation.getSize() == 0)
        {
            findViewById(R.id.alone).setVisibility(View.VISIBLE);
            findViewById(R.id.loading).setVisibility(View.GONE);
        }
    }

    public void display()
    {
        if(done == baseActivity.relation.getSize())
        {

            for(int i= 0;i<listUsers.size();i++)
            {
                Friend temp = new Friend();
                temp.id = listUsers.get(i).getID();
                temp.name = listUsers.get(i).getPrenom()+ " "+listUsers.get(i).getNom();
                temp.photo = listUsers.get(i).getPhoto()[0];
                temp.text = listUsers.get(i).getDescription();
                friendList.add(temp);
            }
            Log.e("dodormeur","displaying"+friendList.size());
            findViewById(R.id.loading).setVisibility(View.GONE);

            mRecyclerView = (RecyclerView) findViewById(R.id.list_friend);

            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);

            mAdapter = new FriendAdapter(friendList);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    public void rdv(View v)
    {
        Log.e("dodormeur","rdv"+v.getTag().toString());
    }
    public void click(View v)
    {
        Log.e("dodormeur","click"+v.getTag().toString());
        Intent i = new Intent(this,messageActivity.class);
        Log.e("dodormeur",""+friendList.get(Integer.parseInt(v.getTag().toString())).id);
        i.putExtra("id",friendList.get(Integer.parseInt(v.getTag().toString())).id);
        startActivity(i);
    }
    //params : 0 = case de listUser, 1 = id de l'user
    private class DownloadUsers extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls)
        {
            return ""+DatabaseHelper.getUser(listUsers.get(Integer.parseInt(urls[0])),Integer.parseInt(urls[1]),friendsActivity.this);
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
