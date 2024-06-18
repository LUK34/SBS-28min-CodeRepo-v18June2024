package kw.kng.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import kw.kng.entities.SomeBean;

@RestController
public class FilteringController 
{
	// http:localhost:8080/filtering
	@GetMapping("/filtering")
	public MappingJacksonValue filtering()
	{
		SomeBean someBean = new SomeBean("value1", "value2", "value3");		
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");//This will filter out the attribute having column name as `field1` and `field3`
		
		FilterProvider filters= new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		mappingJacksonValue.setFilters(filters);
		
		return mappingJacksonValue;
	}
	
	// http:localhost:8080/filtering-list
	@GetMapping("/filtering-list") //field2, field1
	public MappingJacksonValue filteringList()
	{
		List<SomeBean> list= Arrays.asList(new SomeBean("value1","value2","value3"),
																 new SomeBean("value4","value5","value6"));
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");//This will filter out the attribute having column name as `field1` and `field3`
		
		FilterProvider filters= new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		mappingJacksonValue.setFilters(filters);
		
		return mappingJacksonValue;
	}
	
}


/*

 1. What is MappingJacksonValue?? What does it do??
 
 Ans:
 MappingJacksonValue is a class in the Spring Framework used to wrap an object that you want to serialize to JSON,
 allowing you to configure various properties of the serialization process. It provides a way to control the JSON serialization using Jackson, 
 which is the default JSON processor for Spring.

Here's a breakdown of what it does and how it's used:

Wraps the Object: 
It takes the object you want to serialize (in this case, someBean) and wraps it. 
This allows you to add additional configuration to the serialization process.

Serialization Filters:
 You can use MappingJacksonValue to apply serialization filters. 
 This is particularly useful if you want to include or exclude certain fields from the JSON output based on some criteria.
  For example, you can apply a Jackson filter to dynamically filter properties during serialization.



2. What are SpringBoot actuators??

localhost:8080/actuator
Ans:
Spring Boot Actuator: Provides Spring Boots production-ready features.
	-> Monitor and manage your application in your production.
	
this provides number of endpoints:
 	beans -Complete list of Spring beans in your app.
	health- Application health information
	metric- Application metrics
	mappings- Details around Request Mappings
	
3. What is HAL(JSON Hypertext Application Language) ?

Ans:
->	Simple format that gives a consistent and easy way to hyperlink between resources in your API.
-> An API explorer for RESTFul Hypermedia API using HAL
-> Enable your non-technical teams to play with APIs
-> Spring Boot HAL Explorer
	-> Auto - configures HAL Explorer for Spring Boot Projects.

3. Difference between HAL and OpenAPI??

Ans:

	API development involves the use of different tools and formats to achieve specific goals. OpenAPI is a specification that provides a standardized way of building and documenting RESTful APIs.
It enables developers to design, document, and consume APIs in a structured format using JSON or YAML. On the other hand, HAL is a hypermedia format that adds hypermedia controls to JSON representations of resources, making APIs more discoverable and navigable.

HAL Explorer is a tool that allows developers to interact with HAL-based APIs by exploring resources and following links provided within the responses.
While OpenAPI is focused on defining and documenting APIs, HAL enables hypermedia controls in API responses, and HAL Explorer is a tool for interacting with APIs that implement the HAL format.
Depending on the project's needs, developers can use OpenAPI for API design and documentation and HAL Explorer to work with APIs that use the HAL hypermedia format.

I hope the above information helps, am happy to assist you further.
	
	
	
 */