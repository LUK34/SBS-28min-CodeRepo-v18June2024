package kw.kng.repo;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kw.kng.entities.Course;
import kw.kng.entities.Review;

@Repository
@Transactional
public class CourseRepo 
{
	@Autowired
	EntityManager em;
	
	private Logger logger= LoggerFactory.getLogger(this.getClass());
	
	
	public Course findById(Long id)
	{
		return em.find(Course.class, id);
	}
	
	public boolean deleteById(Long id) 
	{
        Course course = em.find(Course.class, id);
        if (course != null)
        {
            em.remove(course);
            return true;
        }
        return false;
    }
	
	public Course save(Course course)
	{
		if(course.getId() == null)
		{
			em.persist(course);
		}
		else
		{
			em.merge(course);
		}
		
		return course;
	}
	
	public void playWithEntityManager2()
	{
		Course course1= new Course("Web Services in 100 Steps");
		em.persist(course1);
		
		Course course2= findById(1001L);
		course2.setName("JPA in 50 Steps - Updated");
	}
	

	public void playWithEntityManager() 
	{
		logger.info("playWithEntityManager - start");
		Course course1= new Course("Web Services in 100 Steps");
		em.persist(course1); //persist is used to create a new Entity.
		em.flush();
		/*
		 What does the flush does??
		  whatever changes that have been done. Those hanges will be sent to DB/
		*/
		course1.setName("Web Services in 100 Steps -  Updated");
		em.flush(); 
		
		Course course2= new Course("Angular JS in 100 Steps");
		em.persist(course2);
		em.flush();
		
		em.clear();
		/*
		method in Java Persistence API (JPA) is used to clear the persistence context of an EntityManager.
		This operation effectively removes all managed entities from the persistence context, turning them into detached entities.
		 As a result, the EntityManager no longer tracks any changes to these entities.
		 */
		em.detach(course1);
		em.detach(course2); 
		/*
		   detach method is used to detach the entity from the persistance layer(DB)
		*/
		course2.setName("Angular JS in 100 Steps- Updated");
		
		em.refresh(course1);
		/*
		  method in Java Persistence API (JPA) is used to synchronize the state of a given entity with the underlying database.
		  Essentially, this method updates the entity to reflect any changes that have been made to it in the database 
		  since it was last loaded or since the last flush operation. This can be particularly useful in a variety of situations, 
		  such as when changes might have been made by other transactions or processes that affect the data integrity of the current context.
		 */
		em.flush();
		/*
		 with the help of @Transactional annotation the entity manager
		 keeps track of all the things that were inserted through it, that were updated,
		 entuty manager would start keeping track of them.
		 */
	}
	
	
	public void addReviewsForCourse(Long courseId, List<Review> reviews)
	{
		//Get the course 10003
		Course course= findById(courseId);
		logger.info("course.getReviews() -> {}",course.getReviews());
		
		for(Review review: reviews)
		{
			course.addReview(review);
			review.setCourse(course);
			em.persist(course);
		}
			
		
	}
	
	
	
	
	
}


/*
 
 1.
  An entity in this context is a lightweight, persistent domain object. 
 In simpler terms, an entity represents a table in a database, and each instance of an entity corresponds to a row within that table.

2.
 The entity manager is responsible for managing the lifecycle of entities, which includes operations such as:

3.
Persistence: Saving objects to the database.
Retrieval: Fetching objects from the database.
Update: Making changes to objects already stored in the database.
Deletion: Removing objects from the database.
Entity managers handle these tasks by abstracting the complexities of direct database interactions from developers,
 allowing them to interact with objects in the programming language they are using rather than dealing with SQL queries directly. 
 This makes the code cleaner, more maintainable, and easier to understand.

4. 
One of the most common examples of an ORM framework that uses an entity manager is Java Persistence API (JPA), which defines a set of concepts and techniques for managing relational data in Java applications.
 
 
 */