package kw.kng;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kw.kng.entities.Course;
import kw.kng.repo.StudentRepo;

@SpringBootTest(classes=Application.class)
public class CriteriaQuery 
{
private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private StudentRepo srepo;
		
	@Autowired
	private EntityManager em;
	
	/*
	
	@Test
	public void criteria_query_basics()
	{
		//e,g:  Select c from Course c where name like '%100Steps' 
		
		//1. Use Criteria Builder to create a Criteria Query returning the expected result Object.
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2. Define roots for tables which are involved in the query.
		// this will represent `from Course c`
		Root<Course> courseRoot=cq.from(Course.class);
		
		//3. Define Predicates etc using Criteria Builder.
		// this represents ` name like '%100Steps' `
		Predicate like100Steps= cb.like(courseRoot.get("name"), "%100 Steps");
		
		//4. Add Predicated etc to the Criteria Query.
		// this represents ` where  name like '%100Steps' `
		cq.where(like100Steps);
		
		//5. Build the TypedQuery using the entity manager and criteria query.
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		
		List<Course> resultList= query.getResultList();
		logger.info("Results -> {}", resultList);
	}

	@Test
	public void criteria_query_find_all_students_without_courses()
	{
		//e,g:  Select c from Course c where c.students is empty
		
		//1. Use Criteria Builder to create a Criteria Query returning the expected result Object.
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2. Define roots for tables which are involved in the query.
		// this will represent `from Course c`
		Root<Course> courseRoot=cq.from(Course.class);
		
		//3. Define Predicates etc using Criteria Builder.
		// this represents ` c.students is empty `
		Predicate studentsIsEmpty= cb.isEmpty(courseRoot.get("students"));
		
		//4. Add Predicated etc to the Criteria Query.
		// this represents ` where c.students is empty `
		cq.where(studentsIsEmpty);
		
		//5. Build the TypedQuery using the entity manager and criteria query.
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		
		List<Course> resultList= query.getResultList();
		logger.info("Results -> {}", resultList);
		
	}
	
	
	@Test
	public void criteria_query_joins()
	{
		//e,g:  Select c from Course c join c.students s
		
		//1. Use Criteria Builder to create a Criteria Query returning the expected result Object.
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2. Define roots for tables which are involved in the query.
		// this will represent `from Course c`
	
		Root<Course> courseRoot=cq.from(Course.class);
		
		//3. Define Predicates etc using Criteria Builder.
		// this represents ` join c.students s `
		Join<Object, Object> join = courseRoot.join("students");
		
		//4. Add Predicated etc to the Criteria Query.

		
		//5. Build the TypedQuery using the entity manager and criteria query.
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		
		List<Course> resultList= query.getResultList();
		logger.info("Results -> {}", resultList);
		
	}
	
	
	@Test
	public void criteria_query_left_joins()
	{
		//e,g:  Select c from Course c left join c.students s
		
		//1. Use Criteria Builder to create a Criteria Query returning the expected result Object.
		CriteriaBuilder cb=em.getCriteriaBuilder();
	//	CriteriaQuery<Course> cq = cb.createQuery(Course.class);
		
		//2. Define roots for tables which are involved in the query.
		// this will represent `from Course c`
		Root<Course> courseRoot=cq.from(Course.class);
		
		//3. Define Predicates etc using Criteria Builder.
		// this represents ` join c.students s `
		Join<Object, Object> join = courseRoot.join("students", JoinType.LEFT);
		
		//4. Add Predicated etc to the Criteria Query.

		
		//5. Build the TypedQuery using the entity manager and criteria query.
		TypedQuery<Course> query = em.createQuery(cq.select(courseRoot));
		
		List<Course> resultList= query.getResultList();
		logger.info("Results -> {}", resultList);
		
	}
	
	*/
}


