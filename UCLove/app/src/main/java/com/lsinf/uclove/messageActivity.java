package com.lsinf.uclove;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by damien on 28/04/16.
 */
public class messageActivity extends baseActivity {

    int alreadyLoading = 0;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    int idOther = 0;
    int sizeLast = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        idOther = getIntent().getExtras().getInt("id");
        setContentView(R.layout.message_activity);

        loadMessages();
        mHandler = new Handler(Looper.getMainLooper());

        createNavigationMenu();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(mHandler!= null)mHandler.removeCallbacks(myTask);
    }

    private Handler mHandler;
    Runnable myTask = new Runnable() {
        @Override
        public void run() {
            //do work
            loadMessages();
            mHandler.postDelayed(this, 1000);
        }
    };
    @Override
    public void onStart() {
        super.onStart();
        mHandler.postDelayed(myTask, 1000);
    }


    public void loadMessages()
    {
        if(alreadyLoading == 0)
        {
            alreadyLoading = 1;
            new DownloadMessage().execute();
        }
    }

    public void displayMessages()
    {
        Messages m = baseActivity.messages;
        ArrayList<Message> listMessage = new ArrayList<Message>();
        Log.e("dodormeur","m size "+m.size()+" id "+idOther+"listMessage size "+listMessage.size());
        for(int i = 0;i<m.size();i++)
        {
            if(m.getGetter(i) == idOther || m.getSender(i) == idOther)
            {
                Message temp = new Message();
                temp.sender = m.getGetter(i) == idOther;
                temp.text = m.getText(i);
                listMessage.add(temp);
            }
        }
        Log.e("dodormeur","m size "+m.size()+" id "+idOther+"listMessage size "+listMessage.size());
        if(sizeLast == listMessage.size())return;
        sizeLast = listMessage.size();
        mRecyclerView = (RecyclerView) findViewById(R.id.list_message);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MessageAdapter(listMessage,this);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void send(View v)
    {
        new SendMessage().execute(((EditText)findViewById(R.id.text_to_send)).getText().toString());
    }

    private class DownloadMessage extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls)
        {
            if(baseActivity.messages == null)baseActivity.messages = new Messages();
            return ""+DatabaseHelper.getMessages(messageActivity.this);
        }
        @Override
        protected void onPostExecute(String result)
        {
            alreadyLoading = 0;
            if(result.equals("1"))
            {
                displayMessages();
            }
        }
    }


    private class SendMessage extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls)
        {
            return ""+DatabaseHelper.sendMessage(urls[0],idOther,messageActivity.this);
        }
        @Override
        protected void onPostExecute(String result)
        {
            if(result.equals("1"))
            {
                ((EditText)findViewById(R.id.text_to_send)).setText("");
                displayMessages();
            }
        }
    }
}
