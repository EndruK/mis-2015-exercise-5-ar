package com.qualcomm.vuforia.samples.VuforiaSamples.app.ImageTargets;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.webkit.WebView;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by andre on 6/30/15.
 */
public class WebTexture {
    private static Bitmap[] webImages;
    public WebTexture() {
        webImages = new Bitmap[1];
    }
    private class WebHandler extends AsyncTask<String, Void, String> {
        private String load(String input) throws IOException {
            //TODO: load the image
            HttpClient c = new DefaultHttpClient();
            HttpResponse resp = c.execute(new HttpGet(input));
            StatusLine status = resp.getStatusLine();
            if(status.getStatusCode() == HttpStatus.SC_OK) {
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                resp.getEntity().writeTo(outStream);
                String respAsString = outStream.toString();
                return respAsString;
            }
            else {
                resp.getEntity().getContent().close();
                String test = "ERROR: " + Integer.toString(status.getStatusCode()) + " " + status.getReasonPhrase();
                throw  new IOException(test);
            }
        }
        @Override
        public String doInBackground(String... input) {
            try {
                return load(input[0]);
            } catch (IOException e) {
                return e.getMessage();
            }
        }
    }

    public void loadWebTexture(WebView v) {
        //load textures

        webImages[0] = renderBitmap(v);

        /*WebHandler handler = new WebHandler();
        try {
            String url = "http://www.uni-weimar.de/uploads/pics/bertel_web.jpg";
            //String r015 = handler.execute(url).get();
            Bitmap b = renderBitmap(v,url);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        /*Image r015 = new Image();
        Image r014 = new Image();
        Image r013 = new Image();
        Image hk7  = new Image();
        //r015.setURL("http://www.uni-weimar.de/qisserver/rds?state=wplan&act=Raum&pool=Raum&show=plan&P.vx=kurz&raum.rgid=3218");
        r015.setURL("http://www.uni-weimar.de/uploads/pics/bertel_web.jpg");
        //r014.setURL("http://www.uni-weimar.de/qisserver/rds?state=wplan&act=Raum&pool=Raum&show=plan&P.vx=kurz&raum.rgid=3217");
        //hk7.setURL("http://www.uni-weimar.de/qisserver/rds?state=wplan&act=Raum&pool=Raum&show=plan&P.vx=kurz&raum.rgid=2947");
        //r013.setURL("http://www.uni-weimar.de/qisserver/rds?state=wplan&act=Raum&pool=Raum&show=plan&P.vx=kurz&raum.rgid=3216");
        r015.execute();
        //r014.execute();
        //hk7.execute();
        //r013.execute();
        try {
            //String output = r015.get();
            webImages[0] = renderBitmap(v,"http://www.uni-weimar.de/uploads/pics/bertel_web.jpg");
            //webImages[1] = r014.get();
            //webImages[2] = hk7.get();
            //webImages[3] = r013.get();
            System.out.println("all images loaded");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("failed to load images");
        }*/
    }
    public Bitmap[] getTextures() {
        return webImages;
    }
    public static int getTextureIndex(String imageID) {
        int offset = 2;
        if(imageID.equals("B11_R015"))
            return offset+1;
        else if(imageID.equals("B11_R014"))
            return offset+2;
        else if(imageID.equals("HK7_HS"))
            return offset+3;
        else if(imageID.equals("B11_R013"))
            return offset+4;
        else if(imageID.equals(""))
            return offset+0;
        return 0;
    }
    private static Bitmap renderBitmap(WebView v) {
        Bitmap b = Bitmap.createBitmap(v.getLayoutParams().width,v.getLayoutParams().height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
        v.draw(c);
        return b;
    }
}
