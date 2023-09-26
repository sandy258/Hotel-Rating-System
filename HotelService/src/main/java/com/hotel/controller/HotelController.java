package com.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.exception.HotelNotFoundException;
import com.hotel.models.Hotel;
import com.hotel.service.HotelServices;

import jakarta.validation.Valid;

@RestController
public class HotelController {
	@Autowired
	private HotelServices hotelServices;
	
	@PostMapping
	ResponseEntity<Hotel> createHotel(@Valid  @RequestBody Hotel hotel){
		return new ResponseEntity<>(hotelServices.createHotel(hotel),HttpStatus.CREATED);
	}
	
	@GetMapping("/{hotelId}")
	ResponseEntity<Hotel> getHotel(@PathVariable String hotelId) throws HotelNotFoundException{
		return new ResponseEntity<>(hotelServices.getHotel(hotelId),HttpStatus.OK);
	}
	
	@GetMapping
	ResponseEntity<List<Hotel>> getAllHotels() {
		return new ResponseEntity<>(hotelServices.getAllHotels(),HttpStatus.OK);
	}
	
	
}
