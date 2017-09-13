package com.finch.facelovely.res;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class CustomView extends View {

	boolean gender;
	public int[] resource;
	public Bitmap[] bitmap;
	MyRes res;

	public CustomView(Context context, boolean gender) {
		super(context);
		this.gender = gender;
		res = new MyRes();
		if (gender) {
			// 拿到男孩默认的图片
			resource = res.getBoyDefault();
		} else {
			// 拿到女孩默认的图片
			resource = res.getGirlDefault();
		}
		getData(context);
		bitmap = new Bitmap[resource.length];
		for (int i = 0; i < resource.length; i++) {
			Bitmap bm = BitmapFactory.decodeResource(getResources(),
					resource[i]);
			bitmap[i] = bm;
		}
	}

	public void getData(Context context) {

		SharedPreferences sharedPreferences;
		if (gender) {
			sharedPreferences = context.getSharedPreferences("boy",
					Context.MODE_PRIVATE);
		} else {
			sharedPreferences = context.getSharedPreferences("girl",
					Context.MODE_PRIVATE);
		}
		resource[0] = sharedPreferences.getInt("发型", resource[0]);
		resource[1] = sharedPreferences.getInt("脸型", resource[1]);
		resource[2] = sharedPreferences.getInt("眉毛", resource[2]);
		resource[3] = sharedPreferences.getInt("眼睛", resource[3]);
		resource[4] = sharedPreferences.getInt("嘴巴", resource[4]);
		resource[5] = sharedPreferences.getInt("特征", resource[5]);
		resource[6] = sharedPreferences.getInt("眼镜", resource[6]);
		resource[7] = sharedPreferences.getInt("衣服", resource[7]);
		resource[8] = sharedPreferences.getInt("帽子", resource[8]);
		resource[9] = sharedPreferences.getInt("爱好", resource[9]);
		resource[10] = sharedPreferences.getInt("背景", resource[10]);
		resource[11] = sharedPreferences.getInt("气泡", resource[11]);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		// 缩放要画的图片
		int width = this.getWidth();
		int height = this.getHeight();
		// 缩放发型
		bitmap[0] = Bitmap.createScaledBitmap(bitmap[0], 2 * width / 3,
				2 * width / 3, false);
		bitmap[1] = Bitmap.createScaledBitmap(bitmap[1], 2 * width / 3,
				2 * width / 3, false);
		bitmap[2] = Bitmap.createScaledBitmap(bitmap[2], width / 3, width / 3,
				false);
		bitmap[3] = Bitmap.createScaledBitmap(bitmap[3], 3 * width / 10,
				3 * width / 10, false);
		bitmap[4] = Bitmap.createScaledBitmap(bitmap[4], width / 5, width / 5,
				false);
		bitmap[5] = Bitmap.createScaledBitmap(bitmap[5], width / 2, width / 2,
				false);
		bitmap[6] = Bitmap.createScaledBitmap(bitmap[6], width / 3, width / 3,
				false);
		bitmap[7] = Bitmap.createScaledBitmap(bitmap[7], 5 * width / 6,
				5 * width / 7, false);//------------11
		bitmap[10] = Bitmap
				.createScaledBitmap(bitmap[10], width, height, false);
		// 先画背景 画头
		canvas.drawBitmap(bitmap[10], 0, 0, null);
		
		canvas.drawBitmap(bitmap[1], (width - bitmap[1].getWidth()) / 2,
				(height - bitmap[1].getHeight()) / 2, null);
		canvas.drawBitmap(bitmap[2], (width - bitmap[2].getWidth()) / 2,
				(height - bitmap[2].getHeight()) / 2, null);
		canvas.drawBitmap(bitmap[3], (width - bitmap[3].getWidth()) / 2,
				(height - bitmap[2].getHeight()) / 2 + height / 15, null);
		
		canvas.drawBitmap(bitmap[4], (width - bitmap[4].getWidth()) / 2,
				11 * height / 20, null);
		
		//以下是画衣服
		canvas.drawBitmap(bitmap[5], (width - bitmap[5].getWidth()) / 2,
				(height - bitmap[5].getHeight()) / 2, null);
		canvas.drawBitmap(bitmap[6], (width - bitmap[6].getWidth()) / 2,
				(height - bitmap[6].getHeight()) / 2 + height / 20, null);
		canvas.drawBitmap(bitmap[7], (width - bitmap[7].getWidth()) / 2, height
				- bitmap[7].getHeight(), null);
		// 把发型画在控件中心位置
		canvas.drawBitmap(bitmap[0], (width - bitmap[0].getWidth()) / 2,
				(height - bitmap[0].getHeight()) / 2, null);
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setTextSize(22);
		super.onDraw(canvas);

	}
}
