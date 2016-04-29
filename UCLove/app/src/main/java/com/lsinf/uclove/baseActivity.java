package com.lsinf.uclove;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

public class baseActivity extends AppCompatActivity
{

    public static User mainUser = null;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    public void createNavigationMenu()
    {

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toggle = new ActionBarDrawerToggle
                (
                        this,
                        drawerLayout,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close
                )
        {
        };
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    public void clickMenu(View v)
    {
        Intent i = null;
        if(v.getTag().equals("disconnect"))
        {
            baseActivity.mainUser = null;
            i = new Intent(this, ConnexionActivity.class);
        }
        else if(v.getTag().equals("profil"))
            i = new Intent(this,profilActivity.class ); // Your list's Intent
        else if(v.getTag().equals("preferences"))
            i = new Intent(this,preferenceActivity.class ); // Your list's Intent
        else if(v.getTag().equals("amis"))
            i = new Intent(this,friendsActivity.class ); // Your list's Intent
        else if(v.getTag().equals("requetes"))
            i = new Intent(this,requestActivity.class ); // Your list's Intent
        else
            i = new Intent(this,friendsActivity.class ); // Your list's Intent

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (toggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
