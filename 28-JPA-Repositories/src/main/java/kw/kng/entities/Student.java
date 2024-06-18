package kw.kng.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="STUDENTS")
@NoArgsConstructor
@AllArgsConstructor
public class Student 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="full_name", nullable=false)
	private String name;

	//@OneToOne
	@OneToOne(fetch=FetchType.LAZY) //lLazy fethcing will get the details only when it is needed.
	private Passport passport;//In the STUDENTS table PASSPORT_ID is a FOREIGN KEY to PASSPORT Table.
	
	@ManyToMany
	@JoinTable(	name="STUDENT_COURSE",
							joinColumns= @JoinColumn(name="STUDENT_ID"),
							inverseJoinColumns= @JoinColumn(name="COURSE_ID")
						)
	private List<Course> courses=new ArrayList<>();
	
	public void addCourse(Course course)
	{
		this.courses.add(course);
	}
	
	
	public Student(String name)
	{
		this.name=name;
	}
	
	@Override
	public String toString()
	{
		return String.format("Student[%s]", name);
	}
	
	
}

/*
 If the updated details are not saved in DB then the remaining details  have to be rolled back. That is why we are using @Transactional annotation.
 
 
 */
