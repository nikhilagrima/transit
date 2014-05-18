package com.agrima.transit.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.agrima.transit.R;
import com.agrima.transit.models.ListStock;

public class TransitListAdapter extends ArrayAdapter<ListStock> {
	
	Context context;
	ArrayList<ListStock> values;
	
	
	public TransitListAdapter(Context con,ArrayList<ListStock> list) {
		super(con,R.layout.list_image ,list);
		this.context = con;
		this.values = list;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 
		View listView = null; 
		 		
		//if(convertView == null){
			//gridView = new View(context);
			ListStock item = values.get(position);
			
			int type=item.getId();
			if(type == 0){
				listView = inflater.inflate(R.layout.list_image, null);
				ImageView tileImageView = (ImageView)listView.findViewById(R.id.list_image_view);
				tileImageView.setImageDrawable(item.getImage());
			}else if(type == 1){
				listView = inflater.inflate(R.layout.list_label, null);
				TextView text = (TextView) listView.findViewById(R.id.list_text_view);				
				text.setText(item.getLabel());
				text.setBackgroundColor(Color.parseColor(item.getColor()));
			}
		
			
		return listView;

	}
	
	
}
