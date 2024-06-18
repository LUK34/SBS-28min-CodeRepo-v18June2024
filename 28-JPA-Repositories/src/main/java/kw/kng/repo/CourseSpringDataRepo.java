package kw.kng.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kw.kng.entities.Course;

public interface CourseSpringDataRepo extends JpaRepository<Course, Long>
{
	
	List<Course> findByName(String name);
	List<Course> findByNameAndId(String name, Long id);
	List<Course> findByNameOrderByIdDesc(String name);
	List<Course> countByName(String name);
	List<Course> deleteByName(String name);
	
	@Query("Select c From Course c where name like '%100 Steps' ")
	List<Course> courseWith100Steps();

	@Query(value="Select * From Course c where name like '%100 Steps' ",
				   nativeQuery=true)
	List<Course> courseWith100StepsInNameUsingNativeQuery();

	
}
