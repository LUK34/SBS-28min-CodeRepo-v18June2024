package kw.kng.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="REVIEWS")
@NoArgsConstructor
@AllArgsConstructor
public class Review 
{

	@Id
	@GeneratedValue
	private Long id;

	private String rating;

	private String description;
	
	@ManyToOne
	private Course course;
	
	public Review(String rating, String description) 
	{
		this.rating = rating;
		this.description = description;
	}
		
	
	
	/*
	protected Passport()
	{}
	*/
	
	@Override
	public String toString()
	{
		return String.format("Review[%s %s]", rating, description);
	}


}
