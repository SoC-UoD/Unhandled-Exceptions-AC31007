package com.unhandled_exceptions.test;

import com.unhandled_exceptions.StockObject;

import android.test.*;

public class StockObjectTest extends AndroidTestCase{

	private StockObject BritishPetroleum;
	
	public void setup(){
		BritishPetroleum = new StockObject("BP",1.20, 20);
	}
	
	public void checkGetName()
	{
	
	assertEquals("BP", BritishPetroleum.getName());
	}
}
