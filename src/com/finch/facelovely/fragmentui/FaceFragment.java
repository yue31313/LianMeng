package com.finch.facelovely.fragmentui;

import com.finch.facelovely.listener.FaceItemListener;
import com.finch.facelovely.res.MyRes;
import com.finch.facelovely.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class FaceFragment extends Fragment {
	int[] res;// ��ǰfragmentҪ��ʾ����Դ
	int type;
	boolean gender;

	FaceItemListener listener;

	public FaceItemListener getListener() {
		return listener;
	}

	public void setListener(FaceItemListener listener) {
		this.listener = listener;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// ����fragment��ʱ�򴫵ݵĲ���
		Bundle bundle = getArguments();
		type = bundle.getInt(MyRes.TAG_TYEP);
		gender = bundle.getBoolean(MyRes.TAG_ISBOY);
		switch (type) {
		case 0:
			if (gender) {
				// boy�ķ��͵�ͼƬ��ַ����
				res = MyRes.getBoyHair();
			} else {
				// gril�ķ��͵�ͼƬ��ַ����
				res = MyRes.getGirlHair();
			}
			break;
		case 1:
			// ���͵�ͼƬ��ַ����
			res = MyRes.getFaceShape();
			break;
		case 2:
			// üë��ͼƬ��ַ����
			res = MyRes.getEyeBrow();
			break;
		case 3:
			// �۾���ͼƬ��ַ����
			res = MyRes.getEye();
			break;
		case 4:
			// ��͵�ͼƬ��ַ����
			res = MyRes.getMouth();
			break;
		case 5:
			if (gender) {
				// boy������ͼƬ��ַ����
				res = MyRes.getFeature();
			} else {
				// girl������ͼƬ��ַ����
				res = MyRes.getGirlFeature();
			}
			break;
		case 6:
			// �۾�ͼƬ��ַ����
			res = MyRes.getGlass();
			break;
		case 7:
			if (gender) {
				// boy�·�ͼƬ�ĵ�ַ����
				res = MyRes.getBoyClothes();
			} else {
				// girl�·�ͼƬ��ַ����
				res = MyRes.getGirlClothes();
			}
			break;
		case 8:
			// ñ��ͼƬ��ַ����
			res = MyRes.getHat();
			break;
		case 9:
			// ����ͼƬ��ַ����
			res = MyRes.getHobby();
			break;
		case 10:
			// ����ͼƬ��ַ����
			res = MyRes.getBackGround();
			break;
		case 11:
			// ����ͼƬ��ַ����
			res = MyRes.getPop();
			break;
		default:
			break;
		}
		// ���봴���ײ�Ҫ��ʾ��GridView
		GridView gridView = new GridView(getActivity());
		gridView.setNumColumns(3);
		gridView.setAdapter(new MyAdapter());
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				int resource;
				if (type == 0) {
					if (gender) {
						resource = MyRes.getRealBoyHair()[position];
					} else {
						resource = MyRes.getRealGirlHair()[position];
					}
				} else if (type == 5) {
					if (gender) {
						resource = MyRes.getRealBoyFeature()[position];
					} else {
						resource = MyRes.getRealGirlFeature()[position];
					}
				} else {
					resource = res[position];
				}
				if (resource == R.drawable.show_no) {
					resource = R.drawable.show_nothing;
				}
				listener.changeItemClick(resource, type);
			}
		});
		return gridView;
	}

	class MyAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return res.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return res[position];
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			// ����ÿ��ͼƬ����С�߶ȺͿ��
			ImageView iv = new ImageView(getActivity());
			iv.setMinimumWidth(120);
			iv.setMinimumHeight(120);
			// ����imageView�ı������ֶ����ƵĴ��߿����
			iv.setBackgroundResource(R.drawable.image_bg);
			iv.setImageResource(res[position]);
			iv.setPadding(5, 2, 5, 2);
			return iv;
		} 

	}
}
