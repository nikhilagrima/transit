package com.agrima.transit;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.agrima.transit.adapters.TransitListAdapter;
import com.agrima.transit.models.ListStock;
import com.agrima.transit.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class ListUI extends Activity {

	ActionBar titleBar;
	Context context;
	ListView tileList;
	private static final String TAG = "Transit";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_view);
		context = getApplicationContext();
				
		
		
		
		
		
		//ArrayList<ListStock> list = new ArrayList<ListStock>();
		
		
		//list.add(new ListStock(getResources().getDrawable(R.drawable.offer_1)));
		//list.add(new ListStock("hai dear","#f45ff0"));
		//list.add(new ListStock(getResources().getDrawable(R.drawable.offer_2)));
		//list.add(new ListStock("hai dear","#fff000"));
		tileList = (ListView)findViewById(R.id.list_view);
		
		setActionBarView(getIntent().getStringExtra("storeId"));
		
		//TransitListAdapter adapter = new TransitListAdapter(this, list);
		
		//tileList.setAdapter(adapter);
		
		
	}
	
	private void setActionBarView(String StoreId){
		final SharedPreferences prefs = getGcmPreferences(context);
		String jsonStr = prefs.getString(StoreId, "");
		if(!(jsonStr.equals("")||jsonStr.equals(null))){
			
			Toast.makeText(getApplicationContext(), StoreId , Toast.LENGTH_LONG).show();
			try{
				
				
				
			JSONObject jsonObj = new JSONObject(jsonStr);
			
			Toast.makeText(getApplicationContext(), jsonObj.toString() , Toast.LENGTH_LONG).show();
		        
			titleBar = getActionBar();
			titleBar.setTitle(jsonObj.getString("header_title"));
			titleBar.setIcon(R.drawable.openfiles);
			String col = jsonObj.getString("header_color");
			switch (col){
			case "Header1":
				titleBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1D1C1C")));
				break;
			case "Header2":
				titleBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0166A0")));
				break;
			case "Header3":
				titleBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FE4646")));
				break;
			case "Header4":
				titleBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#54D111")));
				break;
			case "Header5":
				titleBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#058517")));
				break;
			default :
				titleBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#"+col)));
			}
			
			
			ArrayList<ListStock> list = new ArrayList<ListStock>();
			
			
			String c = jsonObj.getString("body_1");
			String[] s = c.split(",");
			for (String string : s) {
				if(!(string.equals("")||string == null)){
				list.add(getele(string));
				}
			}
			
			c = jsonObj.getString("body_2");
			 s = c.split(",");
			 for (String string : s) {
				 if(!(string.equals("")||string == null)){
						list.add(getele(string));
						}
				}
			
			
			
			TransitListAdapter adapter = new TransitListAdapter(this, list);
			
			tileList.setAdapter(adapter);
			
		        
			}catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	ListStock getele(String c){
		switch (c){
		case "image1":
			return new ListStock(getResources().getDrawable(R.drawable.offer_1));
			
		case "image2":
			return new ListStock(getResources().getDrawable(R.drawable.offer_2));
		case "image3":
			return (new ListStock(getResources().getDrawable(R.drawable.offer_3)));
		default :
			return new ListStock(c,"#08088A");
			
				
			
		}
	}
	
	private SharedPreferences getGcmPreferences(Context context) {
        // This sample app persists the registration ID in shared preferences, but
        // how you store the regID in your app is up to you.
        return getSharedPreferences(TAG, Context.MODE_PRIVATE);
    }

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

}
