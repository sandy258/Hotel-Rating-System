package com.rating.service;

import java.util.List;

import com.rating.exception.RatingNotFoundException;
import com.rating.models.Rating;

public interface RatingService {
	public Rating createRating(Rating rating);
	public Rating getRating(String ratingId) throws RatingNotFoundException;
	public List<Rating> getAllRating();
}
