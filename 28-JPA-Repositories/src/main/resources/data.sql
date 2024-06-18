insert into COURSE_DETAILS(id,full_name, created_date, last_updated_date) values(10001,'JPA in 50 Steps',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
insert into COURSE_DETAILS(id,full_name,created_date, last_updated_date) values(10002,'JPA using Entity manager',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
insert into COURSE_DETAILS(id,full_name,created_date, last_updated_date) values(10003,'SpringBoot and Thymeleaf',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
insert into COURSE_DETAILS(id,full_name,created_date, last_updated_date) values(10004,'SpringBoot and React',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
insert into COURSE_DETAILS(id,full_name,created_date, last_updated_date) values(10005,'Dummy 1',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
insert into COURSE_DETAILS(id,full_name,created_date, last_updated_date) values(10006,'Dummy 2',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
insert into COURSE_DETAILS(id,full_name,created_date, last_updated_date) values(10007,'Dummy 3',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
insert into COURSE_DETAILS(id,full_name,created_date, last_updated_date) values(10008,'Dummy 4',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
insert into COURSE_DETAILS(id,full_name,created_date, last_updated_date) values(10009,'Dummy 5',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
insert into COURSE_DETAILS(id,full_name,created_date, last_updated_date) values(10010,'Dummy 6',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());





insert into PASSPORT(id, number)values(40001, 'E123456');
insert into PASSPORT(id, number)values(40002, 'N123457');
insert into PASSPORT(id, number)values(40003, 'L123890');
insert into PASSPORT(id, number)values(40004, 'M312980');

insert into STUDENTS(id,full_name,passport_id)values(20001, 'Ranga',40001);
insert into STUDENTS(id,full_name,passport_id)values(20002, 'Adam',40002);
insert into STUDENTS(id,full_name,passport_id)values(20003, 'Jane',40003);

insert into REVIEWS(id,rating,description,course_id)values(50001, '5', 'Great Course',10001);
insert into REVIEWS(id,rating,description,course_id)values(50002, '4', 'Wonderful Course',10002);
insert into REVIEWS(id,rating,description,course_id)values(50003, '5', 'Awesome Course',10003);
insert into REVIEWS(id,rating,description,course_id)values(50004, '3', 'Nice Course',10004);


insert into STUDENT_COURSE(student_id,course_id)
values(20001,10001);
insert into STUDENT_COURSE(student_id,course_id)
values(20002,10001);
insert into STUDENT_COURSE(student_id,course_id)
values(20003,10001);
insert into STUDENT_COURSE(student_id,course_id)
values(20001,10003);
insert into STUDENT_COURSE(student_id,course_id)
values(20003,10004);
insert into STUDENT_COURSE(student_id,course_id)
values(20003,10005);
insert into STUDENT_COURSE(student_id,course_id)
values(20003,10006);
insert into STUDENT_COURSE(student_id,course_id)
values(20003,10007);


















