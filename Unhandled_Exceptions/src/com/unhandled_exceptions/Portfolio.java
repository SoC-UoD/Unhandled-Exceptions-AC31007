package com.unhandled_exceptions;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;

public class Portfolio extends Activity {
	
	public static String[] historicalWorth;
	String[] companies =  {"BP", "EXPERIAN", "HSBC", "MARKS&SPENCER", "SMITH&NEPHEW"};
	int[] stockNos = {192, 258, 343, 458, 1219};
	Dialog d;
	public static String vaildData;
	public static int date, month , year, hour, min, sec;
	Calendar cal;
	

	public Stock_Object[] stockObjects;

	

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
    
    
    
    public void updateScreen(String success, String [] histWorth){
    	
    	creatStockObjects();
    	
    	historicalWorth = histWorth; 
    	
    	d.dismiss();
    	
    	cal = Calendar.getInstance();
    	date = cal.get(Calendar.DATE);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);
        hour = cal.get(Calendar.HOUR);
        min = cal.get(Calendar.MINUTE);
        sec = cal.get(Calendar.SECOND); 
    	
    	vaildData = success;
    	
    	TextView companyNames = new TextView(this);
    	companyNames=(TextView)findViewById(R.id.TVCompanyNames);
    	
    	TextView stockPrices = new TextView(this);
    	stockPrices=(TextView)findViewById(R.id.TVPrices);
    	
    	TextView TVMessage = new TextView(this);
    	TVMessage=(TextView)findViewById(R.id.TVMessage);
    	
    	TVMessage.setText(vaildData + " " + date + "/" + month + "/" + year + " " + hour + ":" + min + ":" + sec);
    	
    	
  		companyNames.setText("BP"  + '\n' +  "EXPERIAN" + '\n' +  "HSBC" + '\n' +  "MARKS & SPENCER" + '\n' +  "SMITH & NEPHEW");
  		
  		
  		
  		
  		StringBuilder prices = new StringBuilder();

  		for (int i = 0; i < 5; i++)
  		{
  			prices.append("£");
	    	prices.append(stockObjects[i].getSharePrice());
	    	prices.append('\n');
  		}
  		
  		
  		stockPrices.setText(prices);
  		
	
    	
    }
    
    
    public void creatStockObjects() {
    	
    	stockObjects = new Stock_Object[5];
    	
    	for(int i = 0; i < 5; i++)
    	{

    		stockObjects[i].setName(companies[i]);
    		stockObjects[i].setStockNo(stockNos[i]);   				
    				
    	}
    	
    	int x = 0;
    	
    	BufferedReader br;
  		try {
  			br = new BufferedReader(new FileReader("/sdcard/Quotes.csv"));
  			
  			String line;
  			
  		    while ((line = br.readLine()) != null) {
  		    	stockObjects[x].setSharePrice(line);
  		    	x++;
  		    	
  		    }
  		    
  		   br.close();
  		    
  			
  		} catch (IOException e) {
  			System.out.println("Something not working");
  			e.printStackTrace();
  		}
    	
    	
    }
    
  
    
    
    
    
   
    
    
}