package com.jiaop.client;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Setting extends AppCompatActivity {

    private Button btnsave;
    private Button btn_cancel;
    private EditText edtip;
    private EditText edtport;
    SharedPreferences sp;
    private String TAG="=Setting=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        btnsave = (Button) findViewById(R.id.button1);
        btn_cancel=(Button) findViewById(R.id.button2);
        edtip = (EditText) findViewById(R.id.editText1);
        edtport = (EditText) findViewById(R.id.editText2);
        sp = this.getSharedPreferences("SP", MODE_PRIVATE);
        edtip.setText(sp.getString("ipstr", ""));
        edtport.setText(sp.getString("port", ""));
        btn_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();

            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"开始修改");
                String ip = edtip.getText().toString();//
                String port = edtport.getText().toString();//
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("ipstr", ip);
                editor.putString("port", port);
                editor.commit();//保存新数据
                Log.i(TAG, "保存成功"+sp.getString("ipstr", "")+";"+sp.getString("port", ""));
                finish();

            }
        });
    }
}
