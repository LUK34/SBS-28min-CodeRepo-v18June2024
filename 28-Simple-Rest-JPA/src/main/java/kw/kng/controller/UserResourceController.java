package kw.kng.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import kw.kng.dao.UserDao;
import kw.kng.entities.Users;
import kw.kng.exception.UserNotFoundException;
import kw.kng.repo.UsersRepo;

@RestController
public class UserResourceController 
{
	/*
	private UserDao ud;
	
	private UsersRepo urepo;
	
	//---------------------------------------------------------------------------------------------------
						//CONSTRUCTOR INJECTION
	public UserResourceController(UserDao ud)
	{
		this.ud=ud;
	}
	
	//---------------------------------------------------------------------------------------------------
	
	//Before h2
	//URL: http://localhost:8080/users
	@GetMapping("/users")
	public List<Users> retrieveAllUser()
	{
		return ud.findAll();
	}
	
	//Before h2
	// URL: http://localhost:8080/users
	
	@GetMapping("/users/{id}")
	public EntityModel<Users> retrieveUser(@PathVariable int id)
	{
		Users user = ud.findOne(id);
		if(user== null)
		{
			throw new UserNotFoundException("id: "+id);
		}
		EntityModel<Users> entityModel= EntityModel.of(user);
		
		//the below code is a builder that is used to ease building instances to Spring MVC controllers
		WebMvcLinkBuilder link= linkTo(methodOn(this.getClass() ).retrieveAllUser());
		//we are calling the method name `retrieveAllUser` and get the link of this method.
		 entityModel.add(link.withRel("all-users"));
		 
		return entityModel;
	}
	

	//Before h2
	//URL: http://localhost:8080/users
	//Type:DELETE
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id)
	{
		ud.deleteById(id);
	}
	
	
	
	
	
	
	//Before h2
	//URL: http://localhost:8080/users
	//Type: POST
	/*
	 	{
	 		"name":"Al Mulla",
			"birthDate":"1997-08-11"
	 	}
	 */
	
	/*
	  Inorder ot get the value we must GET :localhost:8080/users/4
	 */
	/*
	@PostMapping("/users")
	public ResponseEntity<Users> createUser(@Valid @RequestBody Users user)
	{
		System.out.println("User Details: "+user);
		Users savedUser = ud.save(user);
		// /users/4
		URI location=ServletUriComponentsBuilder
								.fromCurrentRequest()
								.path("/{id}")
								.buildAndExpand(savedUser.getId())
								.toUri(); 
	
		return ResponseEntity.created(location).build();
	}
	*/
	
	/*
	{
		"id":2,
		"name":"Eve",
		"birthDate":"1997-08-11"
	}
	*/
	
	
	
	
	
}

/*
 
 1. Resource is not found => 404
 2. Server exception => 500
 3. Validation error => 400
 4. Success => 200
 5. Created => 201
 6. No Content => 204
 7. Unauthorized ( when authorization fails) => 401
 8. Bad Request ( such as validation error) => 400
 9. Resource Not Found => 404
 10. Server Error => 500
 
 
 
 what is hateoas??
 
HATEOAS (Hypermedia as the Engine of Application State) is a constraint of the REST (Representational State Transfer) application architecture. 
It provides a way for clients to dynamically navigate resources through hyperlinks provided by the server. In other words,
 HATEOAS allows clients to interact with a network of resources without having prior knowledge of the structure or location of those resources.

Here are the key points about HATEOAS:

Hypermedia-Driven: 
The responses from the server contain hypermedia links (usually in the form of URLs) that guide the client on what actions can be performed next.

Dynamic Navigation:
 Clients can discover actions dynamically by following these links, which means they don't need hardcoded paths to resources.

Self-Descriptive Messages: 
The server responses are self-descriptive, meaning the client can understand what actions are available by examining the response.

Decoupling: 
This approach decouples the client and server, allowing the server to evolve independently by changing the URLs or adding new resources without breaking the client.
 
 what is entityModel in hateoas??
 
 n the context of HATEOAS, especially when using Spring HATEOAS in a Spring Boot application, an EntityModel (formerly known as Resource) is a generic container for a domain object (entity) along with a collection of links. This helps to represent the resource in a RESTful API with its associated hypermedia links.

Here’s how it works:

Domain Object: The actual data or entity that you want to represent.
Links: Hypermedia links that guide the client on what actions can be taken or what related resources are available.


 What is Serialization??
 
 Serialization is the process of converting an object to stream.
 
 
 */




