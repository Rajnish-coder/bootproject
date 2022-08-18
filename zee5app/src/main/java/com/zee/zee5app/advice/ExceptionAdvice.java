package com.zee.zee5app.advice;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.zee.zee5app.exceptions.EmailIdExistException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.exceptions.UsernameExistException;

public class ExceptionAdvice {

	@ExceptionHandler(UsernameExistException.class) // to handle the global level exceptions

	public ResponseEntity<?> usernameAlreadyExistExceptionHandler(UsernameExistException e) {
		HashMap<String, String> resData = new HashMap<>();
		resData.put("status", "record already exixts!");
		return ResponseEntity.badRequest().body(resData);
	}

	@ExceptionHandler(EmailIdExistException.class) // to handle the global level exceptions

	public ResponseEntity<?> emailAlreadyExistExceptionHandler(EmailIdExistException e) {
		HashMap<String, String> resData = new HashMap<>();
		resData.put("status", "record already exixts!");
		return ResponseEntity.badRequest().body(resData);
	}

	@ExceptionHandler(UnableToGenerateIdException.class) // to handle the global level exceptions

	public ResponseEntity<?> entryAlreadyExistExceptionHandler(UnableToGenerateIdException e) {
		HashMap<String, String> resData = new HashMap<>();
		resData.put("status", "unable to generate id");
		return ResponseEntity.badRequest().body(resData);
	}

	@ExceptionHandler(NoDataFoundException.class)
	public ResponseEntity<?> noDataFoundExceptionHandler(NoDataFoundException e) {
		HashMap<String, String> resData = new HashMap<>();
		resData.put("status", "no data found!");
		System.out.println("*");
		return ResponseEntity.badRequest().body(resData);
	}

}
