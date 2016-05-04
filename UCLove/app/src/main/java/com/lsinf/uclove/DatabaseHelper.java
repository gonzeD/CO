package com.lsinf.uclove;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
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
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by damien on 23/04/16.
 */
public class DatabaseHelper {
    public static String pseudo = null;
    private static String password = null;
    public static int idMain = 0;
    public static final int NO_INTERNET = -1;
    public static final int INTERNET_ERROR = 0;
    public static final int FIELD_ERROR = -2;
    public static final int PSEUDO_TAKEN = -3;
    public static final int OK = 1;

    static private String downloadUrl(String myurl, String name[], String args[], boolean ascii) {
        InputStream is = null;
        int len = 5000;
        Log.e("dodormeur", "downloading" + myurl);
        try {

            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();


            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setDoInput(true);
            conn.setDoOutput(true);


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = null;
            if (ascii) writer = new BufferedWriter(new OutputStreamWriter(os, "ASCII"));
            else writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            String temp = "";
            if (name != null && args != null)
                for (int i = 0; i < args.length; i++) {
                    if (i != 0) temp += "&";
                    temp += name[i] + "=" + args[i];
                }
            Log.e("dodormeur", temp);
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
            content = s.hasNext() ? s.next() : "";

            if (is != null) is.close();
            return content;
        } catch (IOException e) {
            if (is != null) try {
                is.close();
            } catch (IOException e1) {
            }
            return null;
        }
    }


    public static boolean checkInternet(Context ctx) {
        ConnectivityManager connMgr = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    public static int connect(String p, String mdp, Context ctx) {
        if (checkInternet(ctx)) {
            try {
                String[] act = new String[]{"action", "pseudo", "password"};
                String[] arg = new String[]{"connect", p, mdp};
                Log.e("dodormeur", "internet connecting");
                String t = downloadUrl("http://dracognards.be/uclove/main.php", act, arg, false);
                Log.e("dodormeur", t);
                JSONObject jObject = new JSONObject(t);
                if (jObject == null || t == null) return INTERNET_ERROR;
                else if (jObject.has("success")) {
                    pseudo = p;
                    password = mdp;
                    idMain = jObject.getInt("ID");
                    Log.e("dodormeur", "id : " + idMain);
                    return OK;
                }
                return INTERNET_ERROR;
            } catch (Exception e) {
                return INTERNET_ERROR;
            }
        } else return NO_INTERNET;
    }


    public static String uploadPicture(Context ctx, Bitmap b) {
        Log.e("dodormeur", "starting upload");
        InputStream inputStream;
        if (checkInternet(ctx)) {
            try {
                Log.e("dodormeur", "starting upload2");
                // Must compress the Image to reduce image size to make upload easy
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.PNG, 50, baos);
                byte[] imageBytes = baos.toByteArray();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();

                String encodedString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                Log.e("dodormeur", encodedString.length() + "");
                String[] act = new String[]{"image"};
                String[] arg = new String[]{encodedString};
                String t = downloadUrl("http://dracognards.be/uclove/upload.php", act, arg, true);
                Log.e("dodormeur", t);
                JSONObject jObject = new JSONObject(t);
                if (t == null || jObject == null) return "" + INTERNET_ERROR;
                else if (jObject.has("success")) {
                    return jObject.getString("file");
                } else return "" + INTERNET_ERROR;
            } catch (Exception e) {
                return "" + INTERNET_ERROR;
            }
        } else return "" + NO_INTERNET;
    }



