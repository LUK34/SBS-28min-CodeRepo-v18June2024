package kw.kng.soap;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import kw.kng.courses.CourseDetails;
import kw.kng.courses.DeleteCourseDetailsRequest;
import kw.kng.courses.DeleteCourseDetailsResponse;
import kw.kng.courses.GetAllCourseDetailsRequest;
import kw.kng.courses.GetAllCourseDetailsResponse;
import kw.kng.courses.GetCourseDetailsRequest;
import kw.kng.courses.GetCourseDetailsResponse;
import kw.kng.soap.bean.Course;
import kw.kng.soap.exception.CourseNotFoundException;
import kw.kng.soap.service.CourseDetailService;
import kw.kng.soap.service.CourseDetailService.Status;

@Endpoint
public class CourseDetailsEndpoint 
{
	//methods
	//input - GetCourseDetailsRequest
	//output - GetCourseDetailsResponse
	
	@Autowired
	private CourseDetailService cds;
	
	
	@PayloadRoot( namespace="http://kng.kw/courses" ,
			   localPart="GetAllCourseDetailsRequest")
	@ResponsePayload
	public GetAllCourseDetailsResponse processAllCourseDetailsRequest (@RequestPayload GetAllCourseDetailsRequest request)
	{
		List<Course> courses= cds.findAll();
		
		return mapAllCourseDetails(courses);
	}
	
	
	@PayloadRoot( namespace="http://kng.kw/courses" ,
							   localPart="GetCourseDetailsRequest")
	@ResponsePayload
	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request)
	{
		
		Course course=cds.findById(request.getId());
		if (course == null)
		{
			throw new CourseNotFoundException("Invalid Course Id " + request.getId());
		}
		return mapCourseDetails(course);
	}
	

	@PayloadRoot( namespace="http://kng.kw/courses" ,
			   localPart="DeleteCourseDetailsRequest")
	@ResponsePayload
	public DeleteCourseDetailsResponse deleteCourseDetailsRequest(@RequestPayload DeleteCourseDetailsRequest request) {

		Status status = cds.deleteById(request.getId());

		DeleteCourseDetailsResponse response = new DeleteCourseDetailsResponse();
		response.setStatus(mapStatus(status));

		return response;
	}
	
	private GetCourseDetailsResponse mapCourseDetails(Course course) //Get Specific course details
	{
		GetCourseDetailsResponse response = new GetCourseDetailsResponse();
		response.setCourseDetails(mapCourse(course)); //calls the `mapCourse` method which will set and return the value.
		return response;
	}

	private GetAllCourseDetailsResponse mapAllCourseDetails(List<Course> courses)  //Get all Course Details
	{
		GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
		for (Course course : courses) {
			CourseDetails mapCourse = mapCourse(course);//calls the `mapCourse` method which will set and return the value.
			response.getCourseDetails().add(mapCourse);
		}
		return response;
	}
	
	
	// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
																//COMMON METHODS are DEFINED HERE

	//`mapCourse`  -> COMMON  METHOD DEFINED HERE
	private CourseDetails mapCourse(Course course) //Map the course details on the basis of setters from `CourseDetails.java` generated from xsd file
	{
		CourseDetails courseDetails = new CourseDetails();

		courseDetails.setId(course.getId());
		
		courseDetails.setName(course.getName());

		courseDetails.setDescription(course.getDescription());
		return courseDetails;
	}

	//`mapStatus`  -> COMMON  METHOD DEFINED HERE	
	/*
	 	The `Status` which is present as an argument will go and map with `Status` present in enum.
	 
	 */
	private kw.kng.courses.Status mapStatus(Status status) 
	{
		if (status == Status.FAILURE)
		{
			return kw.kng.courses.Status.FAILURE;
		}
			
		return  kw.kng.courses.Status.SUCCESS;
	}
	
	
	// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
}


/*
 The provided code is a Java class that defines a SOAP web service endpoint using Spring Web Services (Spring-WS). 
 It's set up to handle SOAP requests and responses specifically for fetching details about a course.
 Here's a breakdown of the main components and what they do:

1. Package and Imports
---------------------------------------
1.1 Package Declaration:
 	package kw.kng.soap; — This line defines the package name of the class, which helps organize the code and avoid naming conflicts.
1.2 Imports: 
	These statements import the necessary classes and annotations from various libraries, particularly Spring Framework's Web Services support
	and some custom classes representing the courses.

2. Annotations and Class Definition
-------------------------------------------------
2.1 @Endpoint: 
	This annotation marks the class as a Spring-WS endpoint, which means it will handle SOAP messages.

2.2 Class Definition:
 		public class CourseDetailsEndpoint — Defines a public class that serves as the SOAP endpoint.

3.Method and Its Annotations
------------------------------------------------
3.1 @PayloadRoot: 
Specifies the namespace and the local part (name) of the SOAP message this method should handle.
 In this case, the method will be triggered when a SOAP message with the namespace http://kng.kw/courses 
 and the local part GetCourseDetailsRequest is received.

3.2 @ResponsePayload: 
	Indicates that the return value of the method should be mapped to the payload of the SOAP response.

3.3 @RequestPayload: 
Marks the method's parameter as the payload of the SOAP request, which means the incoming SOAP message
 will be unmarshalled (converted from XML to Java object) into a GetCourseDetailsRequest object.

4. Method Implementation
-----------------------------------------------
4.1 Method Signature:
 	public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) — 
 	This method takes a GetCourseDetailsRequest object as input and returns a GetCourseDetailsResponse object.

4.2 Response Creation:
 	Inside the method, a new GetCourseDetailsResponse object is created. This object will be populated and returned as the SOAP response.

4.3 Setting Course Details:
A new CourseDetails object is instantiated.The course ID is extracted from the request and set on the courseDetails object.
The name and description of the course are hardcoded ("Microservices Course" and a description) and set on the courseDetails object.
The courseDetails object is then added to the response object.

4.4 Return: 
The GetCourseDetailsResponse object, now containing the course details, is returned. This will be serialized into XML and sent back as the SOAP response.
This setup is typical for Spring-WS applications where you need to handle specific types of SOAP requests and generate appropriate SOAP responses.
 The use of annotations makes the configuration largely declarative and integrates cleanly with the rest of the Spring ecosystem.


5. Remember you must use the wizdler extension to `DELETE` and `POST`.
Use `POST`  and enter the value as 2 and then click on the `GO` button


 */


