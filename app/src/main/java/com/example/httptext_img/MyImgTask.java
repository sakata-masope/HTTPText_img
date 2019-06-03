package com.example.httptext_img;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyImgTask extends AsyncTask<String ,Void,Bitmap>
{

    private Listener listener;

    ImageView httpimg;



    @Override
    protected Bitmap doInBackground(String... params)
    {
        Bitmap bmp =null;
        InputStream inputStream=null;

        // final StringBuilder result = new StringBuilder();

        HttpURLConnection connection=null;

        try
        {
            URL url = new URL(params[0]);

            connection=(HttpURLConnection)url.openConnection();

            connection.setReadTimeout(3000);
            connection.setConnectTimeout(3000);

            connection.setRequestMethod("GET");

            connection.setInstanceFollowRedirects(false);

            connection.connect();

            int resp=connection.getResponseCode();

            if(resp!=HttpURLConnection.HTTP_OK)
            {
                throw new IOException("Http Response code:"+resp);
            }
            inputStream=connection.getInputStream();
            if(inputStream!=null) {
                bmp = BitmapFactory.decodeStream(inputStream);
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }finally {
            if(inputStream!=null)
            {
                try{
                    inputStream.close();
                }catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
            if(connection!=null)
            {
                connection.disconnect();
            }
        }

        return bmp;
    }
    @Override
    protected void onPostExecute(Bitmap bmp)
    {
        if(listener != null)
        {
            listener.onSuccess(bmp);
        }

    }
    void setListener(Listener listener)
    {
        this.listener=listener;
    }
    interface Listener{
        void onSuccess(Bitmap bmp);
    }

}
