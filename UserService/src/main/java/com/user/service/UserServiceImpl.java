package com.user.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.exception.UserNotFoundException;
import com.user.models.Hotel;
import com.user.models.Rating;
import com.user.models.User;
import com.user.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo repo;
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public User createUser(User user) {
		user.setUserId(UUID.randomUUID().toString());
		return repo.save(user);
	}

	@Override
	public User getUser(String userId) throws UserNotFoundException {
		User user = repo.findById(userId).orElseThrow(()-> new UserNotFoundException("User Not Found"));
		Rating[] ratingArray = restTemplate.getForObject("http://RATING-SERVICE/v1/rating-service/userId/"+user.getUserId(), Rating[].class);
		List<Rating> ratingList = Arrays.stream(ratingArray).toList();
		ratingList.stream().map(rating ->{
			ResponseEntity<Hotel> hotel=restTemplate.getForEntity("http://HOTEL-SERVICE/v1/hotel-service/"+rating.getHotelId(),Hotel.class);
			Hotel hotel2=hotel.getBody();
			rating.setHotel(hotel2);
			return rating;
		}).collect(Collectors.toList());
		
		user.setRatings(ratingList);
		return user;	
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users=repo.findAll();
		for(User user:users) {
			Rating[] ratingArray = restTemplate.getForObject("http://RATING-SERVICE/v1/rating-service/userId/"+user.getUserId(), Rating[].class);
			List<Rating> ratingList = Arrays.stream(ratingArray).toList();
			ratingList.stream().map(rating ->{
				ResponseEntity<Hotel> hotel=restTemplate.getForEntity("http://HOTEL-SERVICE/v1/hotel-service/"+rating.getHotelId(),Hotel.class);
				Hotel hotel2=hotel.getBody();
				rating.setHotel(hotel2);
				return rating;
			}).collect(Collectors.toList());
			
			user.setRatings(ratingList);
		}
		return users;
		
	}

}
