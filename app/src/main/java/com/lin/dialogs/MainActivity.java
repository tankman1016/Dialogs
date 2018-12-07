package com.lin.dialogs;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lin.dialogs.listener.OnCancelListener;
import com.lin.dialogs.listener.OnSureListener;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {

    private Button btn1;
    private Button btn2;
    private Button btn3;

    AlertDialog alertDialog;
    CommonDialog commonDialog;

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

                CommonDialog.Builder builder = new CommonDialog.Builder()
                        .title("对画框1")
                        .setCancelable(false)
                        .setMsg("你好a")
                        .setSureText("确定文字")
                        .setOnSureListener(new OnSureListener() {
                            @Override
                            public void onSure() {
                                //当前能够点击 说明alertDialog不为空且正常显示
                                Toast.makeText(MainActivity.this, "您点击了确定", Toast.LENGTH_SHORT).show();
                                commonDialog.dismiss();
                            }
                        })
                        .setCancelText("取消文字")
                        .setOnCancelListener(new OnCancelListener() {
                            @Override
                            public void onCancel() {
                                Toast.makeText(MainActivity.this, "您点击了取消", Toast.LENGTH_SHORT).show();
                            }
                        });
                commonDialog = builder.create();
                commonDialog.show(getFragmentManager(), "一般对话框");

                break;
            case R.id.btn2:

                alertDialog = new AlertDialog.Builder(this)
                        .setTitle("你好")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //当前能够点击 说明alertDialog不为空且正常显示
                                Toast.makeText(MainActivity.this, "您点击了确定", Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();
                            }
                        }).create();
                alertDialog.show();

                break;
            case R.id.btn3:

                break;
        }
    }

}
