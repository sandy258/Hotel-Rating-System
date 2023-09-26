package com.hotel.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.models.Hotel;

public interface HotelRepo extends JpaRepository<Hotel, String> {

}
