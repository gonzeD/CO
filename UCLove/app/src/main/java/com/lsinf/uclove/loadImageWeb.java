package com.lsinf.uclove;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by damien on 30/04/16.
 * Permet de charger les photos des utilisateur. Il faut fournir une imageview, ainsi que le nom de l'image.
 */
    public class loadImageWeb extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public loadImageWeb(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                Log.e("dodormeur","fetching image "+"http://dracognards.be/uclove/pictures/"+urldisplay+".png");
                InputStream in = new java.net.URL("http://dracognards.be/uclove/pictures/"+urldisplay+".png").openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

