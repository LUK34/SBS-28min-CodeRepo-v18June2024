package kw.kng.soap.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode=FaultCode.CUSTOM, customFaultCode="{http://kng.kw/courses}001_COURSE_NOT_FOUND")
public class CourseNotFoundException extends RuntimeException 
{
	
	private static final long serialVersionUID = 1L;

		public CourseNotFoundException(String message)
		{
			super(message);
		}
		

}


//Select wizdler option -> GetCourseDetails


/*
 	The @SoapFault annotation is used in Java to define custom SOAP fault details for exceptions in web service applications. 
 	Specifically, it is used with the JAX-WS (Java API for XML Web Services) framework.

Here's what the annotation and its parameters do:

@SoapFault Annotation
Purpose: 
The @SoapFault annotation is used to map a Java exception to a SOAP fault, specifying the fault code and optionally the fault string, 
fault actor, and detail elements.
Usage:
 It is typically applied to custom exception classes in a web service to indicate how these exceptions should be represented in the SOAP fault message.
Parameters
faultCode: This specifies the fault code to be used in the SOAP fault message. 
The fault code indicates the nature of the fault. Common values include:

FaultCode.CLIENT (or javax.xml.ws.soap.SOAPFaultException.FAULT_CODE_CLIENT):
 Indicates that the fault was caused by the client, such as by sending an invalid request.

FaultCode.SERVER (or javax.xml.ws.soap.SOAPFaultException.FAULT_CODE_SERVER): 
Indicates that the fault was caused by the server.

FaultCode.MUST_UNDERSTAND: 
Indicates that the SOAP message contained a header element that the server could not process.

FaultCode.VERSION_MISMATCH:
 Indicates that there was a version mismatch in the SOAP message.


 */