package com.jiaop.picasso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.image);
    }

    /**
     * 详见http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2014/0731/1639.html
     * 支持加载本地图片和sdcard中的图片文件等
     * Picasso.with(context).load(R.drawable.landing_screen).into(imageView1);
     * Picasso.with(context).load(new File(...)).into(imageView2);
     */
    private void howUse() {
        Picasso.with(MainActivity.this).load("http://i.imgur.com/DvpvklR.png").into(image);
        //图像处理
        Picasso.with(MainActivity.this)
                .load("http://i.imgur.com/DvpvklR.png")
                .resize(50, 50)
                .centerCrop()
                .into(image);
    }

}
