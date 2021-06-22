package user;

import classes.Student;
import databaseClass.StudentAccounts;
import databaseClass.SubmittedAssignment;
import helper.*;
import interfaces.StudentInterface;
import interfaces.services.StudentServices;
import interfaces.utilities.AssignmentServices;
import interfaces.utilities.EnrolledCourseServices;
import interfaces.utilities.SubmittedAssignmentServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentUser extends StudentAccounts implements StudentInterface {
    private Student currentStudent;
    private StudentServices studentServices;
    private AssignmentServices assignmentServices;
    private EnrolledCourseServices enrolledCourseServices;
    private SubmittedAssignmentServices submittedAssignmentServices;
    private List<Student> studentList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);


    public void welcome(Student student) throws IOException {
        System.out.println("Hello "+student.getStudentName());
        studentServices = new StudentHelper(currentStudent);
        assignmentServices = new AssignmentHelper(currentStudent);
        enrolledCourseServices = new EnrolledCourseHelper(currentStudent); //student -> currentStudent kia bas
        submittedAssignmentServices = new SubmittedAssignmentHelper(currentStudent);

        studentMenu();
    }

    private void studentMenu() throws IOException {
        int option;
        do {
            System.out.println("Choose from below options" +
                    "\n1) Check Course Attendance" +
                    "\n2) Submit Assignment" +
                    "\n3) Get Assignment List" +
                    "\n4) Get Assignment Marks" +
                    "\n5) Get Your Details"+
                    "\n6) Show Enrolled Courses"+
                    "\n7) Change Account Password"+
                    "\n8) Logout");
            option=sc.nextInt();
            switch (option)
            {
                case 1:
                    enrolledCourseServices.getStudentAttendance();
                    break;

                case 2: //submit assignment
                    assignmentServices.submitAssignment
                            (enrolledCourseServices.getCourseList(), currentStudent.getStudentID());
                    break;

                case 3: //Get Assignment List

                    assignmentServices.showCourseAssignments(enrolledCourseServices.getStudentEnrolledCourses());
//                    assignmentServices.getAssignmentList();

                    break;

                case 4: //Get Assignment Marks
                    submittedAssignmentServices.getAssignmentMarks(currentStudent.getStudentID());
                    break;

                case 5: //Get Student Details
                    studentServices.getStudentDetails();
                    break;

                case 6:
                    //show enrolled courses
                    enrolledCourseServices.showEnrolledCourses();
                    break;

                case 7:
                    //change account password
                    studentServices.changeAccountPassword(currentStudent.getStudentID());
                    break;

                case 8:
                    break;

                default:
                    System.out.println("Enter a Valid Choice");
            }
        }while (option!=8);
    }

    @Override
    public Student isStudentAccountValid()  {
        studentList = super.getStudentList();
        String userID,password;
        System.out.println("Enter Student ID");
        userID = sc.nextLine();
        System.out.println("Enter Password");
        PasswordHelper passwordHelper = new PasswordHelper();
        password = passwordHelper.encryptPassword(sc.nextLine());


        for(Student i:studentList)
        {
            if(i.getStudentID().equals(userID) && i.getPin().equals(password))
            {
                currentStudent = i;
                return currentStudent;
            }
        }
        return null;
    }

}
