package com.hereshem.databaseconnect;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListDataActivity extends Activity {

	String data[] = { "Hello", "this", "is", "android", "class" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_adddata);

		new AsyncTask<Void, Void, String>() {

			@Override
			protected String doInBackground(Void... params) {
				ServerRequest req = new ServerRequest();
				if (req.isNetworkConnected(getApplicationContext())) {
					return req
							.getHTTP("http://pi.hemshrestha.com.np/test/contacto.php/?action=list");
				} else {
					Toast.makeText(getApplicationContext(),
							"No internet connection", Toast.LENGTH_SHORT)
							.show();
				}
				return null;
			}

			@Override
			protected void onPostExecute(String result) {
				super.onPostExecute(result);

				try {
					JSONObject jObj = new JSONObject(result);
					JSONArray jarr = jObj.getJSONArray("data");
					data = new String[jarr.length()];
					for (int i = 0; i < jarr.length(); i++) {
						JSONObject j = jarr.getJSONObject(i);
						String name = j.getString("c_fname");
						data[i] = name;
					}
				} catch (Exception e) {
				}
				populate();
			}
		}.execute();
	}

	public void populate() {
		ListView lv = (ListView) findViewById(R.id.listView1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				getApplicationContext(), android.R.layout.simple_list_item_1, data) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.simple_list_data, null);
				TextView tv = (TextView) view.findViewById(R.id.txt_name);
				tv.setText(data[position]);
				return view;
			}
		};
		lv.setAdapter(adapter);
	}

}
