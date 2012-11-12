package com.unhandled_exceptions;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;


public class Total_Worth extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_worth);
        
        Button btnLeft = (Button) findViewById(R.id.btnLeft);
        Button btnRight = (Button) findViewById(R.id.btnRight);
        
        
        btnLeft.setOnClickListener(new View.OnClickListener() {
        	
        	public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
        	
        });
        
        
        
        btnRight.setOnClickListener(new View.OnClickListener() {
        	
        	public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent("com.example.unhandled_exceptions_working.WEEKLY_PROFIT_LOSS"));
				finish();
			}
        	
        });
        
        
        
        
    }

   
    
    
    
}
