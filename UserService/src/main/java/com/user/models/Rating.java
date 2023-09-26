package com.user.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Rating {
	private String ratingId;
	private String userId;
	private String feedback;
	private String hotelId;
	private int ratingOutOfTen;
	private Hotel hotel;
}
