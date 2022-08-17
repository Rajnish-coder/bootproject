package com.zee.zee5app.exceptions;

public class InvalidIdException extends Exception {

	//override toString method
	// super call
	public InvalidIdException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + super.getMessage();
	}
	
}
