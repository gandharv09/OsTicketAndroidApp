package com.example.jsonrequest;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class Display extends Activity {
	 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.display);
		
		Intent intent = getIntent();
		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE); 
		
		 TextView textView = (TextView) findViewById(R.id.textview);
		 //new TextView(this);
		 Log.i("hello","test3");
		    textView.setTextSize(40);
		    textView.setText(message);

		    // Set the text view as the activity layout
		// Show the Up button in the action bar.
		
        
		
	}}