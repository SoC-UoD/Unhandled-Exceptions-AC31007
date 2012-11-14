package com.unhandled_exceptions.test;

import android.test.AndroidTestCase;
import com.unhandled_exceptions.Download_Thread;

public class Download_ThreadTest extends AndroidTestCase {
	
	public Download_ThreadTest()
	{
		super();
		
		assertEquals(6,Download_Thread.add());
	}

	
}
