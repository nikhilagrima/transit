package com.agrima.transit;

import java.io.IOException;



import com.agrima.transit.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

public class SplashScreen extends Activity {

	private static final String TAG = "Transit";

    public static final String EXTRA_DATA = "data";
    public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    // IMPORTANT: replace with your own sender ID
    private String SENDER_ID = "412575123763";

    
    private GoogleCloudMessaging gcm;
    private Context context;

    private String registrationId;
    
    ProgressBar spashProgress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_sceen);
		
		
		 context = getApplicationContext();
		 spashProgress = (ProgressBar) findViewById(R.id.splashProgressBar);
				 

	       // mDisplay = (TextView) findViewById(R.id.display);

	     //   Button btnClearMessage = (Button) findViewById(R.id.btnClearMessages);

	     
	        setReceivedMessageFromIntent(getIntent());

	        // Check device for Play Services APK. If check succeeds, proceed with GCM registration.
	        if (checkPlayServices()) {
	            gcm = GoogleCloudMessaging.getInstance(this);
	            registrationId = getRegistrationId(context);
	           
	            if (registrationId.length() == 0) {
	                registerInBackground();
	            }else{
	            	Toast.makeText(getApplicationContext(), "Registerd ="+registrationId, Toast.LENGTH_SHORT).show();
	            	homescreen();
	            }
	        } else {
	            Log.i(TAG, "No valid Google Play Services APK found.");
	        }

	}
	
	void homescreen(){
		Intent i = new Intent(SplashScreen.this,HomeScreen.class);
		startActivity(i);
		finish();
	}


	
	 @Override
	    protected void onResume() {
	        super.onResume();
	        // Check device for Play Services APK.
	        checkPlayServices();
	    }

	    @Override
	    protected void onNewIntent(Intent intent) {
	        super.onNewIntent(intent);

	        setReceivedMessageFromIntent(intent);
	    }

	    private void setReceivedMessageFromIntent(Intent intent) {
	        if (intent != null) {

	            Bundle extras = intent.getBundleExtra(EXTRA_DATA);

	            if (extras != null) {
	                String messageReceived = extras.getString(EXTRA_MESSAGE);

	                if (messageReceived != null && messageReceived.length() > 0) {
	                    
	                }
	            }
	        }
	    }

	    /**
	     * Check the device to make sure it has the Google Play Services APK. If
	     * it doesn't, display a dialog that allows users to download the APK from
	     * the Google Play Store or enable it in the device's system settings.
	     */
	    private boolean checkPlayServices() {
	        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
	        if (resultCode != ConnectionResult.SUCCESS) {
	            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
	                GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST).show();
	            } else {
	                Log.i(TAG, "This device is not supported.");
	                finish();
	            }
	            return false;
	        }
	        return true;
	    }

	    /**
	     * Stores the registration ID and the app versionCode in the application's
	     * {@code SharedPreferences}.
	     *
	     * @param context application's context.
	     * @param regId registration ID
	     */
	    private void storeRegistrationId(Context context, String regId) {
	        final SharedPreferences prefs = getGcmPreferences(context);
	        int appVersion = getAppVersion(context);
	        Log.i(TAG, "Saving regId on app version " + appVersion);
	        SharedPreferences.Editor editor = prefs.edit();
	        editor.putString(PROPERTY_REG_ID, regId);
	        editor.putInt(PROPERTY_APP_VERSION, appVersion);
	        editor.commit();
	    }

	    /**
	     * Gets the current registration ID for application on GCM service, if there is one.
	     * <p>
	     * If result is empty, the app needs to register.
	     *
	     * @return registration ID, or empty string if there is no existing
	     *         registration ID.
	     */
	    private String getRegistrationId(Context context) {
	        final SharedPreferences prefs = getGcmPreferences(context);
	        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
	        if (registrationId.length() == 0) {
	            Log.i(TAG, "Registration not found.");
	            return "";
	        }
	        // Check if app was updated; if so, it must clear the registration ID
	        // since the existing regID is not guaranteed to work with the new
	        // app version.
	        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
	        int currentVersion = getAppVersion(context);
	        if (registeredVersion != currentVersion) {
	            Log.i(TAG, "App version changed.");
	            return "";
	        }
	        return registrationId;
	    }

	    /**
	     * Registers the application with GCM servers asynchronously.
	     * <p>
	     * Stores the registration ID and the app versionCode in the application's
	     * shared preferences.
	     */
	    private void registerInBackground() {
	        new AsyncTask<Void, Void, String>() {
	            @Override
	            protected String doInBackground(Void... params) {
	                String msg = "";
	                try {
	                    if (gcm == null) {
	                        gcm = GoogleCloudMessaging.getInstance(context);
	                    }

	                    registrationId = gcm.register(SENDER_ID);
	                    msg = "Device registered, registration ID=" + registrationId;

	                    // You should send the registration ID to your server over HTTP, so it
	                    // can use GCM/HTTP or CCS to send messages to your app.
	                    //sendRegistrationIdToBackend();

	                    // Persist the regID - no need to register again.
	                    storeRegistrationId(context, registrationId);
	                } catch (IOException ex) {
	                    msg = "Error :" + ex.getMessage();
	                    // If there is an error, don't just keep trying to register.
	                    // Require the user to click a button again, or perform
	                    // exponential back-off.
	                }
	                return msg;
	            }

	            @Override
	            protected void onPostExecute(String msg) {
	            	 Toast.makeText(getApplicationContext(), registrationId, Toast.LENGTH_SHORT).show();
	            	 homescreen();
	            }
	        }.execute(null, null, null);
	    }

	    @Override
	    protected void onDestroy() {
	        super.onDestroy();
	    }

	    /**
	     * @return Application's version code from the {@code PackageManager}.
	     */
	    private static int getAppVersion(Context context) {
	        try {
	            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
	            return packageInfo.versionCode;
	        } catch (NameNotFoundException e) {
	            // should never happen
	            throw new RuntimeException("Could not get package name: " + e);
	        }
	    }

	    /**
	     * @return Application's {@code SharedPreferences}.
	     */
	    private SharedPreferences getGcmPreferences(Context context) {
	        // This sample app persists the registration ID in shared preferences, but
	        // how you store the regID in your app is up to you.
	        return getSharedPreferences(TAG, Context.MODE_PRIVATE);
	    }

	    /**
	     * Sends the registration ID to the server over HTTP, so it can use GCM/HTTP or CCS to send
	     * messages to your app.
	     */
	   
	

}
