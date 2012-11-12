package com.unhandled_exceptions;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class Portfolio extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
        

        
        //execute this when the download must be fired
 		Download_Thread downloadFile = new Download_Thread(this);
 		downloadFile.execute("http://finance.yahoo.com/d/quotes.csv?s=BP.L+EXPN.L+HSBA.L+MKS.L+SN.L&f=l9");
        
       
        
        //BUTTON CONTROL STARTS HERE................
        
        Button btnLeft = (Button) findViewById(R.id.btnLeft);
        Button btnRight = (Button) findViewById(R.id.btnRight);
        
        
        btnLeft.setOnClickListener(new View.OnClickListener() {
        	
        	public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent("com.example.unhandled_exceptions_working.WEEKLY_PROFIT_LOSS"));

				
			}
        	
        });
        
        
        
        btnRight.setOnClickListener(new View.OnClickListener() {
        	
        	public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent("com.example.unhandled_exceptions_working.TOTAL_WORTH"));
				
				
			}
        	
        });
        
        
        //BUTTON CONTROL STOPS HERE.................
        
        
    }
   
    
    
}
