package kw.kng.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