    /*

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, stream); //compress to which format you want.
            byte [] byte_arr = stream.toByteArray();
            String image_str = Base64.encodeBytes(byte_arr);
            ArrayList<NameValuePair> nameValuePairs = new  ArrayList<NameValuePair>();

            nameValuePairs.add(new BasicNameValuePair("image",image_str));

             Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                  try{
                         HttpClient httpclient = new DefaultHttpClient();
                         HttpPost httppost = new HttpPost("server-link/folder-name/upload_image.php");
                         httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                         HttpResponse response = httpclient.execute(httppost);
                         String the_string_response = convertResponseToString(response);
                         runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    Toast.makeText(UploadImage.this, "Response " + the_string_response, Toast.LENGTH_LONG).show();
                                }
                            });

                     }catch(Exception e){
                          runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                Toast.makeText(UploadImage.this, "ERROR " + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                           System.out.println("Error in http connection "+e.toString());
                     }
            }
        });
         t.start();
        }

        public String convertResponseToString(HttpResponse response) throws IllegalStateException, IOException{

             String res = "";
             StringBuffer buffer = new StringBuffer();
             inputStream = response.getEntity().getContent();
             int contentLength = (int) response.getEntity().getContentLength(); //getting content length…..
              runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(UploadImage.this, "contentLength : " + contentLength, Toast.LENGTH_LONG).show();
            }
        });

             if (contentLength < 0){
             }
             else{
                    byte[] data = new byte[512];
                    int len = 0;
                    try
                    {
                        while (-1 != (len = inputStream.read(data)) )
                        {
                            buffer.append(new String(data, 0, len)); //converting to string and appending  to stringbuffer…..
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    try
                    {
                        inputStream.close(); // closing the stream…..
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    res = buffer.toString();     // converting stringbuffer to string…..

                    runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                       Toast.makeText(UploadImage.this, "Result : " + res, Toast.LENGTH_LONG).show();
                    }
                });
                    //System.out.println("Response => " +  EntityUtils.toString(response.getEntity()));
             }
             return res;
        }
}
     */

    public static int register(Context ctx, String... urls) {
        if (checkInternet(ctx)) {
            try {
                String[] act = new String[]{"action", "pseudo", "password", "nom", "prenom", "sexe", "attirance", "ddnais", "mail", "tel", "ville", "yeux", "cheveux", "hobby", "description", "langue", "picture"};
                String[] arg = new String[urls.length + 1];
                arg[0] = "register";
                for (int i = 0; i < urls.length && i + 1 < act.length && i + 1 < arg.length; i++)
                    arg[i + 1] = urls[i];
                String t = downloadUrl("http://dracognards.be/uclove/main.php", act, arg, false);
                Log.e("dodormeur", t);
                JSONObject jObject = new JSONObject(t);
                if (t == null || jObject == null) return INTERNET_ERROR;
                else if (jObject.has("success")) {
                    pseudo = urls[0];
                    return 1;
                } else return INTERNET_ERROR;
            } catch (Exception e) {
                return INTERNET_ERROR;
            }
        } else return NO_INTERNET;
    }

    public static int getUser(User user, int id, Context ctx) {
        if (checkInternet(ctx)) {
            try {
                String[] act = new String[]{"action", "id"};
                String[] arg = new String[]{"user", "" + id};
                String t = downloadUrl("http://dracognards.be/uclove/main.php", act, arg, false);
                Log.e("dodormeur", t);
                JSONObject jObject = new JSONObject(t);
                if (t == null || jObject == null) return INTERNET_ERROR;

                else if (jObject.has("success") && jObject.has("PHOTO")) {
                    if (jObject.has("ID")) user.setID(Integer.parseInt(jObject.getString("ID")));
                    if (jObject.has("ATTIRANCE")) user.setAttirance(jObject.getString("ATTIRANCE"));
                    if (jObject.has("CHEVEUX")) user.setCheveux(jObject.getString("CHEVEUX"));
                    if (jObject.has("YEUX")) user.setYeux(jObject.getString("YEUX"));
                    if (jObject.has("DESCRIPTION"))
                        user.setDescription(jObject.getString("DESCRIPTION"));
                    if (jObject.has("PHOTO")) user.setPhoto(jObject.getString("PHOTO").split(":"));
                    //  user.setDisponibilite(jObject.getString("disponibilite"));
                    //   user.setFiltres(jObject.getString("filtres"));
                    // user.setHobby(jObject.getString("hobby"));
                    user.setLangue(jObject.getString("LANGUE"));
                    if (jObject.has("MAIL")) user.setMail(jObject.getString("MAIL"));
                    if (jObject.has("DDNAISS")) user.setNaissance(jObject.getString("DDNAISS"));
                    if (jObject.has("NOM")) user.setNom(jObject.getString("NOM"));
                    if (jObject.has("PRENOM")) user.setPrenom(jObject.getString("PRENOM"));
                    if (jObject.has("TEL")) user.setTel(jObject.getString("TEL"));
                    if (jObject.has("VILLE")) user.setVille(jObject.getString("VILLE"));
                    if (jObject.has("SEXE")) user.setSexe(jObject.getString("SEXE"));
                    return 1;
                } else return INTERNET_ERROR;
            } catch (Exception e) {
                return INTERNET_ERROR;
            }
        } else return NO_INTERNET;
    }


