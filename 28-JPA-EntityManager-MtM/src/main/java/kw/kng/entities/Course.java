package kw.kng.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="COURSE_DETAILS")
@NoArgsConstructor
@AllArgsConstructor
public class Course 
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="full_name", nullable=false)
	private String name;
	
	//---------------------------------------------------------------------------------------------------------------
	@OneToMany(mappedBy="course")
	private List<Review> reviews = new ArrayList<>();
	
	@ManyToMany(mappedBy="courses")
	private List<Student> students= new ArrayList<>();
	
	public void addStudent(Student student)
	{
		this.students.add(student);
	}
	
	
	public void addReview(Review review)
	{
		this.reviews.add(review);
	}
	
	public void removeReview(Review review)
	{
		this.reviews.remove(review);
	}
	//---------------------------------------------------------------------------------------------------------------
	
	
	
	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;
	
	@CreationTimestamp
	private LocalDateTime createdDate;

	
	public Course(String name)
	{
		this.name=name;
	}
	
	
	
}

/*

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



 */














