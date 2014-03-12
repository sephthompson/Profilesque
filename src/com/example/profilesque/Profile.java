package com.example.profilesque;

import com.example.profilesque.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Profile extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		
		TextView txtFN = (TextView) findViewById(R.id.textView1);
		TextView txtLN = (TextView) findViewById(R.id.textView2);
		TextView txtAB = (TextView) findViewById(R.id.textView3);
		TextView txtFNDISP = (TextView) findViewById(R.id.textView4);
		TextView txtLNDISP = (TextView) findViewById(R.id.textView5);
		TextView txtABDISP = (TextView) findViewById(R.id.textView6);
	}
}