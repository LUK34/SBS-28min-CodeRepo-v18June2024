package kw.kng.runner;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import kw.kng.entities.FullTimeEmployee;
import kw.kng.entities.PartTimeEmployee;
import kw.kng.repo.EmployeeRepo;

@Component
public class EmployeeRunner implements CommandLineRunner
{
	private Logger logger= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EmployeeRepo erepo;

	@Override
	public void run(String... args) throws Exception 
	{
			//Jack FullTimeEmployeesalary -> 10000
			//Jill 	PartTimeEmployeesalary ->50 per hour
		
			System.out.println("----------------------------------------------------------------------------------------------------------------------------");
			
			System.out.println("Inheritance using Abstract Method:");
			erepo.insert(new PartTimeEmployee("Jill", new BigDecimal("50")));
			erepo.insert(new FullTimeEmployee("Jack", new BigDecimal("10000")));
		
			logger.info("All Employees -> {}", erepo.retrieveAllEmployees() );
			
			System.out.println("----------------------------------------------------------------------------------------------------------------------------");
			
			
	}
	

}
