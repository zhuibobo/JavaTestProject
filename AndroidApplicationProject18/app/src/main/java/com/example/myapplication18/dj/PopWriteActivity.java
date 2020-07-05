package com.example.myapplication18.dj;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dianju.showpdf.DJContentView;
import com.example.myapplication18.R;

/**
 * 简化版弹出手写框
 * @auther chenlf3
 * @date 2017年2月10日-上午11:41:56
 * Copyright (c) 2017点聚信息技术有限公司-版权所有
 */
public class PopWriteActivity extends Activity {
	private Context context;
	private LinearLayout popLayout;
	private DJContentView popContentView;
	private boolean isListener;
	private Button btnCancel,btnClean,btnOK;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.context = this;
		isListener = true;
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去标题
		this.setFinishOnTouchOutside(false);//触屏不会关闭
		/** 打开但是不获取焦点 */
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
		setContentView(R.layout.popwrite);
		btnCancel = (Button) this.findViewById(R.id.btnCancel);
		btnClean = (Button) this.findViewById(R.id.btnClean);
		btnOK = (Button) this.findViewById(R.id.btnOK);
		popLayout = (LinearLayout) this.findViewById(R.id.popLayout);
		
		/** 加载手写板 */
		popLayout.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
			@Override
			public boolean onPreDraw() {
				// TODO Auto-generated method stub
				if(isListener) {
					isListener = false;
					popContentView = new DJContentView(context, true, popLayout.getWidth(), popLayout.getHeight(), 0);
					boolean login = login();
					if(!login) {
						popContentView.saveFile("");
						popContentView.disposeResource();
						popContentView = null;
						Toast.makeText(PopWriteActivity.this, "登陆失败!", Toast.LENGTH_LONG).show();
						return true;
					}
					popLayout.addView(popContentView);
				}
				return true;
			}
		});
		
		
		btnOK.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String writeData = popContentView.getNodes();
				Intent data = new Intent();
				data.putExtra("writeData", writeData);
				setResult(1, data);
				finish();
			}
		});
		
		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		btnClean.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				popContentView.undoAll(true);
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if(popContentView!=null) {
			popContentView.saveFile("");
			popContentView.disposeResource();
			popContentView = null;
		}
		super.onDestroy();
	}
	
	public boolean login() {
		int res = popContentView.login("HWSEALDEMO", 4, "");//测试用户
		if(res==1) return true;
		return false;
	}
}
