insert into COURSE_DETAILS(id,full_name, created_date, last_updated_date) values(1001,'JPA in 50 Steps',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
insert into COURSE_DETAILS(id,full_name,created_date, last_updated_date) values(1002,'JPA using Entity manager',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
insert into COURSE_DETAILS(id,full_name,created_date, last_updated_date) values(1003,'Transformers 2',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
insert into COURSE_DETAILS(id,full_name,created_date, last_updated_date) values(1004,'Transformers 1',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());

insert into PASSPORT(id, number)values(40001, 'E123456');
insert into PASSPORT(id, number)values(40002, 'N123457');
insert into PASSPORT(id, number)values(40003, 'L123890');

insert into STUDENTS(id,full_name,passport_id)values(20001, 'Ranga',40001);
insert into STUDENTS(id,full_name,passport_id)values(20002, 'Adam',40002);
insert into STUDENTS(id,full_name,passport_id)values(20003, 'Jane',40003);


insert into REVIEWS(id,rating,description)values(50001, '5', 'Great Course');
insert into REVIEWS(id,rating,description)values(50002, '4', 'Wonderful Course');
insert into REVIEWS(id,rating,description)values(50003, '5', 'Awesome Course');

