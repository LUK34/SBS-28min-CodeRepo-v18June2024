package kw.kng.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post 
{
	@Id
	@GeneratedValue
	private Long id;
	
	@Size(min=10)
	private String description;

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Users user;
}
