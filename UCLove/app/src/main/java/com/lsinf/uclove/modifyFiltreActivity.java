package com.lsinf.uclove;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by damien on 28/04/16.
 */
public class modifyFiltreActivity extends baseActivity {
    ViewGroup root;
    int pos;
    Filtre t;
    boolean working = false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.modify_filtre_activity);
        root = (ViewGroup)findViewById(R.id.root);
        createNavigationMenu();
        pos = Integer.parseInt(getIntent().getStringExtra("pos"));
        if(pos == -1)
        {
            Filtre f = new Filtre();
            f.idFiltre=-1;
            //TODO : add default stuff
            mainUser.filtres.add(f);
            pos = mainUser.filtres.size()-1;
        }
        t = mainUser.filtres.get(pos);
        for(int i = 0;i<t.cara.size();i++)
        {
            addDisplay(t.nameCara.get(i),t.cara.get(i));
        }
    }

    public void addDisplay(String name,String cara)
    {
        TextView t = new TextView(this);
        t.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        t.setText(name+" : "+cara);
        root.addView(t);
    }


    public void click(View v)
    {
        if(working)return;
        if(v.getTag().toString().equals("reset"))
        {
            root.removeAllViews();
            t.cara.clear();
            t.nameCara.clear();
        }
        if(v.getTag().toString().equals("end"))
        {

            new updateFiltre().execute(((EditText)findViewById(R.id.name)).getText().toString());
            working = true;
        }
        if(v.getTag().toString().equals("yeux"))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.modify_pick_yeux)
                    .setItems(R.array.eye_color, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which)
                        {
                            t.cara.add(getResources().getStringArray(R.array.eye_color)[which]);
                            t.nameCara.add(getResources().getString(R.string.modify_yeux));
                            addDisplay(t.nameCara.get(t.nameCara.size()-1),t.cara.get(t.cara.size()-1));
                        }
                    });
            builder.create().show();
        }
        if(v.getTag().toString().equals("cheveux"))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.modify_pick_hair)
                    .setItems(R.array.hair_color, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which)
                        {
                            t.cara.add(getResources().getStringArray(R.array.hair_color)[which]);
                            t.nameCara.add(getResources().getString(R.string.modify_cheveux));
                            addDisplay(t.nameCara.get(t.nameCara.size()-1),t.cara.get(t.cara.size()-1));
                        }
                    });
            builder.create().show();
        }
        if(v.getTag().toString().equals("sexe"))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.modify_pick_genre)
                    .setItems(R.array.genre, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which)
                        {
                            t.cara.add(getResources().getStringArray(R.array.genre)[which]);
                            t.nameCara.add(getResources().getString(R.string.modify_sexe));
                            addDisplay(t.nameCara.get(t.nameCara.size()-1),t.cara.get(t.cara.size()-1));
                        }
                    });
            builder.create().show();
        }

    }

    private class updateFiltre extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls)
        {

            return ""+DatabaseHelper.setFiltre(""+t.idFiltre,t.getData(),urls[0],modifyFiltreActivity.this,t.idFiltre == -1);
        }
        @Override
        protected void onPostExecute(String result)
        {
            if(result.equals("1"))finish();
        }
    }
}
