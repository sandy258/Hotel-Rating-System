package com.hotel.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Hotel {
	@Id
	private String hotelId;
	@NotNull(message = "It cannot be null")
	private String hotelName;
	private String hotelDescription;
	private String location;
}
