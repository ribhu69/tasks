package user;

import classes.StudentAssignment;
import classes.Teacher;
import databaseClass.TeacherAccounts;
import helper.*;
import interfaces.TeacherInterface;
import interfaces.services.StudentServices;
import interfaces.services.TeacherServices;
import interfaces.utilities.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeacherUser extends TeacherAccounts implements TeacherInterface {

    private Teacher currentTeacher;
    private TeacherServices teacherServices;
    private EnrolledCourseServices enrolledCourseServices;
    private AssignmentServices assignmentServices;
    private CourseServices courseServices;
    private SubmittedAssignmentServices submittedAssignmentServices;
    private StudentServices studentServices;
    private MarksServices marksServices;
    private String studentID;
    Scanner sc = new Scanner(System.in);
    private List<Teacher> teacherList = new ArrayList<>();
    TeacherHelper teacherHelper;

    @Override
    public Teacher isTeacherAccountValid()  {
        teacherList = super.getTeacherList();
        String userID,password;
        System.out.println("Enter Teacher ID");
        userID = sc.nextLine();
        System.out.println("Enter Password");
        PasswordHelper passwordHelper = new PasswordHelper();
        password = passwordHelper.encryptPassword(sc.nextLine());

        for(Teacher i:teacherList)
        {
            if(String.valueOf(i.getTeacherId()).equals(userID) && i.getTeacherPin().equals(password))
            {
                currentTeacher = i;
                teacherHelper = new TeacherHelper(currentTeacher);
                return currentTeacher;
            }
        }
        return null;
    }

    public void welcome(Teacher teacher) throws IOException {
        System.out.println("Hello "+teacher.getTeacherName());


        teacherServices = new TeacherHelper(currentTeacher);
        enrolledCourseServices = new EnrolledCourseHelper(currentTeacher);
        courseServices = new CourseHelper(currentTeacher);
        assignmentServices = new AssignmentHelper(currentTeacher);
        studentServices = new StudentHelper(currentTeacher);
        submittedAssignmentServices = new SubmittedAssignmentHelper(currentTeacher);
        marksServices = new StudentMarksHelper(currentTeacher);
        teacherMenu();
    }


    private void teacherMenu() throws IOException {
        int option;
        do {
            System.out.println("Choose from below options" +
                    "\n1) Get Student Details" +
                    "\n2) Modify Course Exam Marks for a Student" +
                    "\n3) Generate Marks report of Student" +
                    "\n4) Allot Attendance" +
                    "\n5) Get Your Details"+
                    "\n6) Add New Assignment "+
                    "\n7) Mark Student Assignment"+
                    "\n8) Change Account Password"+
                    "\n9) Logout.");

            option = sc.nextInt();
            switch (option)
            {
                case 1:
                    studentID = teacherServices.getStudentID(courseServices.getTeacherCourseList(),
                            enrolledCourseServices.getEnrolledCourses());
                    if(studentID!=null)
                        studentServices.getStudentDetails(studentID);
                    break;

                case 2:
                    //Modify Course Exam Marks (done)
                    marksServices.modifyCourseMarks(courseServices.getTeacherCourseList());
                    break;

                case 3:
                    //generate marks report of student ->

                    String courseID = courseServices.getCourseID();
                    studentID = teacherServices.getStudentID(courseID,
                            enrolledCourseServices.getEnrolledCourses());
                    if(studentID!=null)
                    {
                        int assignmentMarks = submittedAssignmentServices.getCumulativeAssignmentMarks(courseID,studentID);
                        marksServices.generateStudentReport(assignmentMarks,studentID,courseID);
                    }
                    else
                    {
                        System.out.println("Student Not Found for mentioned Course");
                    }

                    break;

                case 4: //allot attendance
                    enrolledCourseServices.allotAttendance(courseServices.getTeacherCourseList());
                    break;

                case 5://Get Your Details
                    teacherServices.getTeacherDetails();
                    break;

                case 6://Add new Assignment
                    assignmentServices.addAssignment(courseServices.getTeacherCourseList());
                    break;

                case 7: // Mark Student Assignment
                    List<StudentAssignment> tempList = assignmentServices.markAssignment(courseServices.getTeacherCourseList(),
                            submittedAssignmentServices.getAssignmentList());
                    submittedAssignmentServices.updateAssignmentDatabase(tempList);
                    break;

                case 8://change account password
                    teacherServices.changeAccountPassword(String.valueOf(currentTeacher.getTeacherId()));
                    break;

                case 9:
                    break;
            }
        }while(option!=9);
    }
}
