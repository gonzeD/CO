package com.lsinf.uclove;

import android.os.Bundle;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by damien on 20/04/16.
 */
public class SettingsActivity extends baseActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.setting_activity);

        //if(baseActivity.mainUser.getVille() != null)((TextView)findViewById(R.id.mycity)).editText.setHint("test");
        //((TextView)findViewById(R.id.mycity)).setText("test");


         createNavigationMenu();
    }
}
