package com.example.profilesque;

import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;

public class LoginFailDialog extends DialogFragment {
	
	public LoginFailDialog() {
		// Constructor
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_login, container);
        getDialog().setTitle("Login failed!");

        return view;
    }
	
}