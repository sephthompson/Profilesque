package com.example.profilesque;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import com.example.profilesq.R;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
    public void onClick(View v) {
		
		final EditText eTxtUser = (EditText) findViewById(R.id.editText1);
		final EditText eTxtPass = (EditText) findViewById(R.id.editText2);
 
        switch (v.getId()) {
        case R.id.button1:
        	
        	Toast.makeText(LoginActivity.this, "Button press recognized.", Toast.LENGTH_SHORT).show();
 
              new Thread(new Runnable() {
                    public void run() {
 
                        try {
                            URL url = new URL("http://calm-shore-springmvc-hibernate.herokuapp.com/MobileLogin");
                            URLConnection connection = url.openConnection();
 
                            String inputStringUser = eTxtUser.getText().toString();
                            String inputStringPass = eTxtPass.getText().toString();
                            //inputString = URLEncoder.encode(inputString, "UTF-8");
 
                            Log.d("inputStringUser", inputStringUser);
                            Log.d("inputStringPass", inputStringPass);
 
                            connection.setDoOutput(true);
                            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                            out.write(inputStringUser);
                            out.write(inputStringPass);
                            
                            Toast.makeText(LoginActivity.this, "out.write has been performed.", Toast.LENGTH_SHORT).show();
                            
                            out.close();
 
                            } catch(Exception e)
                            {
                                Log.d("Exception",e.toString());
                                Toast.makeText(LoginActivity.this, "DEBUG: " + e, Toast.LENGTH_SHORT).show();
                            }
 
                    }
                  }).start();
 
            break;
            }
        }

}
