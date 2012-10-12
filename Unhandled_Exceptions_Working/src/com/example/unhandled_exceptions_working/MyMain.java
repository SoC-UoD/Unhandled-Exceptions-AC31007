package com.example.unhandled_exceptions_working;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.view.Menu;
import android.widget.TextView;

public class MyMain extends Activity {

	String[] companies =  {"BP", "EXPERIAN", "HSBC", "MARKS & SPENCER", "SMITH & NEPHEW SN"};
	Dialog d;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_main);
        
     // execute this when the download must be fired
     		DownloadThread downloadFile = new DownloadThread(this);
     		downloadFile.execute("http://finance.yahoo.com/d/quotes.csv?s=BP.L+EXPN.L+HSBA.L+MKS.L+SN.L&f=l9");
     		
     		d = new Dialog(this);
     		d.setTitle("Downloading Data");
     		d.show();
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_my_main, menu);
        return true;
    }
    
    
    
    public void updateScreen(String success){
    	    	
    	d.dismiss();
    	
    	TextView stock1 = new TextView(this);
    	stock1=(TextView)findViewById(R.id.stock1); 
    	
    	TextView update = new TextView(this);
    	update=(TextView)findViewById(R.id.update);
    	
    	TextView companyNames = new TextView(this);
    	companyNames=(TextView)findViewById(R.id.companyNames);
    	
    	TextView total = new TextView(this);
    	total = (TextView)findViewById(R.id.total);
    	
    	
    	StringBuilder text = new StringBuilder();

  		BufferedReader br;
  		try {
  			br = new BufferedReader(new FileReader("/sdcard/Quotes.csv"));
  			
  			String line;

  		    while ((line = br.readLine()) != null) {
  		        text.append(line);
  		        text.append('\n');
  		    }
  		    
  		   br.close();
  		    
  			
  		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		
  		StringBuilder company = new StringBuilder();
  		
  		for(int i = 0; i < companies.length;i++)
  		{
  			company = company.append(companies[i]);
  			company = company.append('\n');
  		}
  		
  		StringBuilder totalAmount = new StringBuilder();
  		 
  		
  		totalAmount = totalAmount.append("Total: £");
  		totalAmount = totalAmount.append(totalWorth());
  		
  		total.setText(totalAmount.toString());
  		companyNames.setText(company);
  		stock1.setText(text);
  		update.setText(success);
	
    	
    }
    
    
    public String totalWorth(){
        
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
      
      for(int i = 0; i < stockWorth.length;i++)
      {
    	  if ((stockWorth[i]/100)%100 >= 50)
    	  {
    		  stockWorth[i] += 1;
    	  }
      }
      
      int tWorth = (int)(((stockWorth[0]/100) * 485) + ((stockWorth[1]/100) * 1219) + ((stockWorth[2]/100) * 258) + ((stockWorth[3]/100) * 343) + ((stockWorth[4]/100) * 192));
      
     
      
      DecimalFormat myFormatter = new DecimalFormat("#,###");
      String totWorthString = myFormatter.format(tWorth);

      
        return totWorthString;
        }
    
}
