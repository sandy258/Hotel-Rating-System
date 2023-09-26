package com.rating.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Hotel {
	private String hotelId;
	private String hotelName;
	private String hotelDescription;
	private String location;
}
