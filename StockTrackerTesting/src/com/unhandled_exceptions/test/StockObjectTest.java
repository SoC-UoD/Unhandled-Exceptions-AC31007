package com.unhandled_exceptions.test;

import com.unhandled_exceptions.StockObject;

import android.test.AndroidTestCase;

public class StockObjectTest extends AndroidTestCase{

	
	
	public void checkGetName()throws Throwable{
	
	assertEquals("BP", StockObject.getName());
	}
}
