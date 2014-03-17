package com.example.profilesque;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.profilesque.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
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
				
				Toast.makeText(Login.this, "DEBUG: Button pressed!", Toast.LENGTH_SHORT).show();

				new Thread(new Runnable() {
					public void run() {

						ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
						postParameters.add(new BasicNameValuePair("email", textUser.getText().toString()));
						postParameters.add(new BasicNameValuePair("password", textPass.getText().toString()));

						String response = null;

						try {
							response = SimpleHttpClient
									.executeHttpPost("http://calm-shore-springmvc-hibernate.herokuapp.com/mobilelogin", postParameters);
							String res = response.toString();
							resp = res;//.replaceAll("\\s+", "");

						} catch (Exception e) {
							Log.d("Exception", e.toString());
							textViewError.setText("HTTPCLIENTEXCEPTION: " + e.toString());
						}
					}
				}).start();

				try {
					// Wait a second to get response from server
					Thread.sleep(2000);
					
					/* 
					 * Inside the new thread we cannot update the main thread
					 * So updating the main thread outside the new thread
					 */

					resp = resp.replace("\n", "");

					// DEBUG OUTPUT
					if (resp.equals("success")) {
						textViewError.setText("SUCCESS!");
						
						// START PROFILE ACTIVITY
						Intent intent = new Intent(Login.this, Profile.class);
						startActivity(intent);
						// END PROFILE ACTIVITY
						
					} else if (resp.equals("failure")) {
						textViewError.setText("FAILURE!");
						
						// START LOGIN FAILURE DIALOG
						new AlertDialog.Builder(Login.this)
				        .setIcon(android.R.drawable.ic_dialog_alert)
				        .setTitle("Login failed!")
				        .setMessage("Your login credentials could not be verified.  Please try again.")
					    .setNeutralButton("OK", null)
					    .show();
						// END LOGIN FAILURE DIALOG (this will need to be in its own class later)
						
						
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
