package com.lsinf.uclove;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by damien on 23/04/16.
 */
public class DatabaseHelper
{
    private static String pseudo = null;
    private static String password = null;
    public static final int NO_INTERNET = -1;
    public static final int INTERNET_ERROR = 0;
    public static final int FIELD_ERROR = -2;
    public static final int PSEUDO_TAKEN = -3;
    public static final int OK = 1;

    static private String downloadUrl(String myurl,String name[],String args[])
    {
        InputStream is = null;
        int len = 5000;

        try
        {

            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();


            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setDoInput(true);
            conn.setDoOutput(true);



            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            String temp = "";
            if(name != null && args != null)
            for(int i = 0;i<args.length;i++)
            {
                if(i!= 0)temp+="&";
                temp+=name[i]+"="+args[i];
            }
            writer.write(temp);
            writer.flush();
            writer.close();
            os.close();

            conn.connect();
            int response = conn.getResponseCode();
            is = conn.getInputStream();

            // Convert the InputStream into a stringx&
            String content = "";

            java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
            content =  s.hasNext() ? s.next() : "";

            if (is != null)is.close();
            return content;
        }
        catch (IOException e)
        {
            if (is != null) try {is.close();} catch (IOException e1) {}
            return null;
        }
    }


    public static boolean checkInternet(Context ctx)
    {
        ConnectivityManager connMgr = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    public static int connect(String p,String mdp,Context ctx)
    {
        if(checkInternet(ctx))
        {
            try {
                String[] act = new String[]{"action","pseudo","password"};
                String[] arg = new String[]{"connect",p,mdp};
                String t = downloadUrl("http://dracognards.be/uclove/main.php",act,arg);
                Log.e("dodormeur",t);
                JSONObject jObject = new JSONObject(t);
                if(jObject == null || t == null)return INTERNET_ERROR;
                else if(jObject.has("success"))
                {
                    pseudo = p;
                    password = mdp;
                    return OK;
                }
                return INTERNET_ERROR;
            } catch (JSONException e) {return INTERNET_ERROR;}
        }
        else return NO_INTERNET;
    }

    public static int register(Context ctx,String... urls)
    {
        if(checkInternet(ctx))
        {
            try {
                String[] act = new String[]{"action","pseudo","password","nom","prenom","sexe","ddnais","mail","tel","ville"};
                String[] arg = new String[urls.length+1];
                arg[0] = "register";
                for( int i = 0; i < urls.length && i+1<act.length; i++)arg[i+1]=urls[i];
                String t = downloadUrl("http://dracognards.be/uclove/main.php",act,arg);
                Log.e("dodormeur",t);
                JSONObject jObject = new JSONObject(t);
                if(t == null || jObject == null)return INTERNET_ERROR;
                else if(jObject.has("success"))
                {
                    pseudo = urls[0];
                    return 1;
                }
                else return INTERNET_ERROR;
            } catch (JSONException e) {return INTERNET_ERROR;}
        }
        else return NO_INTERNET;
    }

    public static int getMainUser(User user,Context ctx)
    {
        if(checkInternet(ctx))
        {
            try {
                String[] act = new String[]{"action","pseudo","password"};
                String[] arg = new String[]{"user",pseudo,password};
                String t = downloadUrl("http://dracognards.be/uclove/main.php",act,arg);
                Log.e("dodormeur",t);
                JSONObject jObject = new JSONObject(t);
                if(t == null || jObject == null)return INTERNET_ERROR;

                else if(jObject.has("success"))
                {
                    if(jObject.has("ATTIRANCE"))user.setAttirance(jObject.getString("ATTIRANCE"));
                    if(jObject.has("CHEVEUX"))user.setCheveux(jObject.getString("CHEVEUX"));
                    if(jObject.has("YEUX"))user.setYeux(jObject.getString("YEUX"));
                    if(jObject.has("DESCRIPTION"))user.setDescription(jObject.getString("DESCRIPTION"));
                  //  user.setDisponibilite(jObject.getString("disponibilite"));
                 //   user.setFiltres(jObject.getString("filtres"));
                   // user.setHobby(jObject.getString("hobby"));
                    user.setLangue(jObject.getString("LANGUE"));
                    if(jObject.has("MAIL"))user.setMail(jObject.getString("MAIL"));
                    if(jObject.has("DDNAIS"))user.setNaissance(jObject.getString("DDNAIS"));
                    if(jObject.has("NOM"))user.setNom(jObject.getString("NOM"));
                    if(jObject.has("PRENOM"))user.setPrenom(jObject.getString("PRENOM"));
                    if(jObject.has("TEL"))user.setTel(jObject.getString("TEL"));
                    if(jObject.has("VILLE"))user.setVille(jObject.getString("VILLE"));
                    if(jObject.has("SEXE"))user.setSexe(jObject.getString("SEXE"));
                    return 1;
                }
                else return INTERNET_ERROR;
            } catch (JSONException e) {return INTERNET_ERROR;}
        }
        else return NO_INTERNET;
    }
}
