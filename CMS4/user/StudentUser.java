package user;

import assignment.AssignmentHelper;
import assignment.SubmittedAssignmentHelper;
import enrolledCourse.EnrolledCourseHelper;
import student.Student;
import databaseClass.DatabaseInitiator;
import helper.*;
import student.StudentInterface;
import student.StudentHelper;
import student.StudentServices;
import assignment.AssignmentServices;
import enrolledCourse.EnrolledCourseServices;
import assignment.SubmittedAssignmentServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentUser implements StudentInterface {
    private Student currentStudent;
    private StudentServices studentServices;
    private AssignmentServices assignmentServices;
    private EnrolledCourseServices enrolledCourseServices;
    private SubmittedAssignmentServices submittedAssignmentServices;
    private List<Student> studentList = new ArrayList<>();
    private DatabaseInitiator databaseInitiator = new DatabaseInitiator();

    Scanner sc = new Scanner(System.in);

    @Override
    public Student isStudentAccountValid()  {
        studentList = databaseInitiator.getStudentAccounts();
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

    public void welcome(Student student) throws IOException {
        System.out.println("Hello "+student.getStudentName());
        databaseInitiator.initiateDatabase();
        enrolledCourseServices = new EnrolledCourseHelper(databaseInitiator.getEnrolledStudents());
        studentServices = new StudentHelper(databaseInitiator.getStudentAccounts());
        assignmentServices = new AssignmentHelper(databaseInitiator.getAssignments(),
                databaseInitiator.getSubmittedAssignments());
//        enrolledCourseServices = new EnrolledCourseHelper();
        submittedAssignmentServices = new SubmittedAssignmentHelper(databaseInitiator.getSubmittedAssignments());

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
                    //getStudentAttendance (done)
                    enrolledCourseServices.getStudentAttendance(currentStudent);
                    break;

                case 2: //submit assignment (done)
                    assignmentServices.submitAssignment
                            (enrolledCourseServices.getCourseList(currentStudent), currentStudent.getStudentID());
                    updateAssignmentDatabase();
                    updateSubmittedAssignmentDatabase();
                    break;

                case 3: //Get Assignment List (done)

                    assignmentServices.showCourseAssignments(enrolledCourseServices.getStudentEnrolledCourses(currentStudent));
//                    assignmentServices.getAssignmentList();

                    break;

                case 4: //Get Assignment Marks (done)
                    submittedAssignmentServices.getAssignmentMarks(currentStudent.getStudentID());
                    break;

                case 5: //Get Student Details (done)
                    studentServices.getStudentDetails(currentStudent);
                    break;

                case 6:
                    //show enrolled courses (done)
                    enrolledCourseServices.showEnrolledCourses(currentStudent);
                    break;

                case 7:
                    //change account password (done)

                    databaseInitiator.updateStudentAccounts(studentServices.
                            changeAccountPassword(currentStudent));
                    studentServices.updateStudentAccounts(databaseInitiator.getStudentAccounts());
                    break;

                case 8:
                    break;

                default:
                    System.out.println("Enter a Valid Choice");
            }
        }while (option!=8);
    }

    private void updateSubmittedAssignmentDatabase() throws IOException {
        databaseInitiator.updateSubmittedAssignmentList(assignmentServices.getSubmittedAssignmentList());
        assignmentServices.updateAssignmentList(databaseInitiator.getAssignments());
    }

    private void updateAssignmentDatabase() throws IOException {

        databaseInitiator.updateAssignmentsList(assignmentServices.getAssignmentsList());
        assignmentServices.updateAssignmentList(databaseInitiator.getAssignments());
    }



}
