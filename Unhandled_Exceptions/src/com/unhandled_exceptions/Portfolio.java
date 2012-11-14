package com.unhandled_exceptions;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;

public class Portfolio extends Activity {
	
	
	String[] companies =  {"BP", "EXPERIAN", "HSBC", "MARKS & SPENCER", "SMITH & NEPHEW"};
	Dialog d;
	static int ran;
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
        

        
        //execute this when the download must be fired
 		Download_Thread downloadFile = new Download_Thread(this);
 		downloadFile.execute("http://finance.yahoo.com/d/quotes.csv?s=BP.L+EXPN.L+HSBA.L+MKS.L+SN.L&f=l9");
        
 		//Download dialog starts
 		d = new Dialog(this);
 		d.setTitle("Downloading Data");
 		d.show();
 		
 		ran = 6;
       
        
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
    
    
    
    public void updateScreen(String success){
    	
    	d.dismiss();
    	
    	TextView companyNames = new TextView(this);
    	companyNames=(TextView)findViewById(R.id.TVCompanyNames);
    	
    	TextView stockPrices = new TextView(this);
    	stockPrices=(TextView)findViewById(R.id.TVPrices);
    	
    	StringBuilder company = new StringBuilder();
  		
  		for(int i = 0; i < companies.length;i++)
  		{
  			company = company.append(companies[i]);
  			company = company.append('\n');
  		}
    	
  		companyNames.setText(company);
  		
  		
  		
  		
  		StringBuilder prices = new StringBuilder();

  		BufferedReader br;
  		try {
  			br = new BufferedReader(new FileReader("/sdcard/Quotes.csv"));
  			
  			String line;

  		    while ((line = br.readLine()) != null) {
  		    	prices.append(line);
  		    	prices.append('\n');
  		    }
  		    
  		   br.close();
  		    
  			
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		
  		
  		stockPrices.setText(prices);
  		
  		
  		
	
    	
    }



	public int hasItRan() {
		// TODO Auto-generated method stub
		return ran;
	}
   
    
    
}
