package com.qualcomm.vuforia.samples.VuforiaSamples.app.ImageTargets;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by andre on 6/30/15.
 */
public class WebTexture {
    private Bitmap[] webImages;
    int index;
    public WebTexture() {
        webImages = new Bitmap[5];
        index = 0;
    }
    private class GetImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        public Bitmap doInBackground(String... in) {
            try {
                URL url = new URL(in[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.connect();
                InputStream stream = con.getInputStream();
                Bitmap b = BitmapFactory.decodeStream(stream);
                return b;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    };

    public void loadWebTexture(String url) {
        //load textures
        try {
            GetImage handler = new GetImage();
            Bitmap b = handler.execute(url).get();
            webImages[index] = b;
            index++;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public Bitmap[] getTextures() {
        return webImages;
    }
    public static int getTextureIndex(String imageID) {
        int offset = 2;
        if(imageID.equals("bertel"))
            return offset+1;
        else if(imageID.equals("jakoby"))
            return offset+2;
        else if(imageID.equals("schatter"))
            return offset+3;
        else if(imageID.equals("echtler"))
            return offset+4;
        else if(imageID.equals("stein"))
            return offset+5;
        return 0;
    }
}
