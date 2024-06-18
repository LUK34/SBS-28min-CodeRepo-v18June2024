package kw.kng;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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


	

	
}

/*
 
1. If the updated details are not saved in DB then the remaining details  have to be rolled back. That is why we are using @Transactional annotation.


*/


