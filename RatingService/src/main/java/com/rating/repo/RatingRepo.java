package com.rating.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rating.models.Rating;

public interface RatingRepo extends JpaRepository<Rating, String>{
	public List<Rating> findAllByUserId(String userId);
	public List<Rating> findAllByHotelId(String hotelId);
}
