package com.jiaop.face;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


import org.json.JSONException;
import org.json.JSONObject;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.provider.MediaStore.Images.ImageColumns;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.BitmapFactory.Options;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etFirst;
    private Button btFirst;
    private EditText etSecond;
    private Button btSecond;
    private Button btDetect;
    private Bitmap img;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etFirst = (EditText) findViewById(R.id.etFirst);
        btFirst = (Button) findViewById(R.id.btFirst);
        etSecond = (EditText) findViewById(R.id.etSecond);
        btSecond = (Button) findViewById(R.id.btSecond);
        btDetect = (Button) findViewById(R.id.btDetect);
        tv=(TextView) findViewById(R.id.tv);
        btFirst.setOnClickListener(this);
        btSecond.setOnClickListener(this);
        btDetect.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        switch (v.getId()) {
            case R.id.btFirst:
                startActivityForResult(intent, 1);
                break;
            case R.id.btSecond:
                startActivityForResult(intent, 2);
                break;
            case R.id.btDetect:
                final String firstPath = etFirst.getText().toString();
                final String secondPath = etSecond.getText().toString();
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // 初始化
                        HttpRequests httpRequests = new HttpRequests("4e6fb5ed8effbd8503aea3ce3f44e0c3","i-iPbehwOCuLmEHODeEknGGiV_zxkfsp",true, true);

                        try {

                            //获取第一张图片的信息
                            byte[] array1 = imageProcessing("a.jpg");
                            JSONObject result1 = httpRequests.detectionDetect(new PostParameters().setImg(array1));
                            String face1 = result1.getJSONArray("face").getJSONObject(0).getString("face_id");
//                            System.out.println("face1的id=" + face1);
                            //获取第二张图片的信息
                            byte[] array2 = imageProcessing("d.jpg");
                            JSONObject result2 = httpRequests.detectionDetect(new PostParameters().setImg(array2));
                            String face2 = result2.getJSONArray("face").getJSONObject(0).getString("face_id");
//                            System.out.println("face2的id=" + face2);

//                            System.out.println("开始比较：");
                            //对比两张人脸的相似程度
                            JSONObject Compare = httpRequests.recognitionCompare(new PostParameters().setFaceId1(face1).setFaceId2(face2));
                            final Double smilar = Double.valueOf(Compare.getString("similarity"));


                            runOnUiThread(new Runnable() {
                                public void run() {
                                    tv.setText("相似度为：" + smilar);
                                }
                            });
                        } catch (NumberFormatException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (FaceppParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }
                }).start();

                break;

            default:
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Cursor cursor = getContentResolver().query(data.getData(), null,
                    null, null, null);
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(ImageColumns.DATA);
            String fileSrc = cursor.getString(idx);
            if (requestCode == 1) {
                etFirst.setText(fileSrc);

            } else if (requestCode == 2) {
                etSecond.setText(fileSrc);

            }
            cursor.close();

        }
    }

    // 处理图片
    private byte[] imageProcessing(final String Path) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
//        img = BitmapFactory.decodeFile(Path, options);
        img = getImageFromAssetsFile(Path);
        options.inSampleSize = Math.max(1, (int) Math.ceil(Math.max((double) options.outWidth / 1024f,(double) options.outHeight / 1024f)));

        options.inJustDecodeBounds = false;
//        img = BitmapFactory.decodeFile(Path, options);
        img = getImageFromAssetsFile(Path);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        float scale = Math.min(1,Math.min(600f / img.getWidth(), 600f / img.getHeight()));
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);

        Bitmap imgSmall = Bitmap.createBitmap(img, 0, 0, img.getWidth(),img.getHeight(), matrix, false);

        imgSmall.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        byte[] array = stream.toByteArray();
        return array;
    }

    private Bitmap getImageFromAssetsFile(String fileName)
    {
        Bitmap image = null;
        AssetManager am = getResources().getAssets();
        try
        {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return image;

    }
}
