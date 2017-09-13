package com.finch.facelovely;

import java.io.FileOutputStream;
import java.io.IOException;

import com.finch.facelovely.fragmentui.FacePageAdapter;
import com.finch.facelovely.listener.FaceItemListener;
import com.finch.facelovely.res.CustomView;
import com.finch.facelovely.res.MyRes;

import android.os.Bundle;
import android.os.Environment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements FaceItemListener {

	private ViewPager viewPager;
	private RadioGroup radioGroup;
	private HorizontalScrollView horiScrollView;
	private RadioButton radioButton;
	private TextView tvLine;
	private int fromX;
	private CustomView customView;
	private RelativeLayout reLayout;
	public static boolean isBoy = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent = getIntent();
		isBoy = intent.getBooleanExtra(MyRes.TAG_ISBOY, true);
		reLayout = (RelativeLayout) findViewById(R.id.rl);
		customView = new CustomView(this, isBoy);
		customView.setDrawingCacheEnabled(true);
		reLayout.addView(customView);

		initRadioButton();
		horiScrollView = (HorizontalScrollView) findViewById(R.id.hsv);
		radioButton = (RadioButton) findViewById(R.id.rb0);
		viewPager = (ViewPager) findViewById(R.id.vp);
		tvLine = (TextView) findViewById(R.id.line);
		viewPager.setAdapter(new FacePageAdapter(getSupportFragmentManager(),
				this));
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				// ��ȡˮƽ��������ǰλ��
				int total = (int) ((arg0 + arg1) * radioButton.getWidth());
				// ��ȡ���а�ť��x����
				int green = (viewPager.getWidth() - radioButton.getWidth()) / 2;
				// ����ˮƽ������Ӧ�û����ľ���
				int scroll = total - green;
				// ����ˮƽ������
				horiScrollView.scrollTo(scroll, 0);
				tvMoveTo(arg0, arg1);
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void tvMoveTo(int index, float f) {
		RadioButton button = (RadioButton) radioGroup.getChildAt(index);
		int location[] = new int[2];
		button.getLocationInWindow(location);
		TranslateAnimation animation = new TranslateAnimation(fromX,
				location[0] + f * button.getWidth(), 0, 0);
		animation.setDuration(50);
		animation.setFillAfter(true);
		fromX = (int) (location[0] + f * button.getWidth());
		// ��������
		tvLine.startAnimation(animation);

	}

	// ��ʼ��RadioButton
	private void initRadioButton() {
		radioGroup = (RadioGroup) findViewById(R.id.rg);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb0:
					// ������һҳ
					viewPager.setCurrentItem(0);
					break;
				case R.id.rb1:
					viewPager.setCurrentItem(1);
					break;
				case R.id.rb2:
					viewPager.setCurrentItem(2);
					break;
				case R.id.rb3:
					viewPager.setCurrentItem(3);
					break;
				case R.id.rb4:
					viewPager.setCurrentItem(4);
					break;
				case R.id.rb5:
					viewPager.setCurrentItem(5);
					break;
				case R.id.rb6:
					viewPager.setCurrentItem(6);
					break;
				case R.id.rb7:
					viewPager.setCurrentItem(7);
					break;
				case R.id.rb8:
					viewPager.setCurrentItem(8);
					break;
				case R.id.rb9:
					viewPager.setCurrentItem(9);
					break;
				case R.id.rb10:
					viewPager.setCurrentItem(10);
					break;
				case R.id.rb11:
					viewPager.setCurrentItem(11);
					break;
				default:
					break;
				}
			}
		});

	}

	/**
	 * ���������ҳ
	 */
	public void indexPage(View v) {
		Intent intent = new Intent();
		intent.setClass(this, LogoActivity.class);
		startActivity(intent);
		finish();
	}

	// ������水ť�����Լ�diy�õ�ͼƬ
	public void keepPhoto(View v) {
		// ��customView�������ұ���ͼƬ��sd����
		Bitmap bitmap = customView.getDrawingCache();
		// ��ͼƬ���� �Ե�ǰʱ��Ϊ׼������.png��ʽ����
		String photoName = System.currentTimeMillis() + ".png";
		// ���ñ���ͼƬ��·��
		String photo_path = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/" + photoName;
		try {
			// �����ļ���
			FileOutputStream os = new FileOutputStream(photo_path);
			bitmap.compress(CompressFormat.PNG, 100, os);
			os.close();
			// ����12��ͼƬ��sharedPreference
			SaveResource(this, isBoy);
			Toast.makeText(this, "����ɹ�\nͼƬ������" + photo_path, Toast.LENGTH_SHORT)
					.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * �����Ѿ��Զ��������ͼƬ
	 */
	public void SaveResource(Context context, boolean gender) {
		SharedPreferences sharedPreference;
		if (gender) {
			sharedPreference = context.getSharedPreferences("boy",
					Context.MODE_PRIVATE);
		} else {
			sharedPreference = context.getSharedPreferences("gril",
					Context.MODE_PRIVATE);
		}
		Editor editor = sharedPreference.edit();
		editor.putInt("����", customView.resource[0]);
		editor.putInt("����", customView.resource[1]);
		editor.putInt("üë", customView.resource[2]);
		editor.putInt("�۾�", customView.resource[3]);
		editor.putInt("���", customView.resource[4]);
		editor.putInt("����", customView.resource[5]);
		editor.putInt("�۾�", customView.resource[6]);
		editor.putInt("�·�", customView.resource[7]);
		editor.putInt("ñ��", customView.resource[8]);
		editor.putInt("����", customView.resource[9]);
		editor.putInt("����", customView.resource[10]);
		editor.putInt("����", customView.resource[11]);
		editor.commit();
	}

	@Override
	public void changeItemClick(int res, int index) {
		// ��myView�л�ͼƬ���ػ�ͼƬ
		Bitmap bm = BitmapFactory.decodeResource(getResources(), res);
		customView.bitmap[index] = bm;
		customView.resource[index] = res;
		customView.invalidate();
	}

}
