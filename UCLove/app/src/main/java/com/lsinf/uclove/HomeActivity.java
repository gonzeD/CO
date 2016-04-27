package com.lsinf.uclove;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by damien on 20/04/16.
 * extended by Alban on 27/04/16
 */
public class HomeActivity extends baseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mainUser = new User();
        setContentView(R.layout.home_activity);
        if(baseActivity.mainUser.getPrenom() != null)((TextView)findViewById(R.id.prenom)).setText(baseActivity.mainUser.getPrenom());
        //else ((TextView)findViewById(R.id.prenom)).setText("test");
        if(baseActivity.mainUser.getNom() != null)((TextView)findViewById(R.id.nom)).setText(baseActivity.mainUser.getNom());
        //else ((TextView)findViewById(R.id.nom)).setText("test");
        if(baseActivity.mainUser.getSexe() != null)((TextView)findViewById(R.id.sexe)).setText(baseActivity.mainUser.getSexe());
        //else ((TextView)findViewById(R.id.sexe)).setText("test");
        if(baseActivity.mainUser.getAttirance() != null)((TextView)findViewById(R.id.attirance)).setText(baseActivity.mainUser.getAttirance());
        //else ((TextView)findViewById(R.id.attirance)).setText("test");
        String tab[] = baseActivity.mainUser.getHobby();
        String temp = " ";
        if(tab != null){
            for(int i = 0; i<tab.length; i++) temp+=tab[i];
        ((TextView)findViewById(R.id.hobby)).setText(temp);}
        else ((TextView)findViewById(R.id.hobby)).setText("Liste de mes Hobbys");
        if(baseActivity.mainUser.getNaissance() != null)((TextView)findViewById(R.id.naissance)).setText(baseActivity.mainUser.getNaissance());
        //else ((TextView)findViewById(R.id.naissance)).setText("test");
        if(baseActivity.mainUser.getVille() != null)((TextView)findViewById(R.id.ville)).setText(baseActivity.mainUser.getVille());
        //else ((TextView)findViewById(R.id.ville)).setText("test");
        if(baseActivity.mainUser.getMail() != null)((TextView)findViewById(R.id.mail)).setText(baseActivity.mainUser.getMail());
        //else ((TextView)findViewById(R.id.mail)).setText("test");
        if(baseActivity.mainUser.getYeux() != null)((TextView)findViewById(R.id.yeux)).setText(baseActivity.mainUser.getYeux());
        //else ((TextView)findViewById(R.id.yeux)).setText("test");
        if(baseActivity.mainUser.getCheveux() != null)((TextView)findViewById(R.id.cheveux)).setText(baseActivity.mainUser.getCheveux());
        //else ((TextView)findViewById(R.id.cheveux)).setText("test");
        if(baseActivity.mainUser.getTel() != null)((TextView)findViewById(R.id.tel)).setText(baseActivity.mainUser.getTel());
        //else ((TextView)findViewById(R.id.tel)).setText("test");
        if(baseActivity.mainUser.getDescription() != null)((TextView)findViewById(R.id.description)).setText(baseActivity.mainUser.getDescription());
        //else ((TextView)findViewById(R.id.description)).setText("test");
        String tab1[] = baseActivity.mainUser.getDisponibilite();
        String temp1 = " ";
        if(tab1 != null){
        for(int i = 0;i<tab1.length;i++)
        {temp1=temp1+tab1[i]+" ";}
        ((TextView)findViewById(R.id.dispo)).setText(temp1);}
        else ((TextView)findViewById(R.id.dispo)).setText("Mes Disponibilitées");







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
