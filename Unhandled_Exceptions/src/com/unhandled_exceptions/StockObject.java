package com.unhandled_exceptions;


public class StockObject {

	public static String name;
	public static double sharePrice;
	public static int NumberOfShares;
	
	public StockObject(String newName, double share, int numOfShares){
		name = newName;
		sharePrice = share;
		NumberOfShares = numOfShares;
	}
	
	public static int add() {
		// TODO Auto-generated method stub
		return 6;
	}

	public String getName() {
		
		return name;
	}



}
