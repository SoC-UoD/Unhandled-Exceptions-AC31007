package com.unhandled_exceptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.StringTokenizer;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;


public class Weekly_Profit_Loss extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_profit_loss);
        
        
        TextView TVProfitLoss = new TextView(this);
    	TVProfitLoss=(TextView)findViewById(R.id.TVProfitLoss);
    	
        
        TextView TVMessage = new TextView(this);
    	TVMessage=(TextView)findViewById(R.id.TVMessage);
    	
    	TVMessage.setText(Portfolio.vaildData + " " + Portfolio.date + "/" + Portfolio.month + "/" + Portfolio.year + " " + Portfolio.hour + ":" + Portfolio.min + ":" + Portfolio.sec);
    	
    	      
    	if ( Portfolio.vaildData == "Downloaded Successful :")
    	{
    	
    		
    	
    	double histTWorth = ((Double.parseDouble(Portfolio.historicalWorth[0]) /100) * 485) + ((Double.parseDouble(Portfolio.historicalWorth[1]) /100) * 1219) + ((Double.parseDouble(Portfolio.historicalWorth[2]) /100) * 258) + ((Double.parseDouble(Portfolio.historicalWorth[3]) /100) * 343) + ((Double.parseDouble(Portfolio.historicalWorth[4]) /100) * 192);
        
        
        if (histTWorth %100 >=50){
        	histTWorth += 1;
        }
        
        int historicWorth = (int)(histTWorth);
        
         
        

        
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
        
        int currentWorth = (int)(tWorth);
        
        
        int profitLoss = currentWorth - historicWorth;
        
        if (profitLoss < 0)
        {
        	TVProfitLoss.setTextColor(android.graphics.Color.RED);
        	TVProfitLoss.setText("- £" + Integer.toString(profitLoss));
        }
        if (profitLoss > 0)
        {
        	TVProfitLoss.setTextColor(android.graphics.Color.GREEN);
        	TVProfitLoss.setText("+ £" + Integer.toString(profitLoss));
        }
        if (profitLoss == 0)
        {
        	TVProfitLoss.setTextColor(android.graphics.Color.WHITE);
        	TVProfitLoss.setText("£" + Integer.toString(profitLoss));
        }
        
    	}
        
        Button btnLeft = (Button) findViewById(R.id.btnLeft);
        Button btnRight = (Button) findViewById(R.id.btnRight);
        
        
        btnLeft.setOnClickListener(new View.OnClickListener() {
        	
        	public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent("com.example.unhandled_exceptions_working.TOTAL_WORTH"));
				finish();
			}
        	
        });
        
        
        
        btnRight.setOnClickListener(new View.OnClickListener() {
        	
        	public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
        	
        });
        
        
        
    }
    
     
    
    
}