    public static int getMessages(Context ctx) {
        if (checkInternet(ctx)) {
            try {
                String[] act = new String[]{"action", "id"};
                String[] arg = new String[]{"messageId", idMain + ""};
                String t = downloadUrl("http://dracognards.be/uclove/main.php", act, arg, false);
                Log.e("dodormeur", t);
                JSONObject jObject = new JSONObject(t);
                if (t == null || jObject == null) return INTERNET_ERROR;

                else if (jObject.has("success")) {
                    baseActivity.messages.clear();
                    int nb = 0;
                    if (jObject.has("number")) nb = jObject.getInt("number");
                    for (int i = 0; i < nb; i++) {
                        if (jObject.has("rec" + i) && jObject.has("ex" + i) && jObject.has("text" + i) && jObject.has("date" + i)) {
                            Log.e("dodormeur", "adding");
                            baseActivity.messages.add(jObject.getInt("rec" + i), jObject.getInt("ex" + i), jObject.getString("date" + i), jObject.getString("text" + i));
                        }
                    }
                    return 1;
                } else return INTERNET_ERROR;
            } catch (Exception e) {
                return INTERNET_ERROR;
            }
        } else return NO_INTERNET;
    }

    public static int getRelation(Context ctx) {
        if (checkInternet(ctx)) {
            try {
                String[] act = new String[]{"action", "id"};
                String[] arg = new String[]{"relation", idMain + ""};
                String t = downloadUrl("http://dracognards.be/uclove/main.php", act, arg, false);
                Log.e("dodormeur", t);
                JSONObject jObject = new JSONObject(t);
                if (t == null || jObject == null) return INTERNET_ERROR;

                else if (jObject.has("success")) {
                    baseActivity.relation = new Relation();
                    int nb = 0;
                    if (jObject.has("number")) nb = jObject.getInt("number");
                    for (int i = 0; i < nb; i++) {
                        if (jObject.has("asker" + i) && jObject.has("state" + i)) {
                            Log.e("dodormeur", "adding");
                            baseActivity.relation.add(jObject.getInt("asker" + i), jObject.getInt("receiver" + i), jObject.getInt("state" + i));
                        }
                    }
                    return 1;
                } else return INTERNET_ERROR;
            } catch (Exception e) {
                return INTERNET_ERROR;
            }
        } else return NO_INTERNET;
    }


    public static int getAllUsers(Context ctx) {
        return NO_INTERNET;
    }


    public static int getFiltres(Context ctx) {

        if (checkInternet(ctx)) {
            try {
                String[] act = new String[]{"action", "id"};
                String[] arg = new String[]{"getFiltre", idMain + ""};
                String t = downloadUrl("http://dracognards.be/uclove/main.php", act, arg, false);
                Log.e("dodormeur", t);
                JSONObject jObject = new JSONObject(t);
                if (t == null || jObject == null) return INTERNET_ERROR;
                else if (jObject.has("success")) {
                    Log.e("dodormeur", jObject.getInt("number") + "");
                    for (int i = 0; i < jObject.getInt("number"); i++) {
                        Filtre f = new Filtre();
                        Log.e("dodormeur", jObject.getInt("number") + "");
                        f.name = jObject.getString("name" + i);
                        Log.e("dodormeur", jObject.getInt("number") + "");
                        f.idFiltre = Integer.parseInt(jObject.getString("id" + i));
                        Log.e("dodormeur", jObject.getInt("number") + "");
                        f.loadData(jObject.getString("filtre" + i));
                        Log.e("dodormeur", jObject.getInt("number") + "");
                        baseActivity.mainUser.getFiltres().add(f);
                        Log.e("dodormeur", jObject.getInt("number") + "");
                        Log.e("dodormeur", "added filtre");
                        /*f.data = jObject.getString("data"+i);
                        f.name = jObject.getString("name"+i);*/
                    }
                    return 1;
                } else return INTERNET_ERROR;
            } catch (Exception e) {
                return INTERNET_ERROR;
            }
        } else return NO_INTERNET;
    }

