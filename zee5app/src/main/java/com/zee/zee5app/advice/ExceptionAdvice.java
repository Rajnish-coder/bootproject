package com.zee.zee5app.advice;

import java.util.HashMap;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.zee.zee5app.exceptions.EmailIdExistException;
import com.zee.zee5app.exceptions.NoDataFoundException;
import com.zee.zee5app.exceptions.UnableToGenerateIdException;
import com.zee.zee5app.exceptions.UsernameExistException;
import com.zee.zee5app.exceptions.apierror.ApiError;


@ControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

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
		resData.put("status", e.getMessage());
		return ResponseEntity.badRequest().body(resData);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage("Validation error");
		apiError.addValidationErrors(ex.getBindingResult().getFieldErrors()); // fieldwise errors
		apiError.addValidationError(ex.getBindingResult().getGlobalErrors());
		return buildResponseEntity(apiError);
	}
	
	private ResponseEntity<Object> buildResponseEntity(ApiError	apiError){
		// to get which RE object === > if I wnat to make any changes into our existing object then in every return we have to do the change 
		// instead of that if we will use buildRE method ===> EOM.
		return new ResponseEntity<>(apiError,apiError.getHttpStatus());
	}

}
