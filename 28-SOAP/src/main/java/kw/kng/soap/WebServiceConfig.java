package kw.kng.soap;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;
import org.springframework.ws.soap.security.wss4j2.callback.SimplePasswordValidationCallbackHandler;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter
{
	
	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) 
	{
		MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();
		messageDispatcherServlet.setApplicationContext(context);
		messageDispatcherServlet.setTransformWsdlLocations(true);
		return new ServletRegistrationBean<>(messageDispatcherServlet, "/ws/*");
	}
	
	// /ws/courses.wsdl
		//PortType - CoursePort
		//Namespace - http://in28minutes.com/courses
	// course-details.xsd
	
	
	//Type the below URL in the browser
	// URL:  http://localhost:8080/ws/course_details.wsdl
	@Bean(name = "course_details")
	public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema coursesSchema)
	{
		DefaultWsdl11Definition definition = new DefaultWsdl11Definition(); //Creating an instance of wsdl
		definition.setPortTypeName("CoursePort"); //we will bind this port to service.
		definition.setTargetNamespace("http://kng.kw/courses");
		definition.setLocationUri("/ws");
		definition.setSchema(coursesSchema); //contents defined seperately
		return definition;
	}
	
	@Bean
	public XsdSchema coursesSchema() 
	{
		return new SimpleXsdSchema(new ClassPathResource("course-details.xsd")); //the name of the xsd file called should be specified here
	}
	

    @Bean
    public Wss4jSecurityInterceptor securityInterceptor() 
    {
        Wss4jSecurityInterceptor securityInterceptor = new Wss4jSecurityInterceptor();
        securityInterceptor.setSecurementActions("UsernameToken");
        securityInterceptor.setValidationCallbackHandler(callbackHandler());
 
        return securityInterceptor;
    }
	
    @Bean
    public SimplePasswordValidationCallbackHandler callbackHandler() 
    {
        SimplePasswordValidationCallbackHandler handler = new SimplePasswordValidationCallbackHandler();
        handler.setUsersMap(Collections.singletonMap("user", "password")); //Username:user, Password: password
        return handler;
    }
	
    // Interceptors.add -> XwsSecurityInterceptor
 	@Override
 	public void addInterceptors(List<EndpointInterceptor> interceptors) {
 		interceptors.add(securityInterceptor());
 	}
	

}


/*
 
 1. Type URL : http://localhost:8080/ws/course_details.wsdl in the browser
 
 2. After doing step 1 . You will see a bunch of contents present.
 
 3. The content will contain the following:  messages,  port type, binding and service.
 
 4. Whatever contents are present in the xsd file will be present in wsdl file.
 wsdl file specified the contents that are present in schema.
 
 5. The contents are as follows:
 	
 	5.1)  `<wsdl:types>`
 	The <wsdl:types> element is a container for data type definitions that are used by the web service. 
 	These data type definitions are usually specified in XML Schema (XSD) format.
 	 The <wsdl:types> element allows you to define complex data types that the web service can use for input and output messages.
 
 	5.2) `<wsdl:message>`
 	The <wsdl:message> element defines the data being exchanged between the web service and the client. It basically defines the request and response.
 	Each message consists of one or more parts, which can be simple or complex data types defined in the <wsdl:types> section.
 	
 	5.3) `<wsdl:portType>`
 	The <wsdl:portType> element defines a collection of operations (methods) that the web service exposes. 
 	Each operation corresponds to a specific action that the service can perform, and it specifies the input and output messages involved in the operation
 	
 	5.4) `<wsdl:binding>`
 	The <wsdl:binding> element specifies the concrete protocol and data format specifications for the operations and messages defined in the <wsdl:portType>.
 	 It tells how the service is bound to a specific protocol (e.g., SOAP over HTTP).
 	
 	5.5) `<wsdl:service>`
 	The <wsdl:service> element defines the endpoint (address) for the web service. It specifies where the service can be accessed.
 	
 6.
 When we are going to use a Queue to transfer the requests and response. The style we are using here is document
to indicate the fact that we are actually exchanging complete XML requests and XML response. The alternative for a document is actually something called RPC, which is remote procedure calls.
It’s like calling a procedure which is defined somewhere else. It’s like calling a Java method which is present somewhere else. When we use a RPC, we would need to define
the input parameters and we would need to define the return type. Just like a method. Document is the most frequently used style of SOAP binding.
 	
7. 	
So what should the security interceptor do when it intercepts the request? So a request is coming in, security interceptor intercepts it. What should it do?
It should check the user id and password. Whether they are valid or not.For that we would need to create a callback handler.
In this example, we would be using a simple password validation callback handler. That’s one of the handlers which is provided by spring web services security.

And other thing we would need to provide to the security interceptor is something called the security policy. We’ll create a security policy dot xml to define the security policy and configure it in our interceptor.

That's basically… that's all we would need to enable security on our web service. Let's get started. 
 	
 	
 	
 */


