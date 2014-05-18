package com.agrima.transit;





import org.json.JSONException;
import org.json.JSONObject;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HomeScreen extends Activity {
	Button scanQR;
	Context context;
	private static final String TAG = "Transit";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_screen);
		context = getApplicationContext();
		scanQR = (Button) findViewById(R.id.ScanQR);
		
		scanQR.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				try {
					
					Intent intent = new Intent(
							"com.google.zxing.client.android.SCAN");
					intent.putExtra("SCAN_MODE", "QR_CODE_MODE,PRODUCT_MODE");
					startActivityForResult(intent, 0);
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), "ERROR:" + e, Toast.LENGTH_LONG).show();

				}

			}
		});
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_screen, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId())
        {
        case R.id.action_qr:
        	try {
				
				Intent intent = new Intent(
						"com.google.zxing.client.android.SCAN");
				intent.putExtra("SCAN_MODE", "QR_CODE_MODE,PRODUCT_MODE");
				startActivityForResult(intent, 0);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), "ERROR:" + e, Toast.LENGTH_LONG).show();

			}
            return true;
            
        default:
            return super.onOptionsItemSelected(item);
        }
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {

			if (resultCode == RESULT_OK) {
				Toast.makeText(getApplicationContext(), "FORMAT:" + intent.getStringExtra("SCAN_RESULT_FORMAT"), Toast.LENGTH_LONG).show();
				//Toast.makeText(getApplicationContext(), intent.getStringExtra("SCAN_RESULT"), Toast.LENGTH_LONG).show();
				String[] dataArray = storeStoreData(intent.getStringExtra("SCAN_RESULT"));
				Toast.makeText(getApplicationContext(),dataArray[1], Toast.LENGTH_LONG).show();
				if(dataArray!=null&dataArray[1].equals("grid")){
				Intent i = new Intent(HomeScreen.this,GridUI.class);
				i.putExtra("storeId", dataArray[0]);
				
				startActivity(i);
				}
				else if(dataArray!=null&dataArray[1].equals("list")){
					Intent i = new Intent(HomeScreen.this,ListUI.class);
					i.putExtra("storeId", dataArray[0]);
					
					startActivity(i);
					}
				else if(dataArray!=null&dataArray[1].equals("gallery")){
					Intent i = new Intent(HomeScreen.this,StoreActivity.class);
					i.putExtra("storeId", dataArray[0]);
					
					startActivity(i);
					}
				else{
					Toast.makeText(getApplicationContext(), "invalid data", Toast.LENGTH_LONG).show();
				}
			
				
			} else if (resultCode == RESULT_CANCELED) {
				Toast.makeText(getApplicationContext(), "Scan cancelled.", Toast.LENGTH_LONG).show();
				
			}
		}
	}
	
	private String[] storeStoreData(String jsonStr){
		String storeId;
		String type;
		final SharedPreferences prefs = getGcmPreferences(context);
		if(!(jsonStr.equals("")||jsonStr.equals(null))){
			
		
			try{
			JSONObject jsonObj = new JSONObject(jsonStr);
				storeId = jsonObj.getString("store_id");
				type = jsonObj.getString("type");
				SharedPreferences.Editor editor = prefs.edit();
		        editor.putString(storeId,jsonStr);
		        editor.commit();
		        return new String[] {storeId,type};
		        
			}catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
        
	}

	private SharedPreferences getGcmPreferences(Context context) {
        // This sample app persists the registration ID in shared preferences, but
        // how you store the regID in your app is up to you.
        return getSharedPreferences(TAG, Context.MODE_PRIVATE);
    }
	
}