    public static int sendMessage(String text, int id, Context ctx) {
        if (checkInternet(ctx)) {
            try {
                String[] act = new String[]{"action", "id", "getter", "text"};
                String[] arg = new String[]{"sendMessage", idMain + "", "" + id, text};
                String t = downloadUrl("http://dracognards.be/uclove/main.php", act, arg, false);
                Log.e("dodormeur", t);
                JSONObject jObject = new JSONObject(t);
                if (t == null || jObject == null) return INTERNET_ERROR;
                else if (jObject.has("success")) {
                    return 1;
                } else return INTERNET_ERROR;
            } catch (Exception e) {
                return INTERNET_ERROR;
            }
        } else return NO_INTERNET;
    }

    public static int setRelation(String asker, String receiver, String state, Context ctx) {
        if (checkInternet(ctx)) {
            try {
                String[] act = new String[]{"action", "asker", "receiver", "state"};
                String[] arg = new String[]{"setRelation", asker, receiver, state};
                String t = downloadUrl("http://dracognards.be/uclove/main.php", act, arg, false);
                Log.e("dodormeur", t);
                JSONObject jObject = new JSONObject(t);
                if (jObject == null || t == null) return INTERNET_ERROR;
                else if (jObject.has("success")) {
                    return OK;
                }
                return INTERNET_ERROR;
            } catch (Exception e) {
                return INTERNET_ERROR;
            }
        } else return NO_INTERNET;
    }

    public static int setFiltre(String id, String data, String name, Context ctx, boolean add) {
        if (checkInternet(ctx)) {
            try {
                String[] act;
                if (add) act = new String[]{"action", "owner", "data", "name"};
                else act = new String[]{"action", "owner", "id", "data", "name"};
                String[] arg;
                if (add) arg = new String[]{"setFiltre", idMain + "", data, name};
                else arg = new String[]{"setFiltre", idMain + "", id, data, name};
                String t = downloadUrl("http://dracognards.be/uclove/main.php", act, arg, false);
                Log.e("dodormeur", t);
                JSONObject jObject = new JSONObject(t);
                if (jObject == null || t == null) return INTERNET_ERROR;
                else if (jObject.has("success")) {
                    return OK;
                }
                return INTERNET_ERROR;
            } catch (Exception e) {
                return INTERNET_ERROR;
            }
        } else return NO_INTERNET;
    }

    public static int searchUser(String search, Context ctx) {
        if (checkInternet(ctx)) {
            try {
                String[] act = new String[]{"action", "idAsker", "data"};
                String[] arg = new String[]{"search", idMain + "", search};
                String t = downloadUrl("http://dracognards.be/uclove/main.php", act, arg, false);
                Log.e("dodormeur", t);
                JSONObject jObject = new JSONObject(t);
                if (jObject == null || t == null) return INTERNET_ERROR;
                else if (jObject.has("success")) {
                    return Integer.parseInt(jObject.getString("result"));
                }
                return INTERNET_ERROR;
            } catch (Exception e) {
                return INTERNET_ERROR;
            }
        } else return NO_INTERNET;
    }

    public static int reset(Context ctx) {

        Log.e("dodormeur", "reseting");
        if (checkInternet(ctx)) {
            try {
                Log.e("dodormeur", "reseting2");

                String t = downloadUrl("http://dracognards.be/uclove/init.php?mdp=wouldyourmombeproud", null, null, false);
                Log.e("dodormeur", "doneReseting");
                Log.e("dodormeur", t);
            } catch (Exception e) {
                return INTERNET_ERROR;
            }
        }
        return NO_INTERNET;
    }

    public static int setDispo(String time, Context ctx) {

        //12:00;18:00;15:00;1800
        //A verifier par Damien
        if (checkInternet(ctx)) {
            try {
                String act[] = new String[]{"action", "monday1", "monday2", "tuesday1", "tuesday2", "wednesday1", "wednesday2", "thursday1"
                        , "thursday2", "friday1", "friday2", "saturday1", "saturday2", "sunday1", "sunday2"};
                String arg[] = new String[]{"setDispo", time};
                String t = downloadUrl("http://dracognards.be/uclove/main.php", act, arg, false);
                Log.e("dodormeur", t);
                JSONObject jObject = new JSONObject(t);
                if (jObject == null || t == null) return INTERNET_ERROR;
                else if (jObject.has("success")) {
                    return OK;
                }
                return INTERNET_ERROR;
            } catch(Exception e) {
                return INTERNET_ERROR;
            }


        } else return NO_INTERNET;

    }
}
