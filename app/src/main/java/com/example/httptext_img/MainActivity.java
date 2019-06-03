package com.example.httptext_img;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView httpImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        httpImg=findViewById(R.id.myImageView);

        //HTTPによる画像取得
        MyImgTask imgTask=new MyImgTask();
        imgTask.setListener(createListener());
        imgTask.execute("http://www.bmcomp.jp/images/yagi.jpg");


    }

    private MyImgTask.Listener createListener()
    {
        return new MyImgTask.Listener() {
            @Override
            public void onSuccess(Bitmap bmp) {

                httpImg.setImageBitmap(bmp);

            }
        };
    }
}
