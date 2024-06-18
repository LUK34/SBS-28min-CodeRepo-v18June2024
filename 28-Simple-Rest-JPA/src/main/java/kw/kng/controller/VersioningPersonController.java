package kw.kng.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kw.kng.entities.Name;
import kw.kng.entities.PersonV1;
import kw.kng.entities.PersonV2;

@RestController
public class VersioningPersonController 
{
	//----------------------------------------------------------------------------------------------------------------------------------------------------------
		
														// URI Versioning
			
	//	http://localhost:8080/v1/person
	@GetMapping("/v1/person")
	public PersonV1 getFirstVeriosnOfPerson()
	{
		return new PersonV1("Bob Charlie");
	}
	
	//	http://localhost:8080/v2/person
	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionOfPerson()
	{
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------
	
														// Request Parameter versioning
	
	//	http://localhost:8080/person?version=1
	@GetMapping(path="/person", params="version=1")
	public PersonV1 getFirstVersionOfPersonRequestParameter()
	{
		return new PersonV1("Bob Charlie");
	}
	
	//	http://localhost:8080/person?version=2
	@GetMapping(path="/person", params="version=2")
	public PersonV2 getSecondVersionOfPersonRequestParameter()
	{
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------
	
												// (Custom) headers versioning
	
	//	http://localhost:8080/person/header
	@GetMapping(path="/person/header", headers="X-API-VERSION=1")	//header:X-API-VERSION value=1
	public PersonV1 getFirstVersionOfPersonRequestHeader()
	{
		return new PersonV1("Bob Charlie");
	}
	
	//	http://localhost:8080/person/header
	@GetMapping(path="/person/header", headers="X-API-VERSION=2")	//header:X-API-VERSION value=2
	public PersonV2 getSecondVersionOfPersonRequestHeader()
	{
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	//----------------------------------------------------------------------------------------------------------------------------------------------------------
	
											// Media Type Versioning
	
	//	http://localhost:8080/person/accept
	//header:X-API-VERSION    value=1 ,		header: Accept 		value=application/vnd.company.app-v1+json
	@GetMapping(path="/person/accept", produces="application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfPersonAcceptHeader()
	{
		return new PersonV1("Bob Charlie");
	}

	//	http://localhost:8080/person/accept
	//header:X-API-VERSION    value=2 ,		header: Accept 		value=application/vnd.company.app-v2+json
	@GetMapping(path="/person/accept", produces="application/vnd.company.app-v2+json")
	public PersonV2 getSecondVersionOfPersonAcceptHeader()
	{
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
}
/*
 
 What are the different factors that we must consider for versioning REST API??
 Ans:
 1. URI Pollution:
  When we are doing Request Parameter versioning we will have lot of URI Pollution. 
  So to avoid URI pollution we do versioning REST API.
  
  2.Misuse of HTTP Headers:
  (Custom) Header versioning and Media type versioning will misuse the header so we do Versioning.
 
 3. Caching:
 Typically caching is done on the basis of the url. when it comes to header versioing and media type versioning we will
 use different type of versions. But both the versions will have the same url.
 */