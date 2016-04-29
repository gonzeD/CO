package com.lsinf.uclove;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import javax.swing.text.html.ImageView;

/**
 * Created by damien on 13/04/16.
 */
public class InscriptionActivity extends AppCompatActivity
{
    Bitmap picture = null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.inscription_activity);
        // createNavigationMenu();
    }

    public void register(View v)
    {

     //  "pseudo","password","nom","prenom","sexe","ddnais","mail","tel","ville"};
        DatePicker datePicker = (DatePicker) findViewById(R.id.naissance);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();
        String date = day+"/"+month+"/"+year;

        new DownloadWebpageTask().execute(
                ((EditText) findViewById(R.id.pseudo)).getText().toString(),
                ((EditText) findViewById(R.id.password)).getText().toString(),
                ((EditText) findViewById(R.id.name)).getText().toString(),
                ((EditText) findViewById(R.id.firstname)).getText().toString(),
                ((EditText) findViewById(R.id.sexe)).getText().toString(),
                date,
                ((EditText) findViewById(R.id.mail)).getText().toString(),
                ((EditText) findViewById(R.id.phone)).getText().toString(),
                ((EditText) findViewById(R.id.city)).getText().toString(),
                ((EditText) findViewById(R.id.hobby)).getText().toString(),
                ((EditText) findViewById(R.id.description)).getText().toString(),
                ((EditText) findViewById(R.id.langue)).getText().toString(),
                ((RadioButton) (findViewById(((RadioGroup) findViewById(R.id.button_sexe)).getCheckedRadioButtonId()))).getText().toString()
        );
    }

    public void takePicture(View v)
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 1);
        }
    }

    public void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            picture = (Bitmap) extras.get("data");
            ((ImageView)findViewById(R.id.picturePreview)).setImageBitmap(imageBitmap);
        }
    }
    public void choosePicture(View v)
    {

    }

    private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls)
        {
            return ""+DatabaseHelper.register(InscriptionActivity.this,urls);
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
