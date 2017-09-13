package com.finch.facelovely;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class SplashActivity extends Activity {
	static Timer timer = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(SplashActivity.this,LogoActivity.class);
				startActivity(intent);
				finish();
			}
		}, 2000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

}
