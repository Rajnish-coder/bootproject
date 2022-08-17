package com.zee.zee5app.exceptions;

public class UnableToGenerateIdException extends Exception {

	public UnableToGenerateIdException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}
}
