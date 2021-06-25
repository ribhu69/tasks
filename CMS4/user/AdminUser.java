package user;

import admin.Admin;
import admin.AdminHelper;
import assignment.SubmittedAssignmentHelper;
import assignment.SubmittedAssignmentServices;
import course.*;
import enrolledCourse.EnrolledCourseHelper;
import enrolledCourse.EnrolledCourseServices;
import marks.MarksServices;
import marks.StudentMarksHelper;
import student.Student;
import student.StudentHelper;
import teacher.Teacher;
import databaseClass.DatabaseInitiator;
import helper.*;
import admin.AdminInterface;
import admin.AdminServices;
import student.StudentServices;
import teacher.TeacherServices;
import teacher.TeacherHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AdminUser implements AdminInterface {
    private Admin currentAdmin;
    private AdminServices adminServices;
    private CourseServices courseServices;
    private CourseTopicServices courseTopicServices;
    private StudentServices studentServices;
    private MarksServices marksServices;
    private TeacherServices teacherServices;
    private String courseID;
    private Course course;
    private SubmittedAssignmentServices submittedAssignmentServices;
    private EnrolledCourseServices enrolledCourseServices;
    private DatabaseInitiator databaseInitiator = new DatabaseInitiator();
    Scanner sc = new Scanner(System.in);
    List<Admin> adminList = new ArrayList<>();
    AdminHelper adminHelper;


    @Override
    public Admin isAdminAccountValid() {
        adminList = databaseInitiator.getAdminAccounts();
        String userID,password;
        System.out.println("Enter Admin ID");
        userID = sc.nextLine();
        System.out.println("Enter Password");
        PasswordHelper passwordHelper = new PasswordHelper();
        password = passwordHelper.encryptPassword(sc.nextLine());


        for(Admin i:adminList)
        {
            if(String.valueOf(i.getAdminID()).equals(userID) && String.valueOf(i.getAdminPin()).equals(password))
            {
                currentAdmin = i;
                return currentAdmin;
            }
        }
        return null;
    }

    @Override
    public void welcome(Admin admin) throws IOException {
        System.out.println("Welcome "+admin.getAdminName());
        databaseInitiator.initiateDatabase();
        databaseInitiator.initiateDatabase();
        enrolledCourseServices = new EnrolledCourseHelper(databaseInitiator.getEnrolledStudents());
        studentServices = new StudentHelper(databaseInitiator.getStudentAccounts());
        submittedAssignmentServices = new SubmittedAssignmentHelper(databaseInitiator.getSubmittedAssignments());
        adminServices = new AdminHelper(databaseInitiator.getAdminAccounts());
        courseServices = new CourseHelper(databaseInitiator.getCourseList());
        courseTopicServices = new CourseTopicHelper(databaseInitiator.getCourseTopics());
        teacherServices = new TeacherHelper(databaseInitiator.getTeacherAccounts());
        marksServices = new StudentMarksHelper(databaseInitiator.getStudentMarksList());
        adminMenu();
    }

    private void adminMenu() throws IOException {
        int option;
        do {
            System.out.println("Choose from below options" +
                    "\n--------------------------" +
                    "\n1) Add New Admin" +
                    "\n2) Remove Admin" +
                    "\n--------------------------" +
                    "\n3) Add New Course" +
                    "\n4) Add New Course Teacher" +
                    "\n5) Modify Existing Course" +
                    "\n6) Modify Course Teacher Details" +
                    "\n7) Delete Course" +
                    "\n--------------------------" +
                    "\n8) Add New Student" +
                    "\n9) Remove Student" +
                    "\n10) Get List of Course" +
                    "\n11) Get List of Enrolled Students" +
                    "\n--------------------------" +
                    "\n12) Add Students to a Course" + //
                    "\n13) Allot Course to a Teacher" + //
                    "\n14) Remove Student from a Course" + //
                    "\n15) Change Teacher for a Course" + //
                    "\n--------------------------" +
                    "\n16) Generate Student Result" + //
                    "\n17) Logout");

            option = sc.nextInt();
            switch (option) {

                case 1://Add New Admin (done)
                    adminServices.addNewAdmin();
                    updateAdminDatabase();

                    break;

                case 2://Remove Admin (done)
                    adminServices.removeAdmin(currentAdmin);
                    updateAdminDatabase();
                    break;

                case 3://Add New Course (done)
                    course = courseServices.addCourse();
                    if (course != null) {
                        courseTopicServices.addCourseTopics(course.getCourseName(), course.getCourseID());

                        updateCourse();
                        updateCourseTopics();

                    } else
                        System.out.println("Error occurred while adding new course.");
                    break;

                case 4: //Add New Course Teacher (done)
                    teacherServices.addTeacher();

                    updateTeacherDatabase();

                    break;

                case 5: //Modify Existing Course

                    int choice = 0;

                    do {

                        try {
                            System.out.println("Choose from below options" +
                                    "\n1) Change Course Name" +
                                    "\n2) Change Course Topic" +
                                    "\n3) Add New Course Topic" +
                                    "\n4) Delete Course Topic" +
                                    "\n5) Return to previous menu.");

                            choice = sc.nextInt();

                            switch (choice) {

                                case 1://Change Course Name
                                    String courseName = courseServices.changeCourseTitle(courseServices.getCourseList());


                                    courseID = "";
                                    if (courseName != null) {
                                        courseID = courseServices.getCourseID(courseName);

                                        updateCourse();

                                        if (courseID != null) {
                                            courseTopicServices.changeCourseTitle(courseID, courseName);

                                            updateCourseTopics();
                                        }
                                    } else
                                        System.out.println("Error Occurred.");
                                    break;

                                case 2://Change Course Topic (done)
                                    courseTopicServices.changeCourseTopic();

                                    updateCourseTopics();

                                    break;

                                case 3: //add new course topic(done)
                                    courseTopicServices.addNewCourseTopic(courseServices.getCourseAdmin());

                                    updateCourseTopics();
                                    break;

                                case 4: //delete specific course topic (done)
                                    courseTopicServices.deleteSpecificCourseTopic(courseServices.getCourseAdmin());

                                    updateCourseTopics();
                                    break;

                            }
                        }  catch(InputMismatchException e)

                        {
                            System.out.println("Enter An appropriate Choice.");
                            sc.next();
                        }
                    }while (choice!=5);

                    break;


                case 6://Modify Course Teacher Details (done)
                    courseServices.modifyCourseTeacher();

                    updateCourse();
                    break;

                case 7://Delete Course (done)
                    courseID = courseServices.deleteCourse();

                    updateCourse();

                    if(courseID!=null)
                    {
                        courseTopicServices.deleteCourseTopic(courseID);

                        updateCourseTopics();
                    }
                    else
                        System.out.println("No Such Course Exists in Database.");
                    break;

                case 8: //Add Student Done
                    studentServices.addStudent();

                    updateStudentDatabase();


                    break;

                case 9: //REMOVE STUDENT done
                    studentServices.removeStudent();

                    updateStudentDatabase();
                    break;

                case 10: //Get Course List (done)
                    courseServices.iterateCourseList();
                    break;

                case 11: //Get List of Enrolled Students (done)
                    studentServices.iterateStudentList();
                    break;

                case 12:  //Add Students to a Course (DONE)

                    courseID = courseServices.getCourseAdmin();
//                    System.out.println("course id is: "+courseID);
                    String courseName = courseServices.getCourseName(courseID);
                    System.out.println("Chosen course name is: "+courseName);
                    List<Student> studentList = studentServices.getStudentByDetails();
                    System.out.println("Enter Total number of Classes");
                    int classes = sc.nextInt();
                    enrolledCourseServices.addStudentToCourse(courseName,courseID.toUpperCase(),classes,studentList);

                    updateEnrolledCourseDatabase();



                    break;

                case 13: //Allot Course to a Teacher (done)
                    course = courseServices.getExistingCourse();
                    System.out.println(course.getCourseName());
                    Teacher teacher = teacherServices.getExistingTeacher();
                    System.out.println(teacher.getTeacherName());
                    courseServices.allotCourseToTeacher(course.getCourseID(), teacher);

                    updateCourse();

                    break;

                case 14: //Remove Student from a Course
                    String studentID = studentServices.getStudentIdAdmin();
                    courseID = studentServices.getStudentCourseID(enrolledCourseServices.getEnrolledCourses(),studentID);
//                    enrolledCourseServices.removeStudent(studentID,courseID);

                    if(studentID!=null && courseID!=null)
                    {
                        enrolledCourseServices.removeStudent(studentID, courseID);

                        updateEnrolledCourseDatabase();

                    }

                    else
                        System.out.println("Please check the student and course details.");
                    break;

                case 15: //Change Teacher for a course (done)
                    course = courseServices.getRequestedCourse();
                    teacher = teacherServices.getCourseTeacherAdmin();

                    System.out.println("Selected Course: "+course.getCourseID());
                    System.out.println("Selected Teacher: "+teacher.getTeacherId());
                    courseServices.allotCourseToTeacher(course.getCourseID(),teacher);
                    updateCourse();

                    marksServices.updateCourseTeacher(course.getCourseID(),teacher.getTeacherId());
                    updateMarksDatabase();


                    break;

                case 16: //generate Student Results (done)
                    studentID = studentServices.getStudentIdAdmin();
                    List<String> studentCourses = enrolledCourseServices.getStudentEnrolledCourses(studentID);
                    if(studentCourses!=null)
                    {
                        for(String courseID:studentCourses)
                        {
                            int assignmentMarks = submittedAssignmentServices.getCumulativeAssignmentMarks(courseID,studentID);
                            marksServices.generateStudentReport(assignmentMarks,studentID,courseID);
                        }
                    }
                    break;

                case 17:
                    break;

                default:
                    System.out.println("Choose a valid option.");

            }

        } while (option != 17);
    }

    private void updateMarksDatabase() throws IOException {
        databaseInitiator.updateStudentMarkList(marksServices.getCourseMarksList());
        marksServices.updateStudentMarkList(databaseInitiator.getStudentMarksList());
    }

    private void updateEnrolledCourseDatabase() throws IOException {
        databaseInitiator.updateEnrolledCourse(enrolledCourseServices.getEnrolledCourses());
        enrolledCourseServices.updateEnrolledCourses(databaseInitiator.getEnrolledStudents());
    }

    private void updateStudentDatabase() throws IOException {

        databaseInitiator.updateStudentAccounts(studentServices.getStudentList());
        studentServices.updateStudentList(databaseInitiator.getStudentAccounts());
    }

    private void updateTeacherDatabase() throws IOException {
        databaseInitiator.updateTeacherAccounts(teacherServices.getTeacherList());
        teacherServices.updateTeacherAccounts(databaseInitiator.getTeacherAccounts());
    }

    private void updateCourseTopics() throws IOException {
        databaseInitiator.updateCourseTopics(courseTopicServices.getCourseTopicList());
        courseTopicServices.updateCourseTopics(databaseInitiator.getCourseTopics());
    }

    private void updateAdminDatabase() throws IOException {
        databaseInitiator.updateAdminAccounts(adminServices.getAdminList());
        adminServices.updateAdminList(databaseInitiator.getAdminAccounts());
    }

    private void updateCourse() throws IOException {
        databaseInitiator.updateCourseList(courseServices.getCourseList());
        courseServices.updateCourseList(databaseInitiator.getCourseList());
    }

}
