package user;

import classes.*;
import databaseClass.AdminAccounts;
import helper.*;
import interfaces.AdminInterface;
import interfaces.services.AdminServices;
import interfaces.services.StudentServices;
import interfaces.services.TeacherServices;
import interfaces.utilities.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminUser extends AdminAccounts implements AdminInterface {
    private Admin currentAdmin;
    private AdminServices adminServices;
    private CourseServices courseServices;
    private CourseTopicServices courseTopicServices;
    private StudentServices studentServices;
    private MarksServices marksServices;
    private TeacherServices teacherServices;
    private String courseID;
    private Course course;
    private Teacher teacher;
    private SubmittedAssignmentServices submittedAssignmentServices;
    private EnrolledCourseServices enrolledCourseServices;
    Scanner sc = new Scanner(System.in);
    List<Admin> adminList = new ArrayList<>();
    AdminHelper adminHelper;

    @Override
    public void welcome(Admin admin) throws IOException {
        System.out.println("Welcome "+admin.getAdminName());
        adminServices = new AdminHelper(currentAdmin);
        courseServices = new CourseHelper(currentAdmin);
        courseTopicServices = new CourseTopicHelper(currentAdmin);
        teacherServices = new TeacherHelper(currentAdmin);
        studentServices = new StudentHelper(currentAdmin);
        marksServices = new StudentMarksHelper(currentAdmin);
        enrolledCourseServices = new EnrolledCourseHelper(currentAdmin);
        submittedAssignmentServices = new SubmittedAssignmentHelper(currentAdmin);
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

                case 1://Add New Admin
                    adminServices.addNewAdmin();
                    break;

                case 2://Remove Admin
                    adminServices.removeAdmin();
                    break;

                case 3://Add New Course
                    course = courseServices.addCourse();
                    if (course != null) {
                        courseTopicServices.addCourseTopics(course.getCourseName(), course.getCourseID());
                    } else
                        System.out.println("Error occurred while adding new course.");
                    break;

                case 4: //Add New Course Teacher
                    teacherServices.addTeacher();
                    break;

                case 5: //Modify Existing Course
                    System.out.println("Choose from below options" +
                            "\n1) Change Course Name" +
                            "\n2) Change Course Topic" +
                            "\n3) Add New Course Topic" +
                            "\n4) Delete Course Topic"+
                            "\n5) Return to previous menu.");

                    int choice = sc.nextInt();
                    switch (choice)
                    {

                        case 1://Change Course Name
                            String courseName = courseServices.changeCourseTitle(courseServices.getCourseListAdmin(currentAdmin));
                            courseID = "";
                            if (courseName != null) {
                                courseID = courseServices.getCourseID(courseName);
                                if (courseID != null) {
                                    courseTopicServices.changeCourseTitle(courseID, courseName);
                                }
                            } else
                                System.out.println("Error Occurred.");
                            break;

                        case 2://Change Course Topic
                            courseTopicServices.changeCourseTopic();
                            break;

                        case 3:
                            courseTopicServices.addNewCourseTopic(courseServices.getCourseAdmin());
                            break;

                        case 4:
                            courseTopicServices.deleteSpecificCourseTopic(courseServices.getCourseAdmin());
                            break;

                        case 5:
                            break;
                    }

                case 6://Modify Course Teacher Details (to be done)
                    courseServices.modifyCourseTeacher();
                    break;

                case 7://Delete Course
                    courseID = courseServices.deleteCourse();
                    if(courseID!=null)
                    {
                        courseTopicServices.deleteCourseTopic(courseID);
                    }
                    else
                        System.out.println("No Such Course Exists in Database.");
                    break;

                case 8:
                    studentServices.addStudent(currentAdmin);
                    break;

                case 9:
                    studentServices.removeStudent(currentAdmin);
                    break;

                case 10: //Get Course List
                    courseServices.iterateCourseList();
                    break;

                case 11: //Get List of Enrolled Students
                    studentServices.iterateStudentList();
                    break;

                case 12:  //Add Students to a Course

                    courseID = courseServices.getCourseAdmin();
                    System.out.println(courseID);
                    String courseName = courseServices.getCourseName(courseID);
                    System.out.println(courseName);
                    List<Student> studentList = studentServices.getStudentByDetails();
                    System.out.println("Enter Total number of Classes");
                    int classes = sc.nextInt();
                    enrolledCourseServices.addStudentToCourse(courseName,courseID.toUpperCase(),classes,studentList);

                    break;

                case 13: //Allot Course to a Teacher
                    course = courseServices.getExistingCourse();
                    Teacher teacher = teacherServices.getExistingTeacher();
                    courseServices.allotCourseToTeacher(course.getCourseID(), teacher);
                    break;

                case 14: //Remove Student from a Course
                    String studentID = studentServices.getStudentIdAdmin();
                    courseID = studentServices.getStudentCourseID(enrolledCourseServices.getEnrolledCourses(),studentID);
                    enrolledCourseServices.removeStudent(studentID,courseID);

                    if(studentID!=null && courseID!=null)
                        enrolledCourseServices.removeStudent(studentID, courseID);
                    else
                        System.out.println("Please check the student and course details.");
                    break;

                case 15: //Change Teacher for a course
                    course = courseServices.getRequestedCourse();
                    teacher = teacherServices.getCourseTeacherAdmin();

                    System.out.println("Selected Course: "+course.getCourseID());
                    System.out.println("Selected Teacher: "+teacher.getTeacherId());
                    courseServices.allotCourseToTeacher(course.getCourseID(),teacher);
                    marksServices.updateCourseTeacher(course.getCourseID(),teacher.getTeacherId());
                    break;

                case 16:
                    studentID = studentServices.getStudentIdAdmin();
                    List<String> studentCourses = enrolledCourseServices.getStudentEnrolledCourses(studentID);
                    for(String courseID:studentCourses)
                    {
                        int assignmentMarks = submittedAssignmentServices.getCumulativeAssignmentMarks(courseID,studentID);
                        marksServices.generateStudentReport(assignmentMarks,studentID,courseID);
                    }
                    break;

                case 17:
                    break;

                default:
                    System.out.println("Choose a valid option.");

            }

        } while (option != 17);
    }

    @Override
    public Admin isAdminAccountValid() throws IOException {
        adminList = super.getAdminList();
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
                adminHelper = new AdminHelper(currentAdmin);
                return currentAdmin;
            }
        }
        return null;
    }
}
