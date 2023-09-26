package com.hotel.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotel.exception.HotelNotFoundException;
import com.hotel.models.Hotel;
import com.hotel.repo.HotelRepo;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {
	
	@InjectMocks
	private HotelServicesImpl hotelServicesImpl;
	
	@Mock
	private HotelRepo hotelRepo;
	
	
	@Test
	void hotelCreationSuccessTest() {
		Hotel hotel = Hotel.builder()
		.hotelName("kfc")
		.hotelDescription("good")
		.location("Pune")
		.build();
		when(hotelRepo.save(hotel)).thenReturn(hotel);
		assertEquals(hotel, hotelServicesImpl.createHotel(hotel));
	}
	
	@Test
	void findhotelSuccessTest() throws HotelNotFoundException {
		Hotel hotel = Hotel.builder()
		.hotelName("kfc")
		.hotelDescription("good")
		.location("Pune")
		.build();
		var hotelId="dhajansijdnjednjoi";
		when(hotelRepo.findById(hotelId)).thenReturn(Optional.of(hotel));
		assertEquals(hotel,hotelServicesImpl.getHotel(hotelId));
	}
	
	
	@Test
	void findhotelFailureTest() throws HotelNotFoundException {
		var hotelId="dhajansijdnjednjoi";
		when(hotelRepo.findById(hotelId)).thenReturn(Optional.empty());
		var exception=assertThrows(HotelNotFoundException.class,() ->hotelServicesImpl.getHotel(hotelId));
		assertEquals("Hotel Not Found",exception.getMessage());
	}
	
	
	@Test
	void findNumberOfHotelsTest() throws HotelNotFoundException {
		Hotel hotel = Hotel.builder()
		.hotelName("kfc")
		.hotelDescription("good")
		.location("Pune")
		.build();
		
		List<Hotel> hotels=new ArrayList<>();
		hotels.add(hotel);
		when(hotelRepo.findAll()).thenReturn(hotels);
		assertEquals(1,hotelServicesImpl.getAllHotels().size());
	}
}
