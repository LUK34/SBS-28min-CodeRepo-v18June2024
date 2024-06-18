package kw.kng;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kw.kng.entities.Course;
import kw.kng.entities.Passport;
import kw.kng.entities.Student;
import kw.kng.repo.StudentRepo;

@SpringBootTest(classes=Application.class)
public class StudentRepoTest 
{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StudentRepo srepo;
		
	@Autowired
	private EntityManager em;
	

	@Test
	public void retrieveStudentAndPassportDetail()
	{
		Student student = em.find(Student.class, 20001L);
		logger.info("student -> {}", student);
		logger.info("passport -> {}", student.getPassport());
	}
	
	@Test
	@Transactional
	public void retrievePassportAndAssociatedStudent()
	{
		Passport passport=em.find(Passport.class, 40001L);
		logger.info("passport ->{}", passport);
		logger.info("studebr -> {}", passport.getStudent());
	}
	
	@Test
	//@Transactional
	public void someOperationToUnderstadPersistenceContext()
	{
		srepo.someDummyMethod();//select the entire code -> Right Click -> Refactor
		//Now the `@Transactional` support is provided by the StudentRepo.
		
	}

	@Test
	@Transactional
	public void retrieveStudentAndCourse()
	{
		Student student=em.find(Student.class, 20001L);
		logger.info("student ->{} ", student);
		logger.info("courses->{}", student.getCourses());
		
		Passport passport=em.find(Passport.class, 40001L);
	
		logger.info("student -> {}", passport.getStudent());
		
	}
	

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

	
}

/*
 
1. If the updated details are not saved in DB then the remaining details  have to be rolled back. That is why we are using @Transactional annotation.


*/


