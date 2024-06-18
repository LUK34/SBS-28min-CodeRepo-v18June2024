package kw.kng;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kw.kng.entities.Course;
import kw.kng.entities.Student;
import kw.kng.repo.StudentRepo;

@SpringBootTest(classes=Application.class)
public class JpqlTest 
{
private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StudentRepo srepo;
		
	@Autowired
	private EntityManager em;
	
	
	
	@Test
	public void jpql_courses_without_students()
	{
		TypedQuery<Course> query= em.createQuery("Select c from Course c where c.students is empty", Course.class);
		
		List<Course> resultList= query.getResultList();
		logger.info("Results -> {}", resultList);
	}
	
	@Test
	public void jpql_courses_with_atleast_2_students()
	{
		TypedQuery<Course> query= em.createQuery("Select c from Course c where size(c.students) >= 2", Course.class);
		
		List<Course> resultList= query.getResultList();
		logger.info("Results -> {}", resultList);
	}
	
	
	@Test
	public void jpql_courses_ordered_students()
	{
		TypedQuery<Course> query= em.createQuery("Select c from Course c order by size(c.students)", Course.class);
		
		List<Course> resultList= query.getResultList();
		logger.info("Results -> {}", resultList);
	}
	
	@Test
	public void jpql_students_with_passports_in_a_certain_pattern()
	{
		TypedQuery<Student> query= em.createQuery("Select s from Student s where s.passport.number like '%1234%' ", Student.class);
		
		List<Student> resultList= query.getResultList();
		logger.info("Results -> {}", resultList);
	}

	//JOIN ->Select c,s, from Course c JOIN c.students s
	//LEFT JOIN => Select c,s, from Course c LEFT JOIN c.students s
	
	//CROSS JOIN => Select c,s, from Course c,Student s
	//3 and 4 => 3*4= 12 Rows
	
	@Test
	public void join()
	{
		//JOIN ->Select c,s from Course c JOIN c.students s
		/*
		Query q= em.createQuery("Select c, s from Course c JOIN c.students s");
		List<Student> resultList= q.getResultList();
		logger.info("Result -> {}", resultList);
		*/
		Query q= em.createQuery("Select c, s from Course c JOIN c.students s");
		List<Object[]> resultList= q.getResultList();
		logger.info("Result -> {}", resultList);
		for(Object[] result:resultList)
		{
			logger.info("Course{} Student{}",result[0], result[1]);
		}
	}
	
	
	@Test
	public void leftJoin()
	{
		// LEFT JOIN => Select c,s from Course c LEFT JOIN c.students s
		Query q= em.createQuery("Select c,s from Course c LEFT JOIN c.students s");
		List<Object[]> resultList= q.getResultList();
		logger.info("Result -> {}", resultList);
		for(Object[] result:resultList)
		{
			logger.info("Course{} Student{}",result[0], result[1]);
		}
	}
	
	
	@Test
	public void crossJoin()
	{
		// CROSS ORIGIN =>
		Query q= em.createQuery("Select c,s from Course c, Student s");
		List<Object[]> resultList= q.getResultList();
		logger.info("Result -> {}", resultList);
		for(Object[] result:resultList)
		{
			logger.info("Course{} Student{}",result[0], result[1]);
		}
	}
	
	
	
	
	
}
