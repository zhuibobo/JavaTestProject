package com.example.myapplication18.dj;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dianju.showpdf.DJContentView;
import com.dianju.showpdf.DJContentView.NodeType;
import com.dianju.showpdf.DJContentView.OperType;
import com.example.myapplication18.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CeActivity2 extends Activity {
	private String filePath = Environment.getExternalStorageDirectory()+"/dianju/zw.aip";
	private LinearLayout contentLayout;
	private DJContentView contentView;
	private Button browse,publicSign,sign,comment,save;
	private boolean isListener;
	private LinearLayout editLayout;
	private EditText editArea;
	private Button btnOK,btnCancel;
	InputMethodManager imm;
	private boolean isCreateSignArea;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ce2);
		isListener = true;
		isCreateSignArea = false;
		initBtn();
		initEditMod();
		this.contentLayout = (LinearLayout) this.findViewById(R.id.contentLayout2);
		this.contentLayout.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
			
			@Override
			public boolean onPreDraw() {
				// TODO Auto-generated method stub
				if(isListener){
					isListener = false;
					contentView = new DJContentView(CeActivity2.this);
					contentView.setValue("SET_FONTFILES_PATH", MainActivity.FONTS_PATH);
					int res = -1;
					if(filePath.endsWith(".aip")) {
						contentView.openTempFile(filePath);
					} else {
						contentView.openFile(filePath);
					}
					contentView.setCurrAction(4);
					boolean login = login();
					if(!login) {
						contentView.saveFile("");
						contentView.disposeResource();
						contentView = null;
						Toast.makeText(CeActivity2.this, "登陆失败!", Toast.LENGTH_LONG).show();
						return true;
					}
					contentView.setPenAttr(25, Color.BLACK);
					contentView.setOnTouchListener(new OnTouchListener() {
						@Override
						public boolean onTouch(View view, MotionEvent event) {
							contentView.onTouchEvent(event);
							externalEvent(event);
							return true;
						}
					});
					contentLayout.addView(contentView);
				}
				return true;
			}
		});
		
		
	}
	
	void initBtn() {//private Button browse,publicSign,sign,comment,save;
		this.browse = (Button) this.findViewById(R.id.browse);
		this.publicSign = (Button) this.findViewById(R.id.publicSign);
		this.sign = (Button) this.findViewById(R.id.sign);
		this.comment = (Button) this.findViewById(R.id.comment);
		this.save = (Button) this.findViewById(R.id.save);
		
		this.browse.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(contentView==null) return;
				contentView.setCurrAction(OperType.NONE);
			}
		});
		
		this.publicSign.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(contentView==null) return;
				contentView.setCurrAction(OperType.WRITE);
			}
		});
		
		this.sign.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(contentView==null) return;
				Intent intent = new Intent();
				intent.setClass(CeActivity2.this, PopWriteActivity.class);
				startActivityForResult(intent,5);
			}
		});

		this.comment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(contentView==null) return;
				contentView.setCurrAction(OperType.TEXT);
			}
		});
		
		this.save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(contentView==null) return;
				saveTo(contentView.getFile(), filePath);
				Toast.makeText(CeActivity2.this, "保存成功!", Toast.LENGTH_LONG).show();
			}
		});
	}
	
	void initEditMod() {
		imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
		editLayout = (LinearLayout) this.findViewById(R.id.editLayout);
		this.editArea = (EditText) this.findViewById(R.id.editArea);
		btnOK = (Button) this.findViewById(R.id.btn_ok);
		btnCancel = (Button) this.findViewById(R.id.btn_cancel);
		
		btnOK.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(contentView.getCurrAction()==OperType.TEXT) {//批注(插入)
					String nodename = getRandName(NodeType.Head.EDIT_TEXT_EX);
					int res = contentView.insertNote(nodename, NodeType.EDIT_TEXT_EX,contentView.getClickPage(), contentView.getClickNodeX(), contentView.getClickNodeY(), 49000-contentView.getClickNodeX(), 2000);
					contentView.setValue(nodename,":PROP:FACENAME:仿宋_GB2312");
					contentView.setValue(nodename, ":PROP:FONTSIZE:16");
					contentView.setValue(nodename, ":PROP:FONTBOLD:1");
					res = contentView.setValue(nodename, editArea.getText().toString());
				}
				editArea.setText("");
				editLayout.setVisibility(View.GONE);
				imm.hideSoftInputFromWindow(editArea.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				contentView.freshPDF();
				//编辑完毕需解锁屏幕
				contentView.unLockScreen();
			}
		});
		/** 文字-取消按钮 */
		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				editArea.setText("");
				editLayout.setVisibility(View.GONE);
				//imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				imm.hideSoftInputFromWindow(editArea.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				contentView.setCurrAction(0);
				//编辑完毕需解锁屏幕
				contentView.unLockScreen();
			}
		});
	}
	
	void externalEvent(MotionEvent event) {
		if((!contentView.isLockScreen()) && ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP)) {
			if(contentView.getCurrAction() == OperType.TEXT && (!contentView.isUseDefaultPopView())) {
				editArea.setText("");
				editLayout.setVisibility(View.VISIBLE);
				editArea.requestFocus();
				imm.showSoftInput(editArea, InputMethodManager.HIDE_NOT_ALWAYS);
				//弹编辑框需锁屏
				contentView.lockScreen();
			} else if(contentView.getCurrAction() == OperType.EDITTEXT && (!contentView.isUseDefaultPopView()) && (!TextUtils.isEmpty(contentView.currNodeName))) {
				String content = contentView.getValueEx(contentView.currNodeName, 2, "", 0, "");
				/** 获取点击文本控件距离父容器左边顶部的距离(页面坐标) */
				editArea.setText(content);
				editLayout.setVisibility(View.VISIBLE);
				imm.showSoftInput(editArea, InputMethodManager.HIDE_NOT_ALWAYS);
				//弹编辑框需锁屏
				contentView.lockScreen();
			}
		}
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch(requestCode){
		case 5://弹出手写窗体返回
			if(resultCode==1){//确定按钮
				if(contentView == null) return;
				String writeData=data.getStringExtra("writeData");
				String signArea = "signArea";//自定义名字
				if(!isCreateSignArea) {
					isCreateSignArea = true;
					contentView.insertNoteByWH(signArea, NodeType.HAND, 0, 28500, 30300, 16400, 4500);
					contentView.setValue(signArea, ":PROP:HWCHARFORMAT:100,300,300");
				} else {
					contentView.setValue(signArea, "");
				}
				contentView.pasteNodesToArea(signArea, writeData);
				contentView.freshPDF();
			}
			break;
		}
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		if(contentView != null) {
			contentView.setVisibility(View.VISIBLE);
		}
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if(contentView != null) {
			contentView.setVisibility(View.GONE);
		}
		super.onPause();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if(contentView != null) {
			Log.d("chenlongfei", "close2");
			contentView.saveFile("");
			contentView.disposeResource();
			contentView = null;
		}
		super.onDestroy();
	}
	
	public boolean login() {
		//此处缺省授权代码getLicOnline，因为使用类型4-测试用户，不需要授权(测试用户包含HWSEALDEMO、HWSEALDEMO1、HWSEALDEMO2)
		//String verifyRes = contentView.getLicOnline("http://www.dianju.cn:9239", "androidceshi004");
		int res = contentView.login("HWSEALDEMO", 4, "");//测试用户
		if(res==1) return true;
		return false;
	}
	
	public static boolean saveTo(byte[] data, String path) {
		try {
			File file = new File(path);
			FileOutputStream out = new FileOutputStream(file);
			out.write(data);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//获取一个随机字符串
	String getRandName(String head) {
		Date d = new Date();
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String nodename = head + format.format(d);
		return nodename;
	}
}
