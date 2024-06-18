package kw.kng.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="PASSPORT")
@NoArgsConstructor
@AllArgsConstructor
public class Passport 
{
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable=false)
	private String number;
	
	@OneToOne(fetch=FetchType.LAZY,  mappedBy="passport")
	private Student student;
	
	/*
	protected Passport()
	{}
*/
	
	public Passport(String number)
	{
		this.number= number;
	}
	

	@Override
	public String toString()
	{
		return String.format("Passport[%s]", number);
	}
}