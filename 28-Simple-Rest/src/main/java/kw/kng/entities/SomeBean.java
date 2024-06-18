package kw.kng.entities;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonFilter("SomeBeanFilter")
public class SomeBean 
{
	
	private String field1;
	
	//@JsonIgnore
	private String field2;
	private String field3;
	
}
