package kw.kng.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

//@Entity
@Data
@AllArgsConstructor
public class Users 
{
	private Integer id;
	
	@Size(min=2, message="Name should have atleast 2 characters")
	@JsonProperty("user_name")
	private String name;

	@Past(message="Birth Date should be in the past")
	@JsonProperty("birth_date")
	private LocalDate birthDate;

}

/*
 There are 2 types of filtering
 
 1. Static Filtering: Same filtering for a bean across different REST API.
 	@JsonIgnoreProperties, @JsonIgnore

 2. Dynamic Filtering: Customize filtering for a bean for a specific REST API
 	@JsonFilter with FilterProvider
 	
 	
 
 */

