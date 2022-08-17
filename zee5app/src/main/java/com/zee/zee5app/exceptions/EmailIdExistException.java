package com.zee.zee5app.exceptions;

public class EmailIdExistException extends Exception {

	public EmailIdExistException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + super.getMessage();
	}
	
}
