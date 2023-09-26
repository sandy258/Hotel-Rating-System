package com.rating.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Rating {
	@Id
	private String ratingId;
	private String userId;
	private String feedback;
	private String hotelId;
	private int ratingOutOfTen;
	@Transient
	private Hotel hotel;
	
}
