package menu;

import DAO.AssignPerCoursePerStudentDAO;
import DAO.AssignmentDAO;
import DAO.AssignmentPerCourseDAO;
import DAO.CourseDAO;
import DAO.GenericDAO;
import DAO.StudentDAO;
import DAO.StudentMoreCoursesDAO;
import DAO.StudentPerCourseDAO;
import DAO.TrainerDAO;
import DAO.TrainerPerCourseDAO;
import java.sql.SQLException;
import model.Trainer;
import model.Student;
import model.Course;
import model.Assignment;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.AssignPerCoursePerStudent;
import model.AssignmentPerCourse;
import model.StudentMoreCourses;
import model.StudentPerCourse;
import model.TrainerPerCourse;

public class Menu extends GenericDAO {

    public static void printMenu() {

        System.out.println("Select one of the following options: ");
        System.out.println("Press 1 to create a student / a student per course");
        System.out.println("Press 2 to create a course");
        System.out.println("Press 3 to create a trainer / a trainer per course");
        System.out.println("Press 4 to create an assignment / an assignment per course");

        System.out.println("Press 5 to print a student list");
        System.out.println("Press 6 to print a course list");
        System.out.println("Press 7 to print a trainer list");
        System.out.println("Press 8 to print an assignment list");

        System.out.println("Press 9 to print all the students per course");
        System.out.println("Press 10 to print all the trainers per course");
        System.out.println("Press 11 to print all the assignments per course");
        System.out.println("Press 12 to print all the assignments per course per student");
        System.out.println("Press 13 to print a list of students that belong to more than one courses");
        System.out.println("Press 14 to exit");

    }

