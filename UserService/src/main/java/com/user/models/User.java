package com.user.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
	@Id
	private String userId;
	@NotNull(message = "It Cannot be null")
	private String name;
	@NotNull(message = "It Cannot be null")
	private String email;
	private String about;
	@Transient
	private List<Rating> ratings=new ArrayList<>();
	
}
