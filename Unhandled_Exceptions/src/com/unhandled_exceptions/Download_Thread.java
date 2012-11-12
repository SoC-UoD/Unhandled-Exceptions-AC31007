package com.unhandled_exceptions;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import android.os.AsyncTask;
import android.os.Environment;
import android.content.Context;

//usually, subclasses of AsyncTask are declared inside the activity class.
//that way, you can easily modify the UI thread from here
public class Download_Thread extends AsyncTask<String, Integer, String> {

	String success;
	public Context ourContext;
	
	public Portfolio activty;
	
	
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
       
       success = "Download Successful !!!";

       
   } catch (Exception e) {
	   success = "Download Unsuccessful !!!";
   }
   
   return success;
    

}




@Override
protected void onPostExecute(String success) {

	((Portfolio) ourContext).updateScreen(success);
	
	   
}


}