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
}
