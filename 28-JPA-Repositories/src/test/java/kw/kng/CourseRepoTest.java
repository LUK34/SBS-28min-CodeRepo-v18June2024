package kw.kng;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import kw.kng.entities.Course;
import kw.kng.entities.Review;
import kw.kng.repo.CourseRepo;

@SpringBootTest(classes=Application.class)
public class CourseRepoTest 
{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseRepo crepo;
	
		
	@Autowired
	private EntityManager em;
	
	@Test
	public void findById_basic() 
	{		
		Course course = crepo.findById(10001L);
		assertEquals("JPA in 50 Steps", course.getName());
	}
	
	@Test
	public void jpql_basic()
	{
		Query q = em.createQuery("Select c from Course c");
		List resultList = q.getResultList();
		logger.info("Select c from Course c -> {}", resultList);
		
	}
	
	@Test
	public void jpql_typed()
	{
		TypedQuery<Course> query = em.createQuery("Select c from Course c where name like '%100 Steps' ", Course.class);
		List resultList= query.getResultList();
		logger.info("Slect c from Course c -> {}", resultList);
	}
	
	@Test
	public void native_queries_basic()
	{
		Query q1=em.createNativeQuery("SELECT * FROM COURSE_DETAILS", Course.class);
		List resultList = q1.getResultList();
		logger.info("Native Query: SELECT * FROM COURSE_DETAILS -> {} ", resultList);
	}
	
	@Test
	public void native_queries_with_parameter()
	{
		Query q1=em.createNativeQuery("SELECT * FROM COURSE_DETAILS where id=?",  Course.class);
		q1.setParameter(1, 1001L);
		List resultList = q1.getResultList();
		logger.info("Native Query with parameter: SELECT * FROM COURSE_DETAILS where id=? -> {} ", resultList);
	}
	
	@Test
	public void native_queries_with_named_parameter()
	{
		Query q1=em.createNativeQuery("SELECT * FROM COURSE_DETAILS where id= :id",  Course.class);
		q1.setParameter("id", 1001L);
		List resultList = q1.getResultList();
		logger.info("Native Query with named parameter: SELECT * FROM COURSE_DETAILS where id=:id -> {} ", resultList);
	}
	
	@Test
	public void native_queries_to_update()
	{
		Query q2=em.createNativeQuery("Update COURSE_DETAILS set last_updated_date=CURRENT_TIMESTAMP",  Course.class);
		int noOfRowsUpdated= q2.executeUpdate();
		logger.info("noOfRowsUpdated -> {}", noOfRowsUpdated);
	}
	
	@Test
	@DirtiesContext
	public void deleteById_basic()
	{
		long courseid=1003L;
		crepo.deleteById(courseid);
		assertNull(crepo.findById(courseid));
	}
	
	@Test
	@DirtiesContext
	public void save_basic()
	{
		
		//get a courser
		Course course= crepo.findById(1001L);
		assertEquals("JPA in 50 Steps", course.getName());
		
		//Update details
		course.setName("JPA in 50 Steps - Updated");
		crepo.save(course);
		
		//Check the value
		Course course1= crepo.findById(1001L);
		assertEquals("JPA in 50 Steps - Updated", course1.getName());
		
		assertNull(crepo.findById(1002L));
	}
	
	@Test
	@Transactional
	public void retrieveReviewForCourse()
	{
		Course course = crepo.findById(10001L);
		logger.info("{}",course.getReviews());
	
		/*
		 Here we are doing LAZY Fetching. First we retrieve course and then we fethc reviews.
		 By defualt the ONE to MANY relationship. The fetching is LAZY fetching.
		 */
	}
	
	
	@Test
	@Transactional
	public void retrieveCourseForReview()
	{
		Review review= em.find(Review.class, 50001L);
		logger.info("{}", review.getCourse());
		
		/*
		 NOTE:
		 One to Many side of the relationship is always by default LAZY Fetching. Many to One side of the relationship 
		 is always by default EAGER Fetching.
		 */
	}
	
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
		//CACHING
		
	@Test
	@Transactional
	public void findById_firstLevelCacheDemo()
	{
		Course course= crepo.findById(10001L);
		logger.info("First Course Retrieved {}", course);
		
		Course course1= crepo.findById(10001L);
		logger.info("First Course Retrieved {}", course1);
		
		assertEquals("JPA in 50 Steps", course.getName());
		assertEquals("JPA in 50 Steps", course1.getName());
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
}
