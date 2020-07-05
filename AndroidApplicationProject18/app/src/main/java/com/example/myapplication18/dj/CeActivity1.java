package com.example.myapplication18.dj;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dianju.showpdf.DJContentView;
import com.dianju.showpdf.DJContentView.NodeType;
import com.dianju.showpdf.DJContentView.OperType;
import com.example.myapplication18.R;

public class CeActivity1 extends Activity {
	private String filePath = Environment.getExternalStorageDirectory()+"/dianju/1.pdf";
	private LinearLayout contentLayout;
	private DJContentView contentView;
	private boolean isListener;
	private Button browse,addseal,wenzi,edit,btnfont,save;
	private LinearLayout editLayout;
	private EditText editArea;
	private Button btnOK,btnCancel;
	private Button btn21,btn22,btn23,btn24;
	InputMethodManager imm;
	private int fontsize = 14;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ce1);
		isListener = true;
		initBtn();
		initEditMod();
		this.contentLayout = (LinearLayout) this.findViewById(R.id.contentLayout1);
		this.contentLayout.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
			
			@Override
			public boolean onPreDraw() {
				// TODO Auto-generated method stub
				if(isListener){
					isListener = false;
					contentView = new DJContentView(CeActivity1.this);
					//设置字体
					contentView.setValue("SET_FONTFILES_PATH", MainActivity.FONTS_PATH);
					//盖章设置(在印章下方带上日期)
					contentView.setValue("ADD_FORCETYPE_VALUE",""+(8*16*16*16));
					//签名紧跟意见尾行的位置
					contentView.setValue("ADD_FORCETYPE_VALUE6",""+(4*16));
					//附加用户的时间信息用小字显示
					contentView.setValue("ADD_FORCETYPE_VALUE5",""+(16*16));
					int res = -1;
					if(filePath.endsWith(".aip")) {
						res = contentView.openTempFile(filePath);
					} else {
						res = contentView.openFile(filePath);
					}
					boolean login = login();
					if(!login) {
						contentView.saveFile("");
						contentView.disposeResource();
						contentView = null;
						Toast.makeText(CeActivity1.this, "登陆失败!", Toast.LENGTH_LONG).show();
						return true;
					}
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
	
	void initBtn() {//private Button browse,publicSign,comment,save;
		this.browse = (Button) this.findViewById(R.id.browse);
		this.addseal = (Button) this.findViewById(R.id.addseal);
		this.wenzi = (Button) this.findViewById(R.id.wenzi);
		this.edit = (Button) this.findViewById(R.id.edit);
		this.btnfont = (Button) this.findViewById(R.id.btnfont);
		this.save = (Button) this.findViewById(R.id.save);
		this.btn21 = (Button) this.findViewById(R.id.btn21);
		this.btn22 = (Button) this.findViewById(R.id.btn22);
		this.btn23 = (Button) this.findViewById(R.id.btn23);
		this.btn24 = (Button) this.findViewById(R.id.btn24);
		
		this.browse.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(contentView==null) return;
				contentView.setCurrAction(OperType.NONE);
			}
		});
		
		//测试盖章
		this.addseal.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(contentView==null) return;
				String sealPath = Environment.getExternalStorageDirectory()+"/dianju/djtest2.sel";
				//String sealPath = Environment.getExternalStorageDirectory()+"/dianju/2019061516563834.sel";
				int a = contentView.addSeal(sealPath, "", "", 0);
				Log.d("dianju", "a="+a);
			}
		});
		
		this.wenzi.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(contentView==null) return;
				contentView.setCurrAction(OperType.AUTO_HANDLE);
			}
		});

		//测试录入文字+后缀签名
		this.edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(contentView==null) return;
				contentView.setCurrAction(OperType.EDITTEXT);
				
				//设置aip模板上的某些节点可编辑
				List<String> list = new ArrayList<String>();
				list.add("AT_T0_17_1T");//添加使用条款为可编辑节点
				contentView.setEditNodes(list);
			}
		});
		
		//测试录入文字+后缀签名
		this.btnfont.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				fontsize = 30;
				Toast.makeText(CeActivity1.this, "字号已设置为30", Toast.LENGTH_LONG).show();
			}
		});
		
		this.save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(contentView==null) return;
				//saveTo(contentView.getTempFile(), filePath);
				int res = contentView.saveFileEx(filePath, 0);//0-保存并且不关闭打开的文件
				contentView.saveFileEx(Environment.getExternalStorageDirectory()+"/dianju/saveas.pdf", 0);//0-保存并且不关闭打开的文件
				if(res == 1) {
					Toast.makeText(CeActivity1.this, "保存成功!"+filePath, Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(CeActivity1.this, "保存失败!", Toast.LENGTH_LONG).show();
				}
				
			}
		});
		
		this.btn21.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				contentView.moveNode(contentView.currNodeName, -5, 0);
				contentView.freshPDF();
			}
		});
		
		this.btn22.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				contentView.moveNode(contentView.currNodeName, 5, 0);
				contentView.freshPDF();
			}
		});

		this.btn23.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				contentView.moveNode(contentView.currNodeName, 0, -5);
				contentView.freshPDF();
			}
		});
		
		this.btn24.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				contentView.moveNode(contentView.currNodeName, 0, 5);
				contentView.freshPDF();
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
				//新增和修改都是走该方法
				String content = editArea.getText().toString();
				/*if(contentView.getCurrAction()==OperType.EDITTEXT) {//编辑状态下需要加上旧数据
					String oldContent = contentView.getValueEx(contentView.currNodeName, 2, "", 0, "");
					if(oldContent == null || oldContent.startsWith("errorcode")) {//空数据
					} else {
						content = oldContent+content;
					}
				}*/
				contentView.setValue(contentView.currNodeName, content);
				editArea.setText("");
				editLayout.setVisibility(View.GONE);
				imm.hideSoftInputFromWindow(editArea.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				contentView.setDrawColorNode("");
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
				contentView.setDrawColorNode("");
				if(contentView.getCurrAction()==OperType.AUTO_HANDLE) {//新增
					contentView.delNode(contentView.currNodeName);
				}
				contentView.freshPDF();
				//编辑完毕需解锁屏幕
				contentView.unLockScreen();
			}
		});
	}
	
	void externalEvent(MotionEvent event) {
		if((!contentView.isLockScreen()) && ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP)) {
			if(contentView.getCurrAction() == OperType.AUTO_HANDLE) {//批注弹文字采集框
				//插入一个文本节点，并带颜色显示
				String nodename = ""+System.currentTimeMillis();
				int page = contentView.getClickPage();
				int x = contentView.getClickNodeX();
				int y = contentView.getClickNodeY();
				int w = contentView.getClickNodeWidth();
				int h = contentView.getClickNodeHeight();
				//定义默认宽度(当宽度小于8000时，设置为最大宽度，包含点击情况下的宽度)
				if(w<8000) {
					w = 8000;
				}
				if(h<2000) {
					h = 2000;
				}
				int res = contentView.insertNoteByWH(nodename, NodeType.EDIT_TEXT_EX, page, x, y, w, h);
				contentView.setValue(nodename,":PROP:FACENAME:仿宋_GB2312");
				contentView.setValue(nodename, ":PROP:FONTSIZE:"+fontsize);
				contentView.setValue(nodename, ":PROP:FONTBOLD:1");
				contentView.setValue(nodename,":PROP:FREESIZE:2");//设置纵向和横向扩展
				contentView.currNodeName = nodename;
				contentView.setDrawColorNode(nodename);
				contentView.freshPDF();
				
				//弹出文字采集框
				//将页面坐标转换为屏幕坐标
				float[] lt = contentView.getScreenXYFormPageXY(page, x, y);//获取框左上角xy屏幕坐标
				float[] rb = contentView.getScreenXYFormPageXY(page, x+w, y+h);//获取框右下角xy屏幕坐标
				//定位layout出现在框上面
				editLayout.setX(lt[0]);
				editLayout.setY(lt[1]);
				editLayout.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				//设置android文字框大小跟aip文件上文字节点大小相同
				editArea.setLayoutParams(new LinearLayout.LayoutParams((int)(rb[0]-lt[0]), (int)(rb[1]-lt[1])));
				
				editArea.setText("");
				editLayout.setVisibility(View.VISIBLE);
				editArea.requestFocus();
				imm.showSoftInput(editArea, InputMethodManager.HIDE_NOT_ALWAYS);
				//弹编辑框需锁屏
				contentView.lockScreen();
			} else if(contentView.getCurrAction() == OperType.EDITTEXT && (!TextUtils.isEmpty(contentView.currNodeName))) {
				//节点带颜色显示
				contentView.setDrawColorNode(contentView.currNodeName);
				contentView.freshPDF();
				
				//获取框的屏幕坐标数据(EDITTEXT编辑状态下下面方法返回的已是屏幕坐标)
				int page = contentView.getClickPage();
				int x = contentView.getClickNodeX();
				int y = contentView.getClickNodeY();
				int w = contentView.getClickNodeWidth();
				int h = contentView.getClickNodeHeight();
				//定位layout出现在框上面
				editLayout.setX(x);
				editLayout.setY(y);
				editLayout.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
				//设置android文字框大小跟aip文件上文字节点大小相同
				editArea.setLayoutParams(new LinearLayout.LayoutParams(w, h));
				
				String content = contentView.getValueEx(contentView.currNodeName, 2, "", 0, "");
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
				String signArea = "sign";//模板中定义名字
				contentView.setValue(signArea, "");
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
			contentView.saveFile("");
			contentView.disposeResource();
			contentView = null;
		}
		super.onDestroy();
	}
	
	public boolean login() {
		//此处缺省授权代码getLicOnline，因为使用类型4-测试用户，不需要授权(测试用户包含HWSEALDEMO、HWSEALDEMO1、HWSEALDEMO2)
		//String verifyRes = contentView.getLicOnline("http://www.dianju.cn:9239", "androidceshi004");
		contentView.verifyLic("FeL1sPWmIiBdWEUFMGb3rOrznVfhjPMS");//试用授权
		int res = contentView.login("lzw", 2, "");
		//为该用户的文字内容设置签名图片
		String picPath = Environment.getExternalStorageDirectory()+"/dianju/djtest2.sel";
		contentView.setUserInfoEx("lzw", 0, 7, picPath);
		contentView.setUserInfoEx("lzw", 0, 8, ""+(13*16));
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
