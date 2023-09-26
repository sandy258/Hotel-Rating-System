package com.rating.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rating.exception.RatingNotFoundException;
import com.rating.models.Rating;
import com.rating.service.RatingServiceImpl;

@RestController
public class RatingController {
	@Autowired
	private RatingServiceImpl ratingServicesImpl;
	
	@PostMapping
	ResponseEntity<Rating> createRating(@RequestBody Rating Rating){
		return new ResponseEntity<>(ratingServicesImpl.createRating(Rating),HttpStatus.CREATED);
	}
	
	@GetMapping("/{ratingId}")
	ResponseEntity<Rating> getRating(@PathVariable String ratingId) throws RatingNotFoundException{
		return new ResponseEntity<>(ratingServicesImpl.getRating(ratingId),HttpStatus.OK);
	}
	
	@GetMapping
	ResponseEntity<List<Rating>> getAllRatings() {
		return new ResponseEntity<>(ratingServicesImpl.getAllRating(),HttpStatus.OK);
	}
	
	@GetMapping("/hotelId/{hotelId}")
	ResponseEntity<List<Rating>> getAllRatingsByHotelId(@PathVariable String hotelId) {
		return new ResponseEntity<>(ratingServicesImpl.findAllRatingByHotelId(hotelId),HttpStatus.OK);
	}
	
	@GetMapping("/userId/{userId}")
	ResponseEntity<List<Rating>> getAllRatingsByUserId(@PathVariable String userId) {
		return new ResponseEntity<>(ratingServicesImpl.findAllRatingByUserId(userId),HttpStatus.OK);
	}
	
}
