package com.lsinf.uclove;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ConnexionActivity extends AppCompatActivity
{


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.connexion_activity);
        // createNavigationMenu();
       // Intent i = new Intent(ConnexionActivity.this,HomeActivity.class);
        //i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        //startActivity(i);
    }

    public void connect(View v)
    {
        Intent i = new Intent(this,HomeActivity.class);
        //i.setFlags(i.getFlags() |Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

    public void register(View v)
    {
        Intent i = new Intent(this,InscriptionActivity.class);
        //i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(i);

    }

}
