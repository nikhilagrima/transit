package com.agrima.transit.adapters;

import java.util.ArrayList;

import com.agrima.transit.R;
import com.agrima.transit.models.Stock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;




public class TransitGridAdapter extends BaseAdapter {
	
	Context context;
	ArrayList<Stock> values;
	
	public TransitGridAdapter(Context con,ArrayList<Stock> list) {
		this.context = con;
		this.values  = list;
	}
	
	@Override
	public int getCount() {
		return this.values.size();
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 
		View gridView = null; 
		
		
		//if(convertView == null){
			//gridView = new View(context);
			Stock item = values.get(position);
		
			gridView = inflater.inflate(R.layout.grid_tile, null);
			
			ImageView tileImageView = (ImageView)gridView.findViewById(R.id.imgGrid);
			tileImageView.setImageDrawable(item.getImage());
			
			final ImageView loveImageView = (ImageView)gridView.findViewById(R.id.imgLove);
			
			
			
			loveImageView.setOnClickListener(new OnClickListener() {
				int love = 0;
				@Override
				public void onClick(View v) {				
					if(love == 0){
						loveImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.heart2));
						love = 1;
					}else{
						loveImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.heart1));
						love = 0;
					}
				}
			});
			
			TextView txtName = (TextView)gridView.findViewById(R.id.txtName);
			TextView txtPrice = (TextView)gridView.findViewById(R.id.txtPrice);
			
			txtName.setText(item.getName());
			txtPrice.setText(item.getPrice());
			
		//}
			
		return gridView;
	}
	
	@Override
	public Object getItem(int position) {
		return values.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return values.get(position).getId();
	}
	
}
