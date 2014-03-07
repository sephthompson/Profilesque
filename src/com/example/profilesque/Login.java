package com.example.profilesque;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.profilesque.R;

import android.os.Bundle;
import android.app.Activity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {

	private String resp;
	private String errorMsg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		final EditText textUser = (EditText) findViewById(R.id.editText1);
		final EditText textPass = (EditText) findViewById(R.id.editText2);
		final TextView textViewError = (TextView) findViewById(R.id.textView2);
		textViewError.setMovementMethod(new ScrollingMovementMethod());
		Button submitBtn = (Button) findViewById(R.id.button1);

		submitBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				new Thread(new Runnable() {
					public void run() {

						ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
						postParameters.add(new BasicNameValuePair("email", textUser.getText().toString()));
						postParameters.add(new BasicNameValuePair("password", textPass.getText().toString()));

						String response = null;

						try {
							response = SimpleHttpClient
									.executeHttpPost("http://calm-shore-springmvc-hibernate.herokuapp.com/MobileLogin", postParameters);
							String res = response.toString();
							resp = res;//.replaceAll("\\s+", "");

						} catch (Exception e) {
							Log.d("Exception", e.toString());
							textViewError.setText(e.toString());
						}
					}
				}).start();

				try {
					// Wait a second to get response from server
					Thread.sleep(1000);
					
					// Inside the new thread we cannot update the main thread
					// So updating the main thread outside the new thread

					// textViewError.setText("RESPONSE FROM SERVLET: " + resp);
					resp.toString();
					
					// DEBUG OUTPUT
					if (resp.equals("success")) {
						textViewError.setText("SUCCESS!");
					} else if (resp.equals("failure")) {
						textViewError.setText("FAILURE!");
					} else {
						textViewError.setText("NO SCENARIO MATCH: " + resp);
					}

					// ERROR OUTPUT
					if (null != errorMsg && !errorMsg.isEmpty()) {
						textViewError.setText("ERRORMSG: " + errorMsg);
					}
				} catch (Exception e) {
					textViewError.setText("EXCEPTION: " + e.getMessage());
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		// setContentView(R.layout.activity_debug);
		return true;
	}

}
