package com.lsinf.uclove;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ConnexionActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion_activity);
    }

    public void connect(View v)
    {
        new DownloadWebpageTask().execute(
                ((EditText)findViewById(R.id.pseudo)).getText().toString(),
                ((EditText)findViewById(R.id.password)).getText().toString());
    }

    public void register(View v)
    {
        Intent i = new Intent(this,InscriptionActivity.class);
        startActivity(i);
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
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
                Intent i = new Intent(ConnexionActivity.this,HomeActivity.class);
                startActivity(i);
            }
            else if(result.equals(""+DatabaseHelper.NO_INTERNET))
                Toast.makeText(ConnexionActivity.this, R.string.error_no_internet, Toast.LENGTH_LONG).show();

            else if(result.equals(""+DatabaseHelper.INTERNET_ERROR))
                Toast.makeText(ConnexionActivity.this, R.string.error_internet, Toast.LENGTH_LONG).show();
        }
    }

}
