package com.example.profilesque;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.profilesque.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity {
	
	private String resp;
	private String errorMsg;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		final EditText txtEmail = (EditText) findViewById(R.id.editText1);
		final EditText txtPass = (EditText) findViewById(R.id.editText2);
		final EditText txtFirst = (EditText) findViewById(R.id.editText3);
		final EditText txtLast = (EditText) findViewById(R.id.editText4);
		final TextView textViewError = (TextView) findViewById(R.id.textView2);
		textViewError.setMovementMethod(new ScrollingMovementMethod());
		
		Button submitBtn = (Button) findViewById(R.id.button1);		
		
		submitBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Toast.makeText(Register.this, "DEBUG: Button pressed!", Toast.LENGTH_SHORT).show();

				new Thread(new Runnable() {
					public void run() {

						ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
						postParameters.add(new BasicNameValuePair("email", txtEmail.getText().toString()));
						postParameters.add(new BasicNameValuePair("password", txtPass.getText().toString()));
						postParameters.add(new BasicNameValuePair("firstname", txtFirst.getText().toString()));
						postParameters.add(new BasicNameValuePair("lastname", txtLast.getText().toString()));

						String response = null;

						try {
							response = SimpleHttpClient
									.executeHttpPost("http://calm-shore-springmvc-hibernate.herokuapp.com/mobileregister", postParameters);
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
					
					 
					//  Inside the new thread we cannot update the main thread
					//  So updating the main thread outside the new thread
					 

					resp = resp.replace("\n", "");

					// DEBUG OUTPUT
					if (resp.equals("success")) {
						textViewError.setText("SUCCESS!");
						
						// START PROFILE ACTIVITY
						Intent intent = new Intent(Register.this, Profile.class);
						startActivity(intent);
						// END PROFILE ACTIVITY
						
					} else {
						textViewError.setText("NO SCENARIO MATCH: " + resp);
						
						// START SIGNUP FAILURE DIALOG
						new AlertDialog.Builder(Register.this)
				        .setIcon(android.R.drawable.ic_dialog_alert)
				        .setTitle("Registration failed!")
				        .setMessage("Your information was rejected.  Please review and try again.")
					    .setNeutralButton("OK", null)
					    .show();
						// END SIGNUP FAILURE DIALOG (this will need to be in its own class later)
						
						
					} // ERROR OUTPUT
					if (null != errorMsg && !errorMsg.isEmpty()) {
						textViewError.setText("ERRORMSG: " + errorMsg);
					}
				} catch (Exception e) {
					textViewError.setText("EXCEPTION: " + e.getMessage());
				}
			}
		});
		
	}
}