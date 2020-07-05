package com.example.myapplication18.dj;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.Manifest;
import android.app.TabActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;

import com.example.myapplication18.R;

/**
 * 北斗星demo
 * @auther chenlf3
 * @date 2019年6月24日-下午12:22:01
 * Copyright (c) 2019点聚信息技术有限公司-版权所有
 */
@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
	String[] permissionparams = new String[]{"android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.ACCESS_NETWORK_STATE",
            "android.permission.READ_PHONE_STATE",
            "android.permission.CALL_PHONE",
            "android.permission.ACCESS_WIFI_STATE",
            "android.permission.RECORD_AUDIO",
            "android.permission.CAMERA",
            "android.permission.RECORD_AUDIO",
            "android.permission.INTERNET"};
	public static final String PROJECT_PATH = Environment.getExternalStorageDirectory()+File.separator+"dianju"+File.separator;
	public static final String FONTS_PATH = PROJECT_PATH+"fonts/";
	private TabSpec spec;
    private Intent intent;
    private TabHost tabHost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dj_main);
		if(Build.VERSION.SDK_INT>=23 && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(permissionparams, 1);
        } else{
            init();
        }
	}
	
	void init() {
		copy();
		tabHost = getTabHost();
		TabWidget tabWidget = tabHost.getTabWidget();
		
		intent = new Intent(this,CeActivity1.class);
		spec = tabHost.newTabSpec("正文").setIndicator("正文").setContent(intent);
		tabHost.addTab(spec);
		
		intent = new Intent(this,CeActivity2.class);
		spec = tabHost.newTabSpec("审批单").setIndicator("审批单").setContent(intent);
		tabHost.addTab(spec);
		
		intent = new Intent(this,CeActivity3.class);
		spec = tabHost.newTabSpec("文件三").setIndicator("文件三").setContent(intent);
		tabHost.addTab(spec);
		tabHost.setCurrentTab(0);
		/** 设置字体 */
	    for (int i =0; i < tabWidget.getChildCount(); i++) {
	    	TextView tv = (TextView) tabWidget.getChildAt(i).findViewById(android.R.id.title);
	    	tv.setTextSize(30);
	    	//tv.setTextColor(this.getResources().getColorStateList(android.R.color.white));
    	}
	}
	
	void copy() {
        /** 复制文件到SD卡 */
		try {
            ClfUtil.copyDJFileFromAssets(this, "dianju", PROJECT_PATH);
            //字体要删除后再安装
            File dir = new File(FONTS_PATH);
            if(dir.exists() && dir.isDirectory()) {
            	ClfUtil.delDir(dir);
            }
            ClfUtil.copyDJFileFromAssets(this, "fonts", FONTS_PATH);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Log.d("dianju", "获取assets资源错误！");
            return;
        }
    }
	
	public static boolean saveTo(InputStream in,String path) throws IOException {
		File file = new File(path);
		OutputStream os = new FileOutputStream(file);
		byte[] buffer = new byte[1024];
		int i = -1;
		while((i=in.read(buffer)) != -1) {
			os.write(buffer,0,i);
		}
		os.flush();
		in.close();
		os.close();
		return true;
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		// TODO Auto-generated method stub
		if(requestCode==1 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
            init();
        } else {
            finish();
        }
	}
}
