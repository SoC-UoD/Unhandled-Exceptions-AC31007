package com.unhandled_exceptions;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;

import android.os.AsyncTask;
import android.os.Environment;
import android.content.Context;

//usually, subclasses of AsyncTask are declared inside the activity class.
//that way, you can easily modify the UI thread from here
public class Download_Thread extends AsyncTask<String, Integer, String> {

	String success;
	public Context ourContext;
	
	public Portfolio activty;
	
	String[] histWorth = new String[5];
	
	
public Download_Thread(Context c) {
		// TODO Auto-generated constructor stub
	 
	ourContext = c;
	 
	}


@Override
protected void onPreExecute( ) {


	

}


@Override
protected String doInBackground(String... sUrl) {
	   			
	   
	   
   try {
       URL url = new URL(sUrl[0]);
       URLConnection connection = url.openConnection();
       connection.connect();

       //String FILENAME = "Quotes.txt";
       String path = Environment.getExternalStorageDirectory().getPath();
       File file = new File(path,"Quotes.csv");

       
       // download the file
       InputStream input = new BufferedInputStream(url.openStream());
       //OutputStream output = new FileOutputStream("/sdcard/Quotes.txt");
       OutputStream output = new FileOutputStream(file);
       

       byte data[] = new byte[1024];
       int count;
       while ((count = input.read(data)) != -1) {

      	 output.write(data, 0, count);
       }

       output.flush();
       output.close();
       input.close();
           
       downloadHistorical();
       
       
       
       success = "Downloaded Successful :";
           

       

       
   } catch (Exception e) {
	   success = "Download Error :";
   }
   
   return success;
    

}




public void downloadHistorical() {
	
	
	String[] company =  {"BP.L", "EXPN.L", "HSBA.L", "MKS.L", "SN.L"};
	
	Calendar cal = Calendar.getInstance();
	
	
	
	
	//int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	
	//if ( dayOfWeek == 1)
	//{
	//	cal.add(Calendar.DATE, -1);
	//	
	//}
	//if ( dayOfWeek == 2)
	//{
	//	cal.add(Calendar.DATE, -2);
	//	
	//}
	//if ( dayOfWeek == 3)
	//{
	//	cal.add(Calendar.DATE, -3);
	//	
	//}
	//if ( dayOfWeek == 4)
	//{
	//	cal.add(Calendar.DATE, -4);
		
	//}
	//if ( dayOfWeek == 5)
	//{
	//	cal.add(Calendar.DATE, -5);
	//	
	//}
	//if ( dayOfWeek == 6)
	//{
	//	cal.add(Calendar.DATE, -6);
	//	
	//}
	
	
	//ALL ABOVE FORCE CODE REPLACED WITH THE TWO LINES BELOW FOR SAME FUNCTIONALITY
	
	
	cal.add(Calendar.WEEK_OF_YEAR, -1);	
    cal.set(Calendar.DAY_OF_WEEK, 6);
    
    
    
    
    
    int date = cal.get(Calendar.DATE);
    int month = cal.get(Calendar.MONTH);
    int year = cal.get(Calendar.YEAR);
    
    
    
    
    
	
    int x;
	
    for(x = 0; x < 5; x++) {
    	
    
    
	try {
	
		URL url = new URL("http://ichart.yahoo.com/table.csv?s=" + company[x] + "&a=" + month +"&b=" + date + "&c=" + year + "&g=d&ignore=.csv");
	
    URLConnection connection2 = url.openConnection();
    connection2.connect();

    //String FILENAME = "Quotes.txt";
    String path = Environment.getExternalStorageDirectory().getPath();
    File histFile = new File(path,"Historical.csv");

    
    // download the file
    InputStream histInput = new BufferedInputStream(url.openStream());
    OutputStream histOutput = new FileOutputStream(histFile);
    
    
    byte histData[] = new byte[1024];
    int histCount;
    while ((histCount = histInput.read(histData)) != -1) {

 	   histOutput.write(histData, 0, histCount);
    }
    
    
    histOutput.flush();
    histOutput.close();
    histInput.close();
    
    readHistorical(x);
    
    
    
    
	} catch (Exception e) {
		   
	   }
	
	
    }
	
	

	
}



public void readHistorical(int x) {
	
	int record = x;
	
	
	
	String path = Environment.getExternalStorageDirectory().getPath();
    File file = new File(path,"Historical.csv");
	
	
	BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			
			String line;
			int lineNo = 0;

		
			
		    while ((line = br.readLine()) != null) {
		    	
		    	String[] split = line.split(",");
		    	
		    	if (lineNo == 1)
		    	{
		    		histWorth[record] = split[6];		    		
		    	}
		    	
		    	lineNo++;
		    }
		    
		   br.close();
	
		} catch (IOException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
		
		
	
}




@Override
protected void onPostExecute(String success) {

	((Portfolio) ourContext).updateScreen(success, histWorth);
	
	   
}


}