    public static void menuOption() throws ParseException, SQLException {

        Scanner scanner = new Scanner(System.in);

        int optionNumber;
        do {
            optionNumber = scanner.nextInt();

            switch (optionNumber) {
                case 1:
                    StudentDAO studentDAO = new StudentDAO();
                    StudentPerCourseDAO studentPerCourseDAO = new StudentPerCourseDAO();
                    Student student = new Student();
                    Course course = new Course();
                    System.out.println("Please, insert the First Name of the Student.");
                    student.setFirst_name(scanner.next());
                    System.out.println("Please, insert the Last Name of the Student.");
                    student.setLast_name(scanner.next());
                    System.out.println("Please, insert the Date Of Birth of the Student. (Example: 2003-04-23)");
                    String BirthDate = scanner.next();
                    Date Bdate = new SimpleDateFormat("yyyy-MM-dd").parse(BirthDate);
                    student.setDate_of_birth(Bdate);
                    System.out.println("Please, insert the Tuition Fees of the Student.");
                    student.setTuition_fees(scanner.nextInt());
                    
                    System.out.println("Do you want to assign this student to a course? Yes or No");
                    String assignCourse = scanner.next();
                    
                    if(assignCourse.equalsIgnoreCase("yes")){
                        
                    System.out.println("Please, insert the course of the student.");
                    System.out.println("The available courses are: Java, C, Python, Ruby, JavaScript.");
                    String choice = scanner.next();

                    if ("java".equalsIgnoreCase(choice)) {
                        int courses[] = {1};
                        studentPerCourseDAO.insertStudentPerCourse(student.getFirst_name(), student.getLast_name(), student.getDate_of_birth(), student.getTuition_fees(), courses);
                    } else if ("c".equalsIgnoreCase(choice)) {
                        int courses[] = {2};
                        studentPerCourseDAO.insertStudentPerCourse(student.getFirst_name(), student.getLast_name(), student.getDate_of_birth(), student.getTuition_fees(), courses);
                    } else if ("python".equalsIgnoreCase(choice)) {
                        int courses[] = {3};
                        studentPerCourseDAO.insertStudentPerCourse(student.getFirst_name(), student.getLast_name(), student.getDate_of_birth(), student.getTuition_fees(), courses);
                    } else if ("ruby".equalsIgnoreCase(choice)) {
                        int courses[] = {4};
                        studentPerCourseDAO.insertStudentPerCourse(student.getFirst_name(), student.getLast_name(), student.getDate_of_birth(), student.getTuition_fees(), courses);
                    } else if ("javascript".equalsIgnoreCase(choice)) {
                        int courses[] = {5};
                        studentPerCourseDAO.insertStudentPerCourse(student.getFirst_name(), student.getLast_name(), student.getDate_of_birth(), student.getTuition_fees(), courses);
                    } else {
                        System.out.println("There is no course with this name available. Please, try again.");
                    }
                    
                    }else if(assignCourse.equalsIgnoreCase("no")){
                        studentDAO.insertStudent(student);
                    } else {
                        System.out.println("Invalid option. The student is not created. Please, try again.");
                    }

                    break;
                case 2:
                    CourseDAO courseDAO = new CourseDAO();
                    Course courseNew = new Course();
                    System.out.println("Please, insert the title of the Course.");
                    courseNew.setTitle(scanner.next());
                    System.out.println("Please, insert the stream of the Course.");
                    courseNew.setStream(scanner.next());
                    System.out.println("Please, insert the type of the Course.");
                    courseNew.setType(scanner.next());
                    System.out.println("Please, insert the start date of the Course. (Example: 2021-04-23)");
                    String StartDate = scanner.next();
                    Date SDate = new SimpleDateFormat("yyyy-MM-dd").parse(StartDate);
                    courseNew.setStart_date(SDate);
                    System.out.println("Please, insert the end date of the Course. (Example: 2021-05-23)");
                    String EndDate = scanner.next();
                    Date EDate = new SimpleDateFormat("yyyy-MM-dd").parse(EndDate);
                    courseNew.setEnd_date(EDate);
                    courseDAO.insertCourse(courseNew);

                    break;
                case 3:
                    TrainerDAO trainerDAO = new TrainerDAO();
                    TrainerPerCourseDAO trainerPerCourseDAO = new TrainerPerCourseDAO();
                    Trainer trainer = new Trainer();
                    System.out.println("Please, insert the First Name of the Trainer.");
                    trainer.setFirst_name(scanner.next());
                    System.out.println("Please, insert the Last Name of the Trainer.");
                    trainer.setLast_name(scanner.next());
                    System.out.println("Please, insert the Subject of the Trainer.");
                    System.out.println("The available subjects are: Java, C, Python, Ruby, JavaScript.");
                    trainer.setSubject(scanner.next());

                    if (trainer.getSubject().equalsIgnoreCase("java")) {
                        int courses[] = {1};
                        trainerPerCourseDAO.insertTrainerPerCourse(trainer.getFirst_name(), trainer.getLast_name(), trainer.getSubject(), courses);
                    } else if (trainer.getSubject().equalsIgnoreCase("c")) {
                        int courses[] = {2};
                        trainerPerCourseDAO.insertTrainerPerCourse(trainer.getFirst_name(), trainer.getLast_name(), trainer.getSubject(), courses);
                    } else if (trainer.getSubject().equalsIgnoreCase("python")) {
                        int courses[] = {3};
                        trainerPerCourseDAO.insertTrainerPerCourse(trainer.getFirst_name(), trainer.getLast_name(), trainer.getSubject(), courses);
                    } else if (trainer.getSubject().equalsIgnoreCase("ruby")) {
                        int courses[] = {4};
                        trainerPerCourseDAO.insertTrainerPerCourse(trainer.getFirst_name(), trainer.getLast_name(), trainer.getSubject(), courses);
                    } else if (trainer.getSubject().equalsIgnoreCase("javascript")) {
                        int courses[] = {5};
                        trainerPerCourseDAO.insertTrainerPerCourse(trainer.getFirst_name(), trainer.getLast_name(), trainer.getSubject(), courses);
                    } else {
                        System.out.println("There is no subject with this name available. Please, try again.");
                    }

                    break;
                case 4:
                    AssignmentDAO assignmentDAO = new AssignmentDAO();
                    AssignmentPerCourseDAO assignCoDAO = new AssignmentPerCourseDAO();
                    Assignment assignment = new Assignment();
                    System.out.println("Please, insert the title of the Assignment");
                    assignment.setTitle(scanner.next());
                    System.out.println("Please, insert the description of the Assignment.");
                    assignment.setDescription(scanner.next());
                    System.out.println("Please, insert the submission date of the Assignment. (Example: 2021-05-23)");
                    String SubDateTime = scanner.next();
                    Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(SubDateTime);
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    assignment.setSub_date_time(sqlDate);
                    System.out.println("Please, insert the oral mark of the Assignment.");
                    assignment.setOral_mark(scanner.nextDouble());
                    System.out.println("Please, insert the total mark of the Assignment.");
                    assignment.setTotal_mark(scanner.nextDouble());
                    
                    
                    System.out.println("Do you want to add this assignment to a course? Yes or No");
                    String addAssign = scanner.next();
                    
                    if(addAssign.equalsIgnoreCase("yes")){
                        
                    System.out.println("Please, insert the course of the assignment.");
                    System.out.println("The available courses are: Java, C, Python, Ruby, JavaScript.");
                    String choice = scanner.next();

                    if ("java".equalsIgnoreCase(choice)) {
                        int courses[] = {1};
                        assignCoDAO.insertAssignPerCourse(assignment.getTitle(), assignment.getDescription(), assignment.getSub_date_time(), assignment.getOral_mark(),assignment.getTotal_mark(), courses);
                    } else if ("c".equalsIgnoreCase(choice)) {
                        int courses[] = {2};
                         assignCoDAO.insertAssignPerCourse(assignment.getTitle(), assignment.getDescription(), assignment.getSub_date_time(), assignment.getOral_mark(),assignment.getTotal_mark(), courses);
                    } else if ("python".equalsIgnoreCase(choice)) {
                        int courses[] = {3};
                         assignCoDAO.insertAssignPerCourse(assignment.getTitle(), assignment.getDescription(), assignment.getSub_date_time(), assignment.getOral_mark(),assignment.getTotal_mark(), courses);
                    } else if ("ruby".equalsIgnoreCase(choice)) {
                        int courses[] = {4};
                         assignCoDAO.insertAssignPerCourse(assignment.getTitle(), assignment.getDescription(), assignment.getSub_date_time(), assignment.getOral_mark(),assignment.getTotal_mark(), courses);
                    } else if ("javascript".equalsIgnoreCase(choice)) {
                        int courses[] = {5};
                         assignCoDAO.insertAssignPerCourse(assignment.getTitle(), assignment.getDescription(), assignment.getSub_date_time(), assignment.getOral_mark(),assignment.getTotal_mark(), courses);
                    } else {
                        System.out.println("There is no course with this name available. Please, try again.");
                    }
                    
                    }else if(addAssign.equalsIgnoreCase("no")){
                       assignmentDAO.insertAssignment(assignment);
                    } else {
                        System.out.println("Invalid option. The assignment is not created. Please, try again.");
                    }

                    break;
                case 5:
                    System.out.println("All the students are:");
                    System.out.println("--------------------------------------------------------------------------------------------------------");
                    StudentDAO stuDAO = new StudentDAO();
                    List<Student> studentList = stuDAO.getAllStudents();

                    for (Student tempStudent : studentList) {
                        System.out.println(String.format("%-10s%-20s%-20s%-20s%-20s", tempStudent.getStudent_id(), tempStudent.getFirst_name(),
                                tempStudent.getLast_name(), tempStudent.getDate_of_birth(), tempStudent.getTuition_fees()));
                    }

                    break;
                case 6:
                    System.out.println("All the courses are: ");
                    System.out.println("--------------------------------------------------------------------------------------------------------");
                    CourseDAO coDAO = new CourseDAO();
                    List<Course> courseList = coDAO.getAllCourses();

                    for (Course tempCourse : courseList) {
                        System.out.println(String.format("%-10s%-20s%-20s%-20s%-20s%-10s", tempCourse.getCourse_id(), tempCourse.getTitle(),
                                tempCourse.getStream(), tempCourse.getType(), tempCourse.getStart_date(), tempCourse.getEnd_date()));
                    }

                    break;
                case 7:
                    System.out.println("All the trainers are: ");
                    System.out.println("-------------------------------------------------------------");
                    TrainerDAO trDAO = new TrainerDAO();
                    List<Trainer> trainerList = trDAO.getAllTrainers();

                    for (Trainer tempTrainer : trainerList) {
                        System.out.println(String.format("%-10s%-20s%-20s%-20s", tempTrainer.getTrainer_id(), tempTrainer.getFirst_name(),
                                tempTrainer.getLast_name(), tempTrainer.getSubject()));
                    }

                    break;
                case 8:
                    System.out.println("All the assignments are: ");
                    System.out.println("-------------------------------------------------------------------------------------------------");
                    AssignmentDAO asDAO = new AssignmentDAO();
                    List<Assignment> assignmentList = asDAO.getAllAssignments();

                    for (Assignment tempAssignment : assignmentList) {
                        System.out.println(String.format("%-10s%-30s%-20s%-20s%-10s%-10s", tempAssignment.getAssignment_id(), tempAssignment.getTitle(),
                                tempAssignment.getDescription(), tempAssignment.getSub_date_time(), tempAssignment.getOral_mark(), tempAssignment.getTotal_mark()));
                    }

                    break;
                case 9:
                    System.out.println("All the students per course are: ");
                    System.out.println("--------------------------------------------------");
                    StudentPerCourseDAO stcoDAO = new StudentPerCourseDAO();
                    List<StudentPerCourse> st_courList = stcoDAO.getAllStudentsPerCourse();

                    for (StudentPerCourse tempStudents : st_courList) {
                        System.out.println(String.format("%-20s%-20s%-20s", tempStudents.getStream(),
                                tempStudents.getFirst_name(), tempStudents.getLast_name()));
                    }

                    break;
                case 10:
                    System.out.println("All the trainers per course are: ");
                    System.out.println("----------------------------------------------------");
                    TrainerPerCourseDAO trcoDAO = new TrainerPerCourseDAO();
                    List<TrainerPerCourse> tr_courList = trcoDAO.getAllTrainersPerCourse();

                    for (TrainerPerCourse tempTrainers : tr_courList) {
                        System.out.println(String.format("%-20s%-20s%-20s", tempTrainers.getStream(),
                                tempTrainers.getFirst_name(), tempTrainers.getLast_name()));
                    }

                    break;
                case 11:
                    System.out.println("All the assignments per course are: ");
                    System.out.println("-------------------------------------------");
                    AssignmentPerCourseDAO ascoDAO = new AssignmentPerCourseDAO();
                    List<AssignmentPerCourse> as_courList = ascoDAO.getAllAssignmentsPerCourse();

                    for (AssignmentPerCourse tempAssignments : as_courList) {
                        System.out.println(String.format("%-20s%-20s", tempAssignments.getStream(),
                                tempAssignments.getTitle()));
                    }

                    break;
                case 12:
                    System.out.println("All the assignments per course per student are: ");
                    System.out.println("-----------------------------------------------------------------------------------");
                    AssignPerCoursePerStudentDAO as_co_stDAO = new AssignPerCoursePerStudentDAO();
                    List<AssignPerCoursePerStudent> as_co_stList = as_co_stDAO.getAllAssignmentsPerCoursePerStudent();

                    for (AssignPerCoursePerStudent tempAssignments : as_co_stList) {
                        System.out.println(String.format("%-20s%-30s%-20s%-20s", tempAssignments.getFirst_name(), tempAssignments.getLast_name(),
                                tempAssignments.getStream(), tempAssignments.getTitle()));
                    }

                    break;
                case 13:
                    System.out.println("Students who belong to more than one courses are: ");
                    System.out.println("----------------------------------------------");
                    StudentMoreCoursesDAO stmoDAO = new StudentMoreCoursesDAO();
                    List<StudentMoreCourses> stmoList = stmoDAO.getStudentMoreCourses();

                    for (StudentMoreCourses tempStudents : stmoList) {
                        System.out.println(String.format("%-20s%-20s",
                                tempStudents.getFirst_name(), tempStudents.getLast_name()));
                    }
                    break;
                case 14:
                    System.out.println("Exit menu");
                    return;
                default:
                    System.out.println("Invalid number option");

            }
            System.out.println("Please, select your next action");

        } while (optionNumber >= 1 && optionNumber < 14);
    }
}
