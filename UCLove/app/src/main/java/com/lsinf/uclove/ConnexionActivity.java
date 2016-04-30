package com.lsinf.uclove;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class ConnexionActivity extends AppCompatActivity
{
    private int downloadDone = 0;
    private int downloadToDo = 1;

    private int tryingToConnect = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        baseActivity.mainUser = null;
        setContentView(R.layout.connexion_activity);
    }

    public void connect(View v)
    {
        if(tryingToConnect == 0) {
            tryingToConnect = 1;
            new DownloadCheckConnection().execute(
                    ((EditText) findViewById(R.id.pseudo)).getText().toString(),
                    ((EditText) findViewById(R.id.password)).getText().toString());
        }
    }

    public void register(View v)
    {
        Intent i = new Intent(this,InscriptionActivity.class);
        startActivity(i);
    }


    public void finallyConnect()
    {
        if(downloadDone != 1)return;
        tryingToConnect = 0;
        Intent i = new Intent(ConnexionActivity.this,HomeActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        startActivity(i);
        this.finish();
    }


    private class DownloadCheckConnection extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls)
        {
            return ""+DatabaseHelper.connect(urls[0],urls[1],ConnexionActivity.this);
        }
        @Override
        protected void onPostExecute(String result)
        {
            if(result.equals(""+DatabaseHelper.OK))
            {
                new DownloadMainUser().execute();
               // new DownloadAllUsers().execute();
                new DownloadFiltres().execute();
                new DownloadMessage().execute();
                new DownloadRelations().execute();

            }
            else if(result.equals(""+DatabaseHelper.NO_INTERNET)) {;
                tryingToConnect = 0;
                Toast.makeText(ConnexionActivity.this, R.string.error_no_internet, Toast.LENGTH_LONG).show();
            }
            else if(result.equals(""+DatabaseHelper.INTERNET_ERROR)){
                tryingToConnect = 0;
                Toast.makeText(ConnexionActivity.this, R.string.error_internet, Toast.LENGTH_LONG).show();
            }
        }
    }


    private class DownloadMainUser extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls)
        {
            baseActivity.mainUser = new User();
            return ""+DatabaseHelper.getUser(baseActivity.mainUser,DatabaseHelper.idMain,ConnexionActivity.this);
        }
        @Override
        protected void onPostExecute(String result)
        {
            if(result.equals("1")){downloadDone++;
            finallyConnect();}
        }
    }


    private class DownloadMessage extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls)
        {
            baseActivity.messages = new Messages();
            return ""+DatabaseHelper.getMessages(ConnexionActivity.this);
        }
        @Override
        protected void onPostExecute(String result)
        {
            if(result.equals("1")){downloadDone++;
            finallyConnect();}
        }
    }

    private class DownloadAllUsers extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls)
        {
            baseActivity.allUsers = new ArrayList<User>();
            return ""+DatabaseHelper.getAllUsers(ConnexionActivity.this);
        }
        @Override
        protected void onPostExecute(String result)
        {
            if(result.equals("1")){downloadDone++;
                finallyConnect();}
        }
    }

    private class DownloadRelations extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls)
        {
            baseActivity.relation = new Relation();
            return ""+DatabaseHelper.getRelation(ConnexionActivity.this);
        }
        @Override
        protected void onPostExecute(String result)
        {
            if(result.equals("1")){downloadDone++;
                finallyConnect();}
        }
    }

    private class DownloadFiltres extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls)
        {
            baseActivity.filtres = new ArrayList<Filtre>();
            return ""+DatabaseHelper.getFiltres(ConnexionActivity.this);
        }
        @Override
        protected void onPostExecute(String result)
        {
            if(result.equals("1")){downloadDone++;
                finallyConnect();}
        }
    }
}
