package com.finch.facelovely;

import com.finch.facelovely.res.MyRes;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;

public class LogoActivity extends Activity implements OnClickListener {

	private Button btn_boy,btn_girl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logo);
		btn_boy = (Button) findViewById(R.id.boy);
		btn_girl = (Button) findViewById(R.id.girl);
		btn_boy.setOnClickListener(this);
		btn_girl.setOnClickListener(this);
		initView();
	}
	
	
	/**
	 * 男生头像从左至右移动效果
	 */
	public void initView() {
		TranslateAnimation animation = new TranslateAnimation(-100f, 0, 0, 0);
		animation.setDuration(2000);
		animation.setFillAfter(true);
		btn_boy.startAnimation(animation);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.boy:
			Intent intent = new Intent();
			intent.putExtra(MyRes.TAG_ISBOY, true);
			intent.setClass(LogoActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
			break;
		case R.id.girl:
			Toast.makeText(LogoActivity.this, "点击进入女生头像", Toast.LENGTH_SHORT)
					.show();
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.logo, menu);
		return true;
	}

}