/*

CRITERIA QUERY
--------------------------------------

 Criteria Queries are a type-safe, programmatic way to create queries in Java Persistence API (JPA).
  They provide an alternative to JPQL (Java Persistence Query Language) and are especially useful when you need dynamic query construction
   or when you prefer a compile-time checked approach.

Here are key concepts and steps involved in creating and using Criteria Queries in JPA:

Key Concepts
1.1. CriteriaBuilder: A factory for creating various types of objects used in criteria queries, such as predicates, expressions, and orders.
1.2. CriteriaQuery: Represents a query object. It defines the structure of the query, including the result type, query roots, selection, predicates, and ordering.
1.3. Root: Represents an entity in the from clause. It defines a range variable in the query and acts as a starting point for navigating through the entity's attributes.
1.4. Predicates: Used to define conditions in the where clause of a query.
1.5. TypedQuery: Represents a query with a specified result type. It is created from an EntityManager using a CriteriaQuery.
 
TRANSACTIONA MANAGEMENT in JPA
---------------------------------------------------------------------
Transaction management in JPA is a critical aspect of ensuring data integrity and consistency in applications.
 JPA supports both local and global transactions. Local transactions are managed by the application,
 while global transactions (or JTA transactions) are managed by the container in a Java EE environment.

Key Concepts in Transaction Management:

2.1 EntityManager: Central to JPA, it manages the persistence context and provides methods to begin, commit, and roll back transactions.
2.2 Persistence Context: A set of entity instances in which for any persistent entity identity, there is a unique entity instance. It manages entity states and ensures synchronization with the database.
2.3 Transaction Boundaries: Define the start and end points of a transaction, typically involving multiple operations that need to be treated as a single unit of work.

Steps in Transaction Management

Start a Transaction: Begin a transaction before performing any operations that modify the database.
Perform Operations: Carry out the required operations like persisting, merging, or removing entities.
Commit or Rollback: Commit the transaction if all operations succeed, or roll back if an error occurs to maintain data integrity.

ACID Properties
ACID properties are a set of principles that ensure reliable processing of database transactions. ACID stands for:

Atomicity
Consistency
Isolation
Durability

1. Atomicity
Definition: 
Ensures that a series of operations within a transaction are all completed successfully, or none of them are.
 It treats a transaction as an indivisible unit of work.

Example: 
In a banking system, transferring money from Account A to Account B involves debiting Account A and crediting Account B.
 If either operation fails, neither should be applied.

2. Consistency
Definition: 
Ensures that a transaction brings the database from one valid state to another, maintaining the integrity constraints of the database.

Example:
 If a database constraint requires that account balances cannot be negative,  a transaction that would result in a negative balance must be rolled back.

3. Isolation
Definition: 
Ensures that transactions are executed in isolation from each other, meaning that intermediate states of a transaction are not visible to other transactions. 
This prevents conflicts when transactions are executed concurrently.

Example:
 If two transactions are updating the same account balance simultaneously, 
 isolation ensures that one transaction’s changes are not visible to the other until it is completed.

4. Durability
Definition:
 Ensures that once a transaction is committed, its changes are permanent, even in the event of a system failure.

Example:
 Once a transfer of funds between accounts is committed, the changes should persist even if the system crashes immediately after.

--------------------------------------------------------------------------------------------------

These terms are associated with transaction isolation levels and describe specific types of anomalies that can occur in a multi-transaction environment. 
They are crucial in understanding the behavior of transactions in a database system.

Dirty Read
----------------
Definition:
 A dirty read occurs when a transaction reads data that has been modified by another transaction but not yet committed.
  If the other transaction rolls back, the data read by the first transaction becomes invalid because it was never committed.

Example:

Transaction 1 starts and updates a row in the database but has not committed yet.
Transaction 2 reads this uncommitted data.
If Transaction 1 rolls back, the data read by Transaction 2 is no longer valid because it was never actually saved to the database.
Implication: Dirty reads can lead to inconsistencies because transactions may work with data that is not final.

Non-repeatable Read
----------------------------------
Definition: 
A non-repeatable read occurs when a transaction reads the same row twice and gets different values
 each time because another transaction has modified and committed the data in the meantime.

Example:
Transaction 1 reads a row and retrieves a value.
Transaction 2 updates and commits changes to that same row.
Transaction 1 reads the same row again and retrieves a different value.
Implication: Non-repeatable reads can lead to inconsistencies within a single transaction as the same data appears to change unexpectedly.

Phantom Read
-------------------------
Definition: 
A phantom read occurs when a transaction reads a set of rows that satisfy a condition, and another transaction inserts, updates, or deletes rows 
that affect the result set of the first transaction’s query. This causes the first transaction to see a different set of rows if it re-executes the query.

Example:
Transaction 1 executes a query to retrieve all rows that meet a certain condition.
Transaction 2 inserts a new row that meets the same condition and commits.
Transaction 1 re-executes the query and now retrieves a different set of rows, including the new row inserted by Transaction 2.
Implication: Phantom reads can lead to inconsistencies in the result sets returned by a transaction when the data set changes between reads.

Isolation Levels and Anomalies
The isolation levels in database systems define the extent to which transactions are isolated from each other, 
which in turn determines the types of anomalies that can occur. Here are the standard isolation levels and the anomalies they prevent:

Read Uncommitted:
---------------------------------
Allows: Dirty reads, non-repeatable reads, phantom reads.
Prevents: None.
Read Committed:

Prevents: Dirty reads.
Allows: Non-repeatable reads, phantom reads.
Repeatable Read:

Prevents: Dirty reads, non-repeatable reads.
Allows: Phantom reads.

Serializable:

Prevents: Dirty reads, non-repeatable reads, phantom reads.
Provides: Complete isolation by ensuring transactions are serializable, i.e., they appear to execute in a sequential order.


 */













