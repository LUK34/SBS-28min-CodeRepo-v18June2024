package kw.kng.runner;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import kw.kng.repo.StudentRepo;

@Component
public class StudentRunner  implements CommandLineRunner
{
	
	private Logger logger= LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StudentRepo srepo;

	@Override
	public void run(String... args) throws Exception
	{
		// TODO Auto-generated method stub
			
		
		//-----------------------------------------------------------------------------------------------------------------------------------------
										//One to Many Relationship
		
		srepo.saveStudentWithPassport();
	    
	}


}
