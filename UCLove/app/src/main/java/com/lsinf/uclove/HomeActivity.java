package com.lsinf.uclove;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by damien on 20/04/16.
 * extended by Alban on 27/04/16
 */
public class HomeActivity extends baseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_activity);


        if(baseActivity.mainUser.getPrenom() != null)((TextView)findViewById(R.id.prenom)).setText(baseActivity.mainUser.getPrenom());
        //else ((TextView)findViewById(R.id.prenom)).setText("test");
        if (baseActivity.mainUser.getNom() != null)
            ((TextView) findViewById(R.id.nom)).setText(baseActivity.mainUser.getNom());
        //else ((TextView)findViewById(R.id.nom)).setText("test");

        if(baseActivity.mainUser.getSexe(this) != null)((TextView)findViewById(R.id.sexe)).setText(baseActivity.mainUser.getSexe(this));
        //else ((TextView)findViewById(R.id.sexe)).setText("test");
        if(baseActivity.mainUser.getAttirance(this) != null)((TextView)findViewById(R.id.attirance)).setText(baseActivity.mainUser.getAttirance(this));
        //else ((TextView)findViewById(R.id.attirance)).setText("test");
        String tab= baseActivity.mainUser.getHobby();
        if(tab != null){((TextView)findViewById(R.id.hobby)).setText(tab);}
        else ((TextView)findViewById(R.id.hobby)).setText("Liste de mes Hobbies");
        if(baseActivity.mainUser.getNaissance() != null)((TextView)findViewById(R.id.naissance)).setText(baseActivity.mainUser.getNaissance());
        //else ((TextView)findViewById(R.id.naissance)).setText("test");
        if(baseActivity.mainUser.getVille() != null)((TextView)findViewById(R.id.ville)).setText(baseActivity.mainUser.getVille());
        //else ((TextView)findViewById(R.id.ville)).setText("test");
        if(baseActivity.mainUser.getMail() != null)((TextView)findViewById(R.id.mail)).setText(baseActivity.mainUser.getMail());
        //else ((TextView)findViewById(R.id.mail)).setText("test");
        if(baseActivity.mainUser.getYeux(this) != null)((TextView)findViewById(R.id.yeux)).setText(baseActivity.mainUser.getYeux(this));
        //else ((TextView)findViewById(R.id.yeux)).setText("test");
        if(baseActivity.mainUser.getCheveux(this) != null)((TextView)findViewById(R.id.cheveux)).setText(baseActivity.mainUser.getCheveux(this));
        //else ((TextView)findViewById(R.id.cheveux)).setText("test");
        if(baseActivity.mainUser.getTel() != null)((TextView)findViewById(R.id.tel)).setText(baseActivity.mainUser.getTel());
        //else ((TextView)findViewById(R.id.tel)).setText("test");
        if(baseActivity.mainUser.getLangue() != null)((TextView)findViewById(R.id.lang)).setText(baseActivity.mainUser.getLangue());
        //else ((TextView)findViewById(R.id.lang)).setText("test");
        if(baseActivity.mainUser.getDescription() != null)((TextView)findViewById(R.id.description)).setText(baseActivity.mainUser.getDescription());
        else ((TextView)findViewById(R.id.description)).setText("test");
        String tab1[] = baseActivity.mainUser.getDisponibilite();
        String temp1 = " ";
        if(tab1 != null)
        {
            for(int i = 0;i<tab1.length/2;i++)
            {
                temp1+=Integer.parseInt(tab1[i])/100+":"+Integer.parseInt(tab1[i])%100+"\n";
            }
            ((TextView)findViewById(R.id.dispo)).setText(temp1);
        }
        else ((TextView)findViewById(R.id.dispo)).setText("Mes Disponibilités");


        createNavigationMenu();


        if(mainUser.getPhoto().length>0)new loadImageWeb((ImageView) findViewById(R.id.photo1)).execute(mainUser.getPhoto()[0]);
        if(mainUser.getPhoto().length>1)new loadImageWeb((ImageView) findViewById(R.id.photo2)).execute(mainUser.getPhoto()[1]);
        if(mainUser.getPhoto().length>2)new loadImageWeb((ImageView) findViewById(R.id.photo3)).execute(mainUser.getPhoto()[2]);
    }


   public void change(View v) {

       Intent i = new Intent(this, SettingsActivity.class);
        startActivityForResult(i,1);
   }

    public void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        recreate();
    }
}
