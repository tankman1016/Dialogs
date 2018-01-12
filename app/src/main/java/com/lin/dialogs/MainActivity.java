package com.lin.dialogs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener, CommonDialogFmt.OnCommonDialogSureListener {

    private Button btn1;
    private Button btn2;
    private Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                DialogHelper.showCommon(this, "btn1", "提示", "我是dialog1", "确定", "取消");
                break;
            case R.id.btn2:
                DialogHelper.showCommonWithOnlyMsg(this,"btn2","我是dialog2");
                break;
            case R.id.btn3:
                DialogHelper.showNotice(this);
                break;
        }
    }

    @Override
    public void onClick(String key) {
        switch (key) {
            case "btn1":
                Toast.makeText(MainActivity.this, "dialog1", Toast.LENGTH_SHORT).show();
                break;
            case "btn2":
                Toast.makeText(MainActivity.this, "dialog2", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(MainActivity.this, "nokey", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
