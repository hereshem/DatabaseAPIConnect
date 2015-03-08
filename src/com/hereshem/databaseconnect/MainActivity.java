package com.hereshem.databaseconnect;


import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// set view
		setContentView(R.layout.activity_main);
		
		Button save, view;
		
		save = (Button) findViewById(R.id.btn_save);
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//toast("Save clicked");
				doSave();
			}
		});
		
		
		view = (Button) findViewById(R.id.btn_view);
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				toast("Loading list");
				startActivity(new Intent(getApplicationContext(), ListDataActivity.class));
			}
		});
		
	}

	protected void doSave() {
		EditText ed_name = (EditText) findViewById(R.id.editText1);
		EditText ed_email = (EditText) findViewById(R.id.editText2);
		EditText ed_phone = (EditText) findViewById(R.id.editText3);
		
		String name = ed_name.getText().toString();
		String email = ed_email.getText().toString();
		String phone = ed_phone.getText().toString();
		
		toast("Saving name = " + name + " email = " + email + " phone = " + phone);
		
		//check validity
		if(name.length() < 3){
			toast("Less character");
			return;
		}
		if(email.length() < 3){
			toast("Less character");
			return;
		}
		if(email.length() < 3){
			toast("Less character");
			return;
		}
		
		///// ok

		final String url = "http://pi.hemshrestha.com.np/test/contacto.php/?action=add&c_fname=" 
				+ name + "&c_lname=" + email + "&n_mobile=" + phone;
		
		Log.i("Main", "url is = " + url);
		
	 	new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... params) {
				ServerRequest req = new ServerRequest();
				if(req.isNetworkConnected(getApplicationContext())){
					req.getHTTP(url);
				}
				else{
					toast("No internet connection");
				}
				return null;
			}
		}.execute();
		
	}

	protected void toast(String string) {
		Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
	}

	
	
}
