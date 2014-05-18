package com.agrima.transit;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;

import com.agrima.transit.adapters.ImageGalleryAdapter;

public class StoreActivity extends Activity {

	ActionBar titleBar;
	Context context;
	private static final String TAG = "Transit";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_store);
		
		context = getApplicationContext();
				
		
		setActionBarView(getIntent().getStringExtra("storeId"));
		
		Gallery gallery = (Gallery)findViewById(R.id.gallery_store);		
		
		gallery.setSpacing(1);
		final ImageGalleryAdapter adapter = new ImageGalleryAdapter(getApplicationContext());
		gallery.setAdapter(adapter);
		
		OnItemClickListener listener = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,long arg3) {
				
			}
		};
		
		gallery.setOnItemClickListener(listener);
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
