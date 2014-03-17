package com.example.profilesque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONObject;

import com.example.profilesque.R;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.widget.TextView;

public class Profile extends Activity {
	
	String conUrl = "http://calm-shore-springmvc-hibernate.herokuapp.com/mobileprofile";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);

		TextView txtFN = (TextView) findViewById(R.id.textView1);
		TextView txtLN = (TextView) findViewById(R.id.textView2);
		TextView txtAB = (TextView) findViewById(R.id.textView3);
		TextView txtFNDISP = (TextView) findViewById(R.id.textView4);
		TextView txtLNDISP = (TextView) findViewById(R.id.textView5);
		final TextView txtABDISP = (TextView) findViewById(R.id.textView6);
		
		new JSONParser().execute();

	}
	
	// Uses AsyncTask to create a task away from the main UI thread. This task takes a 
    // URL string and uses it to create an HttpUrlConnection. Once the connection
    // has been established, the AsyncTask downloads the contents of the web page as
    // an InputStream. Finally, the InputStream is converted into a string, which is
    // displayed in the UI by the AsyncTask's onPostExecute method.
    private class JSONParser extends AsyncTask<Void, Void, String> {
    	
    	@Override
    	protected String doInBackground(Void... arg0) {
			
    		TextView txtABDISP = (TextView) findViewById(R.id.textView6);
    		
    		try {
    			// Create a new HTTP Client
    			DefaultHttpClient defaultClient = new DefaultHttpClient();
    			// Setup the get request
    			HttpGet httpGetRequest = new HttpGet(conUrl);

    			// Execute the request in the client
    			HttpResponse httpResponse = defaultClient.execute(httpGetRequest);
    			// Grab the response
    			BufferedReader reader = new BufferedReader(new InputStreamReader(
    					httpResponse.getEntity().getContent(), "UTF-8"));
    			String json = reader.readLine();

    			// Instantiate a JSON object from the request response
    			JSONObject jsonObject = new JSONObject(json);

    			String aJsonString = jsonObject.getString("sessionid");
    			
    			return aJsonString;

    		} catch (Exception e) {
    			// In your production code handle any errors and catch the
    			// individual exceptions
    			e.printStackTrace();
    			return e.toString();
    		}
    		
    	}
       
       // onPostExecute displays the results of the AsyncTask.
    	@Override
    	protected void onPostExecute(String result) {
    		TextView txtABDISP = (TextView) findViewById(R.id.textView6);
    		txtABDISP.setText(result);
    	}

    }
}