package com.jiaop.signaturepad;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.gcacace.signaturepad.views.SignaturePad;

/**
 * 手写触控例子
 * 图片类型转换详见
 * https://github.com/gcacace/android-signaturepad/blob/master/SignaturePad-Example/src/main/java/com/github/gcacace/signaturepad/MainActivity.java
 */
public class MainActivity extends AppCompatActivity {

    private SignaturePad signaturePad;//手写板
    private ImageView imgShow;//保存后展示板
    private Button clear;//清空手写板
    private Button save;//保存手写板

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signaturePad = (SignaturePad) findViewById(R.id.signature_pad);
        imgShow = (ImageView) findViewById(R.id.imgShow);
        clear = (Button) findViewById(R.id.clear);
        save = (Button) findViewById(R.id.save);

        /**
         * 手写板监听事件
         */
        signaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            /**
             * 开始进行手写
             */
            @Override
            public void onStartSigning() {
                Toast.makeText(MainActivity.this, "OnStartSigning", Toast.LENGTH_SHORT).show();
            }

            /**
             * 手写过程中
             */
            @Override
            public void onSigned() {
                clear.setEnabled(true);
                save.setEnabled(true);
            }

            /**
             * 手写板空白时
             */
            @Override
            public void onClear() {
                clear.setEnabled(false);
                save.setEnabled(false);
            }
        });

        /**
         * 清空手写板
         */
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //手写板清空
                signaturePad.clear();
            }
        });

        /**
         * 保存手写板
         */
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取手写板的图片
                Bitmap signatureBitmap = signaturePad.getSignatureBitmap();
                //在图片展示板展示保存的图片
                imgShow.setImageBitmap(signatureBitmap);
            }
        });

    }
}
