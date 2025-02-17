package kw.kng.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kw.kng.entities.Course;
import kw.kng.entities.Employee;

@Repository
@Transactional
public class EmployeeRepo 
{
	private Logger logger= LoggerFactory.getLogger(this.getClass());

	@Autowired
	EntityManager em;
	
	//Insert an Employee
	public void insert(Employee employee)
	{
		em.persist(employee);
	}
	
	//Retrieve all employees
	public List<Employee> retrieveAllEmployees()
	{
		return em.createQuery("select e from Employee e", Employee.class).getResultList();
	}
	

	public Course findById(Long id)
	{
		return em.find(Course.class, id);
	}
	
	
	
}
