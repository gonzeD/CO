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
        if(v.getTag().equals("test1"))
            i = new Intent(this,HomeActivity.class ); // Your list's Intent
        else
            i = new Intent(this,FriendActivity.class ); // Your list's Intent

        //i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
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
