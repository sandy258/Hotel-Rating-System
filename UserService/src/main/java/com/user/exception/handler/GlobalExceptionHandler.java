package com.user.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.user.exception.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiResponse> handleUserNotFoundException(UserNotFoundException ex){
		ApiResponse apiResponse = ApiResponse.builder()
		.code(-1)
		.httpStatus(HttpStatus.NOT_FOUND)
		.message(ex.getLocalizedMessage())
		.status("Unsuccessful")
		.build();
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String, String> map=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error ->
		{
			String fieldName=((FieldError)error).getField();
			String message=error.getDefaultMessage();
			map.put(fieldName, message);
		}
		);
		return new ResponseEntity<Map<String,String>>(map,HttpStatus.BAD_REQUEST);
	}

}
