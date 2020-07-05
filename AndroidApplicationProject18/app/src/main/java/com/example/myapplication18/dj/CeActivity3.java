package com.example.myapplication18.dj;

import com.dianju.showpdf.DJContentView;
import com.example.myapplication18.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.LinearLayout;

public class CeActivity3 extends Activity {
	private String filePath = Environment.getExternalStorageDirectory()+"/dianju/hyjl.pdf";
	private LinearLayout contentLayout;
	private DJContentView contentView;
	private boolean isListener;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ce3);
		isListener = true;
		this.contentLayout = (LinearLayout) this.findViewById(R.id.contentLayout3);
		this.contentLayout.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
			
			@Override
			public boolean onPreDraw() {
				// TODO Auto-generated method stub
				if(isListener){
					isListener = false;
					contentView = new DJContentView(CeActivity3.this);
					int res = -1;
					if(filePath.endsWith(".aip")) {
						contentView.openTempFile(filePath);
					} else {
						contentView.openFile(filePath);
					}
					contentLayout.addView(contentView);
				}
				return true;
			}
		});
		
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

}
