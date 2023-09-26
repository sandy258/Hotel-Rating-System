package com.rating.exception;

public class RatingNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	public RatingNotFoundException(String message) {
		super(message);
	}

}
