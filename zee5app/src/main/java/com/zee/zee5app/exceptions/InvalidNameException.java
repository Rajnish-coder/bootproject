package com.zee.zee5app.exceptions;


// our custom exception is extending to Exception class so that it becomes checked
// exception
public class InvalidNameException extends Exception {

	public InvalidNameException(String msg) {
		// TODO Auto-generated constructor stub
		super(msg);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + super.getMessage();
	}
}
