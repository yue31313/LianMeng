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
	int[] res;// 当前fragment要显示的资源
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
		// 创建fragment的时候传递的参数
		Bundle bundle = getArguments();
		type = bundle.getInt(MyRes.TAG_TYEP);
		gender = bundle.getBoolean(MyRes.TAG_ISBOY);
		switch (type) {
		case 0:
			if (gender) {
				// boy的发型的图片地址集合
				res = MyRes.getBoyHair();
			} else {
				// gril的发型的图片地址集合
				res = MyRes.getGirlHair();
			}
			break;
		case 1:
			// 脸型的图片地址集合
			res = MyRes.getFaceShape();
			break;
		case 2:
			// 眉毛的图片地址集合
			res = MyRes.getEyeBrow();
			break;
		case 3:
			// 眼睛的图片地址集合
			res = MyRes.getEye();
			break;
		case 4:
			// 嘴巴的图片地址集合
			res = MyRes.getMouth();
			break;
		case 5:
			if (gender) {
				// boy的特征图片地址集合
				res = MyRes.getFeature();
			} else {
				// girl的特征图片地址集合
				res = MyRes.getGirlFeature();
			}
			break;
		case 6:
			// 眼镜图片地址集合
			res = MyRes.getGlass();
			break;
		case 7:
			if (gender) {
				// boy衣服图片的地址集合
				res = MyRes.getBoyClothes();
			} else {
				// girl衣服图片地址集合
				res = MyRes.getGirlClothes();
			}
			break;
		case 8:
			// 帽子图片地址集合
			res = MyRes.getHat();
			break;
		case 9:
			// 爱好图片地址集合
			res = MyRes.getHobby();
			break;
		case 10:
			// 背景图片地址集合
			res = MyRes.getBackGround();
			break;
		case 11:
			// 气泡图片地址集合
			res = MyRes.getPop();
			break;
		default:
			break;
		}
		// 代码创建底部要显示的GridView
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
			// 设置每张图片的最小高度和宽度
			ImageView iv = new ImageView(getActivity());
			iv.setMinimumWidth(120);
			iv.setMinimumHeight(120);
			// 设置imageView的背景，手动绘制的带边框矩形
			iv.setBackgroundResource(R.drawable.image_bg);
			iv.setImageResource(res[position]);
			iv.setPadding(5, 2, 5, 2);
			return iv;
		} 

	}
}
