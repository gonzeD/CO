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
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


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

    public void showTimePickerDialog(View v) {
        TimePickerFragment newFragment = new TimePickerFragment();
        if(v.getTag().equals("start1"))newFragment.set(0,0);
        if(v.getTag().equals("start2"))newFragment.set(1,0);
        if(v.getTag().equals("start3"))newFragment.set(2,0);
        if(v.getTag().equals("start4"))newFragment.set(3,0);
        if(v.getTag().equals("start5"))newFragment.set(4,0);
        if(v.getTag().equals("start6"))newFragment.set(5,0);
        if(v.getTag().equals("start7"))newFragment.set(6,0);
        if(v.getTag().equals("end1"))newFragment.set(0,1);
        if(v.getTag().equals("end2"))newFragment.set(1,1);
        if(v.getTag().equals("end3"))newFragment.set(2,1);
        if(v.getTag().equals("end4"))newFragment.set(3,1);
        if(v.getTag().equals("end5"))newFragment.set(4,1);
        if(v.getTag().equals("end6"))newFragment.set(5,1);
        if(v.getTag().equals("end7"))newFragment.set(6,1);

        newFragment.show(getSupportFragmentManager(), "timePicker");

    }
    String times[] = {"00:00","00:00","00:00","00:00","00:00","00:00","00:00","00:00","00:00","00:00","00:00","00:00","00:00","00:00"};
    public void setDate(int day,int start,String time)
    {
        int ids[] = {R.id.textView1,R.id.textView2,R.id.textView3,R.id.textView4,R.id.textView5,R.id.textView6,R.id.textView7,R.id.textView8
    ,R.id.textView9,R.id.textView10,R.id.textView11,R.id.textView12,R.id.textView13,R.id.textView14};
        times[day*2+start] = time;
        for(int i = 0;i<14;i++)
        {
            ((TextView)findViewById(ids[i])).setText(times[i]);
        }


    }
}

