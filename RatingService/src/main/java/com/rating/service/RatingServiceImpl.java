package com.rating.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rating.exception.RatingNotFoundException;
import com.rating.models.Rating;
import com.rating.repo.RatingRepo;

@Service
public class RatingServiceImpl implements RatingService{
	
	@Autowired
	private RatingRepo ratingRepo;

	@Override
	public Rating createRating(Rating rating) {
		rating.setRatingId(UUID.randomUUID().toString());
		return ratingRepo.save(rating);
	}

	@Override
	public Rating getRating(String ratingId) throws RatingNotFoundException {
		return ratingRepo.findById(ratingId).orElseThrow(() -> new RatingNotFoundException("Rating Not Found"));
	}

	@Override
	public List<Rating> getAllRating() {
		return ratingRepo.findAll();
	}
	
	public List<Rating> findAllRatingByUserId(String userId){
		return ratingRepo.findAllByUserId(userId);
	}
	
	
	public List<Rating> findAllRatingByHotelId(String hotelId){
		return ratingRepo.findAllByHotelId(hotelId);
	}

}
