package kw.kng;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import kw.kng.entities.Course;
import kw.kng.repo.CourseSpringDataRepo;

@SpringBootTest(classes=Application.class)
public class CourseSpringDataRepoTest 
{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CourseSpringDataRepo crepo;
	
		
	@Test
	public void findById_CoursePresent()
	{
		Optional<Course> courseOptional= crepo.findById(10001L);
		logger.info("{}",courseOptional.isPresent() );
		assertTrue(courseOptional.isPresent());
	}
	
	@Test
	public void findById_CourseNotPresent()
	{
		Optional<Course> courseOptional= crepo.findById(20001L);
		logger.info("{}",courseOptional.isPresent() );
		assertFalse(courseOptional.isPresent());
	}
	

	@Test
	public void playingAroundWithSpringDataRepository()
	{
		Course courseOptiona = new Course("Microservice in 100 Steps");
		crepo.save(courseOptiona);
		
		courseOptiona.setName("Microservices in 100 Steps - Updated");
		crepo.save(courseOptiona);
		
		logger.info("Course -> {}", crepo.findAll());
		logger.info("Count -> {}", crepo.count());
	}
	
	@Test
	public void sortExample()
	{
		Sort s = Sort.by(Sort.Direction.DESC, "name");
		
		logger.info("Sorted Course -> {}", crepo.findAll(s));
		logger.info("Count -> {}", crepo.count());
	}
	
	
	@Test
	public void paginationExample()
	{
		PageRequest pr= PageRequest.of(0, 3);//first page , number of records per page is 3
		//In each page the number of record that must be  present is 3.
		
		Page<Course> firstPage=crepo.findAll(pr);
		logger.info("Count -> {}", firstPage.getContent());
		
		Pageable secondPageable=firstPage.nextPageable();
		Page<Course> secondPage = crepo.findAll(secondPageable);
	
	}
	
	@Test
	public void findUsing()
	{
		logger.info("FindByName -> {}", crepo.findByName("JPA in 50 Steps"));
	}
	
	
}
