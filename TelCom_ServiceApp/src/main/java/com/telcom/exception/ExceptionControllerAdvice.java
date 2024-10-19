package com.telcom.exception;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	private final Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> generalExceptionHandler(Exception exception){
		
          ErrorInfo errorinfo = new ErrorInfo();
          errorinfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
          errorinfo.setErrorMessage(exception.getMessage());
          
          return new ResponseEntity<ErrorInfo>(errorinfo,HttpStatus.INTERNAL_SERVER_ERROR);
          
	}
	
	@ExceptionHandler(TelcomException.class)
	public ResponseEntity<ErrorInfo> telcomExceptionHandler(TelcomException exception){
		
          ErrorInfo errorinfo = new ErrorInfo();
          errorinfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
          errorinfo.setErrorMessage(exception.getMessage());
          
          return new ResponseEntity<ErrorInfo>(errorinfo,HttpStatus.BAD_REQUEST);
          
	}
	
	@ExceptionHandler({MethodArgumentNotValidException.class,ConstraintViolationException.class})
	public ResponseEntity<ErrorInfo> exceptionHandler(Exception exception){
		
		  logger.info(exception.getMessage() , exception);
		  
	      String errormessage;
		  
		  if(exception instanceof MethodArgumentNotValidException) {
			  
			  MethodArgumentNotValidException method = (MethodArgumentNotValidException) exception;
			  errormessage = method.getBindingResult().getAllErrors().stream()
					  .map(ObjectError::getDefaultMessage)
					  .collect(Collectors.joining(", "));
		  }else {
			  
			  ConstraintViolationException cve = (ConstraintViolationException) exception;
			  errormessage = cve.getConstraintViolations().stream()
	                .map(violation -> violation.getMessage())
	                .collect(Collectors.joining(", "));
			  
		  }
		
          ErrorInfo errorinfo = new ErrorInfo();
          errorinfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
          errorinfo.setErrorMessage(errormessage);
          
          return new ResponseEntity<ErrorInfo>(errorinfo,HttpStatus.BAD_REQUEST);
          
	}
	

}
