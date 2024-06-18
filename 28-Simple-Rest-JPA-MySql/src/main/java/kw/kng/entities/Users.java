package kw.kng.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="user_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users 
{
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=2, message="Name should have atleast 2 characters")
	@JsonProperty("user_name")
	private String name;

	@Past(message="Birth Date should be in the past")
	@JsonProperty("birth_date")
	private LocalDate birthDate;

	@OneToMany(mappedBy="user")
	private List<Post> posts;
	
	/*protected Users()
	{
		
	}
	 */
}

/*
 There are 2 types of filtering
 
 1. Static Filtering: Same filtering for a bean across different REST API.
 	@JsonIgnoreProperties, @JsonIgnore

 2. Dynamic Filtering: Customize filtering for a bean for a specific REST API
 	@JsonFilter with FilterProvider
 	
 	
 
 */

