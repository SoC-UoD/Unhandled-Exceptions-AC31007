package com.unhandled_exceptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
        
        
        TextView totalWorth = new TextView(this);
    	totalWorth=(TextView)findViewById(R.id.TVTotal);
    	

        TextView TVMessage = new TextView(this);
    	TVMessage=(TextView)findViewById(R.id.TVMessage);
    	
    	TVMessage.setText(Portfolio.vaildData + " " + Portfolio.date + "/" + Portfolio.month + "/" + Portfolio.year + " " + Portfolio.hour + ":" + Portfolio.min + ":" + Portfolio.sec);
        
        
       
        double[] stockWorth = new double[5];
        int x = 0;
        String line;

        try {
        	BufferedReader br = new BufferedReader(new FileReader("/sdcard/Quotes.csv"));
      
        	while ((line = br.readLine()) != null) {
        		stockWorth[x] = Double.parseDouble(line);
        		x++;
        	}    
      
        	br.close();     
      
        	} catch (IOException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        	}
        
        
        
        
        double tWorth = ((stockWorth[0]/100) * 485) + ((stockWorth[1]/100) * 1219) + ((stockWorth[2]/100) * 258) + ((stockWorth[3]/100) * 343) + ((stockWorth[4]/100) * 192);
        
        if (tWorth %100 >=50){
       	 tWorth += 1;
        }
        
        int worth = (int)(tWorth);
         
         DecimalFormat myFormatter = new DecimalFormat("#,###");
         String totWorthString = myFormatter.format(worth);

         
         totalWorth.setText("Total: £" + totWorthString); 
             
    
        
        
        
    }

   
    
    
    
}
