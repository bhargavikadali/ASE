package com.lkkwd.bhargavi_lab4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	
	Button chanarea;
	TextView display;
	EditText getcity;
	
	String temperature;
	String wea;
	String jsonstring;
	String city;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		chanarea = (Button)findViewById(R.id.button1);
		display = (TextView)findViewById(R.id.TextView3);
		getcity = (EditText)findViewById(R.id.getcity);
		
		final int z;
				
		chanarea.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
					HttpClient htp = new DefaultHttpClient();
					Editable add= getcity.getText();
					
					String url="http://api.openweathermap.org/data/2.5/weather?q="+add.toString();
					HttpResponse response =null;
					
					HttpPost htppost = new HttpPost(url);
					try{
						response = htp.execute(htppost);
					}catch (ClientProtocolException e1){
						e1.printStackTrace();
					}catch (IOException e1){
						e1.printStackTrace();
					}
					StatusLine statusline = response.getStatusLine();
					if(statusline.getStatusCode()==HttpStatus.SC_OK)
					{
						ByteArrayOutputStream out = new ByteArrayOutputStream();
						try{
							response.getEntity().writeTo(out);
							
						}catch (IOException e){
							e.printStackTrace();
						}
						try{ 
							out.close();
						}catch (IOException e){
							e.printStackTrace();
						}
						jsonstring = out.toString();
					}
					try{
						JSONObject obs = new JSONObject(jsonstring);
						JSONObject obj = obs.getJSONObject("main");
						
						
						
						wea = obj.getString("temp");
						
						
						
					}catch(JSONException e){
						e.printStackTrace();
					}
					
					display.setText("Today Weather\nTemperature : "+wea+"\n City :"+ add);
					
							}
		});				
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
