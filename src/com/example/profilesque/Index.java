package com.example.profilesque;

import com.example.profilesque.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Index extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.index);
		
		final TextView txtV1 = (TextView) findViewById(R.id.textView1);
		final TextView txtV2 = (TextView) findViewById(R.id.textView2);
		final Button btnLogin = (Button) findViewById(R.id.button1);
		final Button btnSignup = (Button) findViewById(R.id.button2);
		
		btnLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Index.this, Login.class);
				startActivity(intent);
			}
		});
		
		btnSignup.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Index.this, Register.class);
				startActivity(intent);
			}
		});
	}
	
}
