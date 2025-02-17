package kw.kng.controller;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kw.kng.entities.HelloWorldBean;

@RestController
public class HelloWorldController 
{
	private MessageSource messageSource;
	
	public HelloWorldController(MessageSource messageSource)
	{
		this.messageSource=messageSource;
	}
	
	
	//Before enabling H2 db
	//http:localhost:8080/hello-world
	@RequestMapping(method=RequestMethod.GET, path="/hello-world")
	public String helloWorld()
	{
		return "Hello World";
	}
	
	
	//Before enabling H2 db
	//http:localhost:8080/hello-world/path-variable/Sanga
	@GetMapping(path="/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name)
	{
		return new HelloWorldBean(String.format("Hello World,  %s",name));
	}

	//http:localhost:8080/hello-world-int
	@GetMapping("/hello-world-int")
	public String helloWorldInternationalized() {
		Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage("good.morning.message", null, "Default Message", locale );
		
		//return "Hello World V2"; 
		
		//1:
		//2:
//		- Example: `en` - English (Good Morning)
//		- Example: `nl` - Dutch (Goedemorgen)
//		- Example: `fr` - French (Bonjour)
//		- Example: `de` - Deutsch (Guten Morgen)

	}

	
	
	
}

