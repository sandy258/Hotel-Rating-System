package com.hotel.service;

import java.util.List;

import com.hotel.exception.HotelNotFoundException;
import com.hotel.models.Hotel;

public interface HotelServices {
	public Hotel createHotel(Hotel hotel);
	public Hotel getHotel(String hotelId)  throws HotelNotFoundException;
	public List<Hotel> getAllHotels();
	public Hotel updateHotel(Hotel hotel)throws HotelNotFoundException;
	public Hotel deleteHotel(String hotelId) throws HotelNotFoundException;
}
