package com.unhandled_exceptions.test;

import com.unhandled_exceptions.StockObject;

import android.test.AndroidTestCase;

public class StockObjectTest extends AndroidTestCase{

	public StockObjectTest(){
		super();
		
		assertEquals(6,StockObject.add());
	}
}
