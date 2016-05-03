package com.lsinf.uclove;

import android.os.Bundle;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Locale;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;


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

    public void langue(View v)
    {
        /*Locale myLocale;
        if(((RadioButton)findViewById(R.id.language_french)).isChecked())myLocale= new Locale("en");
        else myLocale= new Locale("fr");
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, SettingsActivity.class);
        startActivity(refresh);
        finish();*/

    }
}
