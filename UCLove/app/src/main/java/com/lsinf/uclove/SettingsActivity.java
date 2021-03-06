package com.lsinf.uclove;

import android.net.Uri;
import android.os.Bundle;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
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
 * Permet de modifier les parametres de l'utilisateur
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


        et = (EditText)findViewById(R.id.hobby);
        et.setText(baseActivity.mainUser.getHobby());

        et = (EditText)findViewById(R.id.description);
        et.setText(baseActivity.mainUser.getDescription());

        RadioGroup ra;
        ra = (RadioGroup)findViewById(R.id.button_attirance);
        ((RadioButton)ra.getChildAt(mainUser.getIdAttirance())).setChecked(true);
        ra = (RadioGroup)findViewById(R.id.button_color_hair);
        ((RadioButton)ra.getChildAt(mainUser.getIdCheveux())).setChecked(true);
       if(mainUser.getDisponibilite() != null)
       {
           String temp[] = mainUser.getDisponibilite();
           for(int i = 0;i<14;i++){
               if(temp.length <= i)times[i] = format("0000");
               else if(temp[i] == "")times[i] = format("0000");
               else times[i] = format(temp[i]);
       }}
        refreshShowDate();
         createNavigationMenu();
    }
    public String format(String s)
    {
        return Integer.parseInt(s)/100+":"+Integer.parseInt(s)%100;
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
        times[day*2+start] = time;
        refreshShowDate();


    }

    public void refreshShowDate()
    {
        int ids[] = {R.id.textView1,R.id.textView2,R.id.textView3,R.id.textView4,R.id.textView5,R.id.textView6,R.id.textView7,R.id.textView8
                ,R.id.textView9,R.id.textView10,R.id.textView11,R.id.textView12,R.id.textView13,R.id.textView14};

        for(int i = 0;i<14;i++)
        {
            ((TextView)findViewById(ids[i])).setText(times[i]);
        }
    }
    public void choosePicture(View v)
    {
        String url = "http://www.dracognards.be/uclove/up.php?id="+DatabaseHelper.idMain;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    public void set(View v)
    {
        Log.e("dodormeur","setting");
     /*   DatePicker datePicker = (DatePicker) findViewById(R.id.naissance);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();
        String date = day+"/"+month+"/"+year;
*/
      /*  int radioButtonID = ((RadioGroup) findViewById(R.id.button_sexe)).getCheckedRadioButtonId();
        View radioButton = ((RadioGroup) findViewById(R.id.button_sexe)).findViewById(radioButtonID);
        int idx = ((RadioGroup) findViewById(R.id.button_sexe)).indexOfChild(radioButton);*/

        int radioButtonID2 = ((RadioGroup) findViewById(R.id.button_attirance)).getCheckedRadioButtonId();
        View radioButton2 = ((RadioGroup) findViewById(R.id.button_attirance)).findViewById(radioButtonID2);
        int idx2 = ((RadioGroup) findViewById(R.id.button_attirance)).indexOfChild(radioButton2);

        /*int radioButtonID3 = ((RadioGroup) findViewById(R.id.button_eyes)).getCheckedRadioButtonId();
        View radioButton3 = ((RadioGroup) findViewById(R.id.button_eyes)).findViewById(radioButtonID3);
        int idx3 = ((RadioGroup) findViewById(R.id.button_eyes)).indexOfChild(radioButton3);*/

        int radioButtonID4 = ((RadioGroup) findViewById(R.id.button_color_hair)).getCheckedRadioButtonId();
        View radioButton4 = ((RadioGroup) findViewById(R.id.button_color_hair)).findViewById(radioButtonID4);
        int idx4 = ((RadioGroup) findViewById(R.id.button_color_hair)).indexOfChild(radioButton4);

        String  hobby,description,langue;
        if (((EditText) findViewById(R.id.hobby)).getText() != null){
            hobby = ((EditText) findViewById(R.id.hobby)).getText().toString();
        }
        else {
            hobby = "";
        }
        if (((EditText) findViewById(R.id.description)).getText() != null){
            description = ((EditText) findViewById(R.id.description)).getText().toString();
        }
        else {
            description = "";
        }
      /*  if (((EditText) findViewById(R.id.langue)).getText() != null){
            langue = ((EditText) findViewById(R.id.langue)).getText().toString();
        }
        else {
            langue = "";
        }*/


        String Sdispo = "";
        for(int i = 0;i<14;i++)Sdispo += times[i].split(":")[0]+times[i].split(":")[1]+":";
        Log.e("dodormeur","exe");
        new DownloadWebpageTask().execute(
                DatabaseHelper.pseudo,
                DatabaseHelper.password,
                mainUser.getNom(),
                mainUser.getPrenom(),
                mainUser.getIDYeux()+"",
                idx2+"",
                mainUser.getNaissance(),
                ((EditText) findViewById(R.id.mail)).getText().toString(),
                ((EditText) findViewById(R.id.phone)).getText().toString(),
                ((EditText) findViewById(R.id.city)).getText().toString(),
                mainUser.getIdSexe()+"",
                idx4+"",
                hobby,
                description,
                mainUser.getLangue(),
                Sdispo);
    }




    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls)
        {
            Log.e("dodormeur","setting");
            return ""+DatabaseHelper.setRegister(SettingsActivity.this,urls);
        }
        @Override
        protected void onPostExecute(String result)
        {
            if(result.equals(""+DatabaseHelper.OK))
            {
                new DownloadMainUser().execute();
            }

        }
    }

    private class DownloadMainUser extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls)
        {
            baseActivity.mainUser = new User();
            return ""+DatabaseHelper.getUser(baseActivity.mainUser, DatabaseHelper.idMain, SettingsActivity.this);
        }
        @Override
        protected void onPostExecute(String result)
        {
            if(result.equals("1")){Toast.makeText(SettingsActivity.this, R.string.update_done, Toast.LENGTH_LONG).show();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result","ok");
                setResult(Activity.RESULT_OK,returnIntent);
                finish();}
        }
    }

}

