package com.agrima.transit;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;






import com.agrima.transit.adapters.TransitGridAdapter;
import com.agrima.transit.models.Stock;



import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GridUI extends Activity{

	ActionBar titleBar;
	Context context;
	GridView tileGrid;
	TransitGridAdapter adapter;
	private static final String TAG = "Transit";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid_ui);
		context = getApplicationContext();
				
		
		setActionBarView(getIntent().getStringExtra("storeId"));
		
		
ArrayList<Stock> list = new ArrayList<Stock>();
		
		list.add(new Stock(getResources().getDrawable(R.drawable.kutty),"Item1","Rs:10000"));		
		list.add(new Stock(getResources().getDrawable(R.drawable.kutty2),"Item3","Rs:10000"));
		list.add(new Stock(getResources().getDrawable(R.drawable.kutty3),"Item4","Rs:10000"));
		list.add(new Stock(getResources().getDrawable(R.drawable.kutty4),"Item5","Rs:10000"));
		list.add(new Stock(getResources().getDrawable(R.drawable.kutty5),"Item6","Rs:10000"));
		list.add(new Stock(getResources().getDrawable(R.drawable.kutty6),"Item7","Rs:10000"));
		list.add(new Stock(getResources().getDrawable(R.drawable.kutty7),"Item8","Rs:10000"));
		list.add(new Stock(getResources().getDrawable(R.drawable.kutty8),"Item9","Rs:10000"));
		
		tileGrid = (GridView)findViewById(R.id.grid);
		
		adapter = new TransitGridAdapter(this, list);
		tileGrid.setAdapter(adapter);
		
		tileGrid.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Toast.makeText(
				   getApplicationContext(),
				   ((TextView) v.findViewById(R.id.txtName))
				   .getText(), Toast.LENGTH_SHORT).show();
 
			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	private void setActionBarView(String StoreId){
		final SharedPreferences prefs = getGcmPreferences(context);
		String jsonStr = prefs.getString(StoreId, "");
		if(!(jsonStr.equals("")||jsonStr.equals(null))){
			
			Toast.makeText(getApplicationContext(), "hear" , Toast.LENGTH_LONG).show();
			try{
				
				
				JSONObject jsonObj = new JSONObject(jsonStr);
				
				Toast.makeText(getApplicationContext(), jsonObj.toString() , Toast.LENGTH_LONG).show();
			        
				titleBar = getActionBar();
				titleBar.setTitle(jsonObj.getString("header_title"));
				titleBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor(jsonObj.getString("header_color"))));
			}catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	private SharedPreferences getGcmPreferences(Context context) {
        // This sample app persists the registration ID in shared preferences, but
        // how you store the regID in your app is up to you.
        return getSharedPreferences(TAG, Context.MODE_PRIVATE);
    }

	

}
