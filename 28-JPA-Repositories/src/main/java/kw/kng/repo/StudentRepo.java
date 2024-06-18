package kw.kng.repo;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kw.kng.entities.Course;
import kw.kng.entities.Passport;
import kw.kng.entities.Student;

@Repository
@Transactional
public class StudentRepo 
{
	@Autowired
	EntityManager em;
	
	private Logger logger= LoggerFactory.getLogger(this.getClass());
	
	
	public Student findById(Long id)
	{
		return em.find(Student.class, id);
	}
	
	public boolean deleteById(Long id) 
	{
        Student student = em.find(Student.class, id);
        if (student != null)
        {
            em.remove(student);
            return true;
        }
        return false;
    }
	
	public Student save(Student student)
	{
		if(student.getId() == null)
		{
			em.persist(student);
		}
		else
		{
			em.merge(student);
		}
		
		return student;
	}
	
	public void playWithEntityManager2()
	{
		Student student1= new Student("Web Services in 100 Steps");
		em.persist(student1);
		
		Student student2= findById(1001L);
		student2.setName("JPA in 50 Steps - Updated");
	}
	

	public void saveStudentWithPassport() 
	{
		Passport passport = new Passport("Z123456");
		em.persist(passport);
		
		Student student =  new Student("Mike");
		student.setPassport(passport);
		em.persist(student);
	}
	
	public void someDummyMethod() 
	{
		//Database opertauon 1 - retrieve student
		Student student=em.find(Student.class, 20001L);
		//Persistence context(Student)
		
		
		//Database Operation 2 -  retrieve passport
		Passport passport= student.getPassport();
		//Persistence Context(passport)
		
		//Database Operation 3 - update passport
		passport.setNumber("E123457");
		//Persistence Context(student, passport++)
		
		//Database Operation 4 - update student
		student.setName("Ranga - updated");
		//Persistence Context(student++, passport++)
	}
	
	public void insertHardCodedStudentAndCourse()
	{
		Student student=new Student("Jack");
		Course course=new Course("Microservices in 100 steps");
		em.persist(student);
		em.persist(course);
		
		student.addCourse(course);
		course.addStudent(student);
		em.persist(student); //After adding data from both sides. You need to persists the ` owning side `.
		
	}
	
	public void insertStudentAndCourse(Student student, Course course)
	{
		//Student student=new Student("Jack");
		//Course course=new Course("Microservices in 100 steps");

		
		student.addCourse(course);
		course.addStudent(student);
		em.persist(student);
		em.persist(course);
		
	}
	
}


/*
 
 1.
  An entity in this context is a lightweight, persistent domain object. 
 In simpler terms, an entity represents a table in a database, and each instance of an entity corresponds to a row within that table.

2.
 The entity manager is responsible for managing the lifecycle of entities, which includes operations such as:

2.1
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