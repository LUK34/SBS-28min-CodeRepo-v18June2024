package kw.kng.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="EMPLOYEE")
@Inheritance(strategy=InheritanceType.JOINED) 							//Parent Class(Employee) and all the Concrete Sub Classes(PartTimeEmployee, FullTimeEmployee) will be provided as seperate tables 
//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS) 		//each concrete sub class(PartTimeEmployee, FullTimeEmployee) will be provided as an individual table.
//@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name="EmployeeType")
@NoArgsConstructor
@AllArgsConstructor
public abstract class Employee 
{

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="full_name", nullable=false)
	private String name;
	
	//---------------------------------------------------------------------------------------------------------------
	

	//---------------------------------------------------------------------------------------------------------------

	
	public Employee(String name)
	{
		this.name=name;
	}
	
	
	
}

/*


An abstract class in Java is a class that cannot be instantiated on its own and is designed to be subclassed. 
It can contain both abstract methods (methods without a body) and concrete methods (methods with a body).
 Abstract classes are used to provide a base class from which other classes can inherit and to enforce a certain structure on the subclasses.

The different types of Inheritance:
1.Single Table
2.Table per Class
3.Joined -> This is the best option to ensure Data Integrity.

To do without inheritance used
Mapped Super Class


 SELECT * FROM COURSE_DETAILS ;
SELECT * FROM PASSPORT ;
SELECT * FROM REVIEWS ;
SELECT * FROM STUDENTS; 
SELECT * FROM STUDENT_COURSE;

SELECT * 
FROM STUDENT_COURSE, STUDENTS, COURSE_DETAILS
WHERE 
STUDENT_COURSE.STUDENT_ID=STUDENTS.ID
AND 
STUDENT_COURSE.COURSE_ID= COURSE_DETAILS.ID;

SELECT * FROM EMPLOYEE 






 */














