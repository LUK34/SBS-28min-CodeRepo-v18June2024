package kw.kng.runner;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import kw.kng.entities.Review;
import kw.kng.repo.CourseRepo;

@Component
public class CourseRunner  implements CommandLineRunner
{
	
	private Logger logger= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepo crepo;

	@Override
	public void run(String... args) throws Exception
	{
		// TODO Auto-generated method stub
		/*	
		
		System.out.println(" ---------------------------------------------------------------------------------------");
		System.out.println(" Using findById using Entity Manager:");
		Course crse= crepo.findById(1001L);
		logger.info("Course 1000{} -> ", crse);
		
	System.out.println(" ---------------------------------------------------------------------------------------");
	 System.out.println(" Using deleteById using Entity Manager:");	
	 long courseId = 1003L; 
	    if (crepo.deleteById(courseId))
	    {
	        System.out.println("Course with id "+ courseId + " deleted successfully.");
	    }
	    else 
	    {
	        System.out.println("Course with id "+ courseId +" not found.");
	    }
	    
	   
		System.out.println(" ---------------------------------------------------------------------------------------");
		Course savedCourse=	crepo.save(new Course("Microservices in 100 steps"));
	    System.out.println("Course with id "+savedCourse.getId()+"  inserted successfully");
	  
	    System.out.println(" ---------------------------------------------------------------------------------------");
	    System.out.println("Play with Entity Manager:");
	  //  crepo.playWithEntityManager();
	    crepo.playWithEntityManager2();
	    System.out.println(" ---------------------------------------------------------------------------------------");
	    */
		
		
		//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
													//Many to Many Mapping
		/*
		 
		 //when it comes to video 96. The below code will not work
		List<Review> reviews= new ArrayList<>();
		reviews.add(new Review("5","Greate Hands-on Stuff.") );
		reviews.add(new Review("5","Hatsoff") );
		
		
		crepo.addReviewsForCourse(1003L,reviews);
		
		*/
		
		
		//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------

		
		
	}


}
