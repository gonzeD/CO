package com.lsinf.uclove;

import android.os.Bundle;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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


        EditText et;
        et = (EditText)findViewById(R.id.mail);
        et.setText(baseActivity.mainUser.getMail());

        et = (EditText)findViewById(R.id.phone);
        et.setText(baseActivity.mainUser.getTel());

        et = (EditText)findViewById(R.id.city);
        et.setText(baseActivity.mainUser.getVille());

        //a decomenter une fois que hobby n'est plus un tableau
        //et = (EditText)findViewById(R.id.hobby);
        //et.setText(baseActivity.mainUser.getHobby());

        et = (EditText)findViewById(R.id.description);
        et.setText(baseActivity.mainUser.getDescription());

        RadioGroup ra;
        ra = (RadioGroup)findViewById(R.id.button_attirance);
        ((RadioButton)ra.getChildAt(mainUser.getIdAttirance())).setChecked(true);
        ra = (RadioGroup)findViewById(R.id.button_color_hair);
        ((RadioButton)ra.getChildAt(mainUser.getIdCheveux())).setChecked(true);

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
