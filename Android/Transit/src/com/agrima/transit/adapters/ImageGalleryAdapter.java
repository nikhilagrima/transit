package com.agrima.transit.adapters;



import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.agrima.transit.R;

public class ImageGalleryAdapter extends BaseAdapter {

	private Context context;
	
	private Integer[] mImageIds = {
			R.drawable.slider,
			R.drawable.slider2,
			R.drawable.slider3            
    };
	
	public ImageGalleryAdapter(Context con) {
		this.context = con;
	}
	
	
	public Integer getResId(int pos) {
		return mImageIds[pos];
	}
	
	@Override
	public int getCount() {	
		return mImageIds.length;
	}
	
	@Override
	public Object getItem(int arg0) {
		return mImageIds[arg0];
	}
	
	@Override
	public long getItemId(int arg0) {
		return arg0;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ImageView i = new ImageView(this.context);
		
		i.setImageResource(mImageIds[position]);
		i.setLayoutParams(new Gallery.LayoutParams(550, 200));
		
		i.setScaleType(ScaleType.FIT_CENTER);
		
		return i;
	}
	
}
