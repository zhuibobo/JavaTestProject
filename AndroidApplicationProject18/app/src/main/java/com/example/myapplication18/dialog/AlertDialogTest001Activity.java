package com.example.myapplication18.dialog;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication18.R;

public class AlertDialogTest001Activity extends AppCompatActivity {

    private AlertDialog alert = null;
    private AlertDialog.Builder builder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog_test001);

        Button button1=findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert = null;
                builder = new AlertDialog.Builder(AlertDialogTest001Activity.this);
                alert = builder.setIcon(R.mipmap.images8)
                        .setTitle("系统提示：")
                        .setMessage("这是一个最普通的AlertDialog,\n带有三个按钮，分别是取消，中立和确定")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(AlertDialogTest001Activity.this, "你点击了取消按钮~", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(AlertDialogTest001Activity.this, "你点击了确定按钮~", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButton("中立", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(AlertDialogTest001Activity.this, "你点击了中立按钮~", Toast.LENGTH_SHORT).show();
                            }
                        }).create();//创建AlertDialog对象
                alert.show();//显示对话框
            }
        });

        Button button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] lesson = new String[]{"语文", "数学", "英语", "化学", "生物", "物理", "体育"};
                alert = null;
                builder = new AlertDialog.Builder(AlertDialogTest001Activity.this);
                alert = builder.setIcon(R.mipmap.ic_launcher)
                        .setTitle("选择你喜欢的课程")
                        .setItems(lesson, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "你选择了" + lesson[which], Toast.LENGTH_SHORT).show();
                            }
                        }).create();
                alert.show();
            }
        });

        Button button3=findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] fruits = new String[]{"苹果", "雪梨", "香蕉", "葡萄", "西瓜"};
                alert = null;
                builder = new AlertDialog.Builder(AlertDialogTest001Activity.this);
                alert = builder.setIcon(R.mipmap.ic_launcher)
                        .setTitle("选择你喜欢的水果，只能选一个哦~")
                        .setSingleChoiceItems(fruits, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "你选择了" + fruits[which], Toast.LENGTH_SHORT).show();
                            }
                        }).create();
                alert.show();
            }
        });

        Button button4=findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] menu = new String[]{"水煮豆腐", "萝卜牛腩", "酱油鸡", "胡椒猪肚鸡"};
                //定义一个用来记录个列表项状态的boolean数组
                boolean[] checkItems = new boolean[]{false, false, false, false};
                alert = null;
                builder = new AlertDialog.Builder(AlertDialogTest001Activity.this);
                alert = builder.setIcon(R.mipmap.ic_launcher)
                        .setMultiChoiceItems(menu, checkItems, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                checkItems[which] = isChecked;
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String result = "";
                                for (int i = 0; i < checkItems.length; i++) {
                                    if (checkItems[i])
                                        result += menu[i] + " ";
                                }
                                Toast.makeText(getApplicationContext(), "客官你点了:" + result, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                alert.show();
            }
        });

        Button button5=findViewById(R.id.button5);
        button5.setOnClickListener(v -> {

            AlertDialog.Builder builder = null;

            builder = new AlertDialog.Builder(AlertDialogTest001Activity.this).setIcon(R.mipmap.images8)
                    .setTitle("系统提示：")
                    .setMessage("这是一个最普通的AlertDialog,\n带有三个按钮，分别是取消，中立和确定");
            //加载自定义的那个View,同时设置下
            final LayoutInflater inflater = AlertDialogTest001Activity.this.getLayoutInflater();
            View  view_custom = inflater.inflate(R.layout.activity_cust_dialog_test001, null,false);
            builder.setView(view_custom);
            builder.setCancelable(false);
            alert = builder.create();
            alert.show();

            Button button8=view_custom.findViewById(R.id.button8);
            button8.setOnClickListener(v1 ->{
                Toast.makeText(getApplicationContext(), "客官你点了", Toast.LENGTH_SHORT).show();
                alert.dismiss();
            });
        });
    }


}
