package com.finch.facelovely.fragmentui;

import com.finch.facelovely.MainActivity;
import com.finch.facelovely.listener.FaceItemListener;
import com.finch.facelovely.res.MyRes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FacePageAdapter extends FragmentPagerAdapter {
	FaceItemListener mListener;

	public FacePageAdapter(FragmentManager fm, FaceItemListener listener) {
		super(fm);
		// TODO Auto-generated constructor stub
		mListener = listener;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		FaceFragment fragment = new FaceFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(MyRes.TAG_TYEP, arg0);
		bundle.putBoolean(MyRes.TAG_ISBOY, MainActivity.isBoy);
		fragment.setArguments(bundle);

		fragment.setListener(mListener);
		return fragment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 12;
	}

}
