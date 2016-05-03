package com.lsinf.uclove;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;

public class baseActivity extends AppCompatActivity
{

    public static User mainUser = null;
    public static Messages messages = null;
    public static ArrayList<User> allUsers = null;
    public static Relation relation = null;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    public void createNavigationMenu()
    {
        ((TextView)findViewById(R.id.menu_name)).setText(mainUser.getPrenom()+" "+mainUser.getNom());
        ((TextView)findViewById(R.id.menu_pseudo)).setText(DatabaseHelper.pseudo);
        new loadImageWeb((ImageView) findViewById(R.id.avatar)).execute(mainUser.getPhoto());
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
            mainUser = null;
            messages = null;
            allUsers = null;
            relation = null;
            i = new Intent(this, ConnexionActivity.class);
        }
        else if(v.getTag().equals("profil"))
            i = new Intent(this,profilActivity.class ); // Your list's Intent
        else if(v.getTag().equals("preferences"))
            i = new Intent(this,SettingsActivity.class ); // Your list's Intent
        else if(v.getTag().equals("reset"))
        { new DownloadMainUser().execute();;return;}
        else if(v.getTag().equals("amis"))
            i = new Intent(this,friendsActivity.class ); // Your list's Intent
        else if(v.getTag().equals("requetes"))
            i = new Intent(this,requestActivity.class ); // Your list's Intent
        else if(v.getTag().equals("rencontres"))
            i = new Intent(this,filtreActivity.class );
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

    private class DownloadMainUser extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls)
        {
            return ""+DatabaseHelper.reset(baseActivity.this);
        }
        @Override
        protected void onPostExecute(String result)
        {

        }
    }




}
