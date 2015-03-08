package com.hereshem.databaseconnect;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ServerRequest {

	public String getHTTP(String url){
		try {
			HttpGet request = new HttpGet(url);
			HttpResponse response = new DefaultHttpClient().execute(request);
			
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			return "error";
		}
	}
	
	public boolean isNetworkConnected(Context ctx) {
		ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
			return false; // There are no active networks.
		} else
			return true;
	}
}
