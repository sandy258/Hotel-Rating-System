package com.hotel.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.exception.HotelNotFoundException;
import com.hotel.models.Hotel;
import com.hotel.repo.HotelRepo;

@Service
public class HotelServicesImpl  implements HotelServices{
	@Autowired
	private HotelRepo hotelRepo;

	@Override
	public Hotel createHotel(Hotel hotel) {
		hotel.setHotelId(UUID.randomUUID().toString());
		return hotelRepo.save(hotel);
	}

	@Override
	public Hotel getHotel(String hotelId) throws HotelNotFoundException {
		return hotelRepo.findById(hotelId).orElseThrow(()->new HotelNotFoundException("Hotel Not Found"));
	}

	@Override
	public List<Hotel> getAllHotels() {
		return hotelRepo.findAll();
	}
	
	public Hotel updateHotel(Hotel hotel) throws HotelNotFoundException {
		Hotel hotel2=getHotel(hotel.getHotelId());
		hotel2.setHotelDescription(hotel.getHotelDescription());
		hotel2.setHotelName(hotel.getHotelName());
		hotel2.setLocation(hotel.getLocation());
		return hotelRepo.save(hotel2);
	}
	
	public Hotel deleteHotel(String hotelId) throws HotelNotFoundException {
		Hotel hotel=getHotel(hotelId);
		hotelRepo.delete(hotel);
		return hotel;
	}
}
