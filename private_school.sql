create database private_school;
use private_school;

create table student(
student_id int not null auto_increment,
first_name varchar(30),
last_name varchar(30),
date_of_birth date,
tuition_fees int,
primary key (student_id)
);

create table trainer(
trainer_id int not null auto_increment,
first_name varchar(30),
last_name varchar(30),
subject varchar(30),
primary key (trainer_id)
);

create table assignment(
assignment_id int not null auto_increment,
title varchar(30),
description varchar(30),
sub_date_time date,
oral_mark decimal(5,2),
total_mark decimal(5,2),
primary key (assignment_id)
);

create table course(
course_id int not null auto_increment,
title varchar(30),
stream varchar(30),
type varchar(30),
start_date date,
end_date date,
primary key (course_id)
);

create table students_per_course(
course_id int not null,
student_id int not null,
primary key(course_id, student_id),
foreign key(course_id)
references course (course_id),
foreign key(student_id)
references student (student_id)
);

create table trainers_per_course(
course_id int not null,
trainer_id int not null,
primary key(course_id, trainer_id),
foreign key(course_id)
references course (course_id),
foreign key(trainer_id)
references trainer (trainer_id)
);

create table assignments_per_course(
course_id int not null,
assignment_id int not null,
primary key(course_id, assignment_id),
foreign key(course_id)
references course (course_id),
foreign key(assignment_id)
references assignment (assignment_id)
);

create table assignments_per_course_per_student(
course_id int not null,
assignment_id int not null,
student_id int not null,
primary key(course_id, assignment_id, student_id),
foreign key(course_id)
references course (course_id),
foreign key(assignment_id)
references assignment (assignment_id),
foreign key(student_id)
references student (student_id)
);

insert into student values ( 1, 'Maria', 'Iakovidou', '1994-07-09', 2250),
(2,'Ioannis', 'Ioannidis', '1988-10-03', 2100),
(3, 'Ioanna', 'Ioannou','1990-05-04', 2000),
(4, 'Georgia', 'Georgiadou', '1990-11-04', 2500),
(5, 'Thodoris', 'Theodorou', '1990-01-06', 2000),
(6, 'Eleni', 'Pappa', '1992-08-08', 2500),
(7, 'Fotis', 'Iliadis', '1988-08-07', 2000),
(8, 'Irini', 'Eleutheriadou', '1981-01-03', 2250),
(9, 'Haris', 'Georgiou', '1983-11-17', 2100),
(10, 'Elpida', 'Euaggelou', '1971-12-03', 2500);

insert into trainer values (1, 'Giorgos', 'Papadopoulos', 'Java'),
(2, 'Georgia', 'Papadopoulou', 'C'),
(3,'Giorgos', 'Georgiou', 'Python'),
(4, 'Maria', 'Anastasiadou', 'Ruby'),
(5, 'Vaggelis', 'Prokopiou', 'JavaScript');

insert into assignment values (1, 'Java PrivateSchool', 'first_project', '2021-04-30', 85.50, 90.03),
(2, 'C PrivateSchool', 'first_project', '2021-07-04', 85.00, 90.00),
(3, 'Python PrivateSchool', 'first_project', '2021-04-14', 89.00, 91.00),
(4, 'Ruby PrivateSchool', 'first_project', '2021-04-16', 87.00, 92.00),
(5, 'JavaScript PrivateSchool', 'first_project', '2021-04-17', 85.00, 93.00);

insert into course values (1, 'Coding Bootcamp', 'Java', 'full-time', '2021-04-30', '2021-09-30'),
(2, 'Coding Bootcamp', 'C', 'full-time', '2021-05-01', '2021-10-01'),
(3, 'Coding Bootcamp', 'Python', 'full-time', '2021-05-02', '2021-10-02'),
(4, 'Coding Bootcamp', 'Ruby', 'part-time', '2021-06-01', '2021-09-30'),
(5, 'Coding Bootcamp', 'JavaScript', 'part-time', '2021-07-01', '2021-10-30');

insert into trainers_per_course (course_id, trainer_id)
values (1,1), (2,2), (3,3), (4,4), (5,5);

insert into students_per_course (course_id, student_id)
values (1,1), (1,2), (2,3), (2,4), (3,5), (3,6), (4,7), (4,8), (5,9), (5,10), (1,4), (2,5), (4,10);

insert into assignments_per_course (course_id, assignment_id)
values (1,1), (2,2), (3,3), (4,4), (5,5);

insert into assignments_per_course_per_student (course_id, assignment_id, student_id)
values (1,1,1), (1,1,2), (2,2,3), (2,2,4), (3,3,5), (3,3,6), (4,4,7), (4,4,8), (5,5,9), (5,5,10), (1,1,4), (2,2,5), (4,4,10);

select * from student;
select * from trainer;
select * from course;
select * from assignment;

-- Get all the Trainers per Course
CREATE VIEW  trainer_per_course AS
select c.stream, t.first_name, t.last_name
from course c, trainer t, trainers_per_course tc
where c.course_id = tc.course_id
and t.trainer_id = tc.trainer_id;

-- Get all the Students per Course
CREATE VIEW student_per_course AS
select c.stream, s.first_name, s.last_name
from course c, student s, students_per_course sc
where c.course_id = sc.course_id
and s.student_id = sc.student_id;


-- Get all the Assignments per Course
CREATE VIEW assignment_per_course AS
select c.stream, a.title
from course c, assignment a, assignments_per_course ac
where c.course_id = ac.course_id
and a.assignment_id = ac.assignment_id;
 
 -- Get all the Assignments per Course per student
 CREATE VIEW assignment_per_course_per_student AS
 select s.first_name, s.last_name, c.stream, a.title
 from  student s, course c, assignment a, assignments_per_course_per_student sca
 where s.student_id = sca.student_id
 and c.course_id = sca.course_id
 and a.assignment_id = sca.assignment_id;

-- Get the Students than belong to more than one Courses
CREATE VIEW students_belong_course  AS
select  s.first_name, s.last_name
from student s, course c, students_per_course sc
where s.student_id = sc.student_id
and c.course_id = sc.course_id
group by sc.student_id having count(sc.student_id) > 1;
 
 
 select * from trainer_per_course; 
 select * from student_per_course;
 select * from assignment_per_course;
 select * from assignment_per_course_per_student;
 select * from students_belong_course;
 


 