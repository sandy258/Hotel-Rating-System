package com.hotel.exception;

public class HotelNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;

	public HotelNotFoundException(String message) {
		super(message);
	}

}
