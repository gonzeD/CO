package com.lsinf.uclove;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by damien on 20/04/16.
 */
public class HomeActivity extends baseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_activity);
        createNavigationMenu();
    }


    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls)
        {
            mainUser = new User();
            return ""+DatabaseHelper.getMainUser(baseActivity.mainUser,HomeActivity.this);
        }
        @Override
        protected void onPostExecute(String result)
        {

        }
    }
}
