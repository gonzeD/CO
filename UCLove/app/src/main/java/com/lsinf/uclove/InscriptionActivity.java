package com.lsinf.uclove;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by damien on 13/04/16.
 */
public class InscriptionActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.inscription_activity);
        // createNavigationMenu();
    }

    public void register(View v)
    {
        new DownloadWebpageTask().execute(
                ((EditText)findViewById(R.id.pseudo)).getText().toString(),
                ((EditText)findViewById(R.id.password)).getText().toString(),
                ((EditText)findViewById(R.id.sexe)).getText().toString()

            );
    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls)
        {
            return ""+DatabaseHelper.register(urls[0],urls[1],urls[2],InscriptionActivity.this);
        }
        @Override
        protected void onPostExecute(String result)
        {
            if(result.equals(""+DatabaseHelper.OK))
            {
                Toast.makeText(InscriptionActivity.this, R.string.register_done, Toast.LENGTH_LONG).show();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result","ok");
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
            else if(result.equals(""+DatabaseHelper.PSEUDO_TAKEN))
            {
                Toast.makeText(InscriptionActivity.this, R.string.register_pseudo_taken, Toast.LENGTH_LONG).show();
            }
            else if(result.equals(""+DatabaseHelper.FIELD_ERROR))
            {
                Toast.makeText(InscriptionActivity.this, R.string.register_field_error, Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(InscriptionActivity.this, R.string.error_internet, Toast.LENGTH_LONG).show();
            }
        }
    }
}
