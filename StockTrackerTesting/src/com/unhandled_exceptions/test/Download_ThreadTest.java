package com.unhandled_exceptions.test;

import android.test.AndroidTestCase;
//import com.unhandled_exceptions.Download_Thread;
import com.unhandled_exceptions.Portfolio;

public class Download_ThreadTest extends AndroidTestCase {
	
	
	
	public Download_ThreadTest()
	{
		super();
		
		
		
		
		
		
 		
		
		
	}
	

	protected void setup() throws Exception{
		super.setUp();
		
		Portfolio port = new Portfolio();
		
		
		
		assertEquals(6,port.hasItRan());
	}
	
	
	
}
