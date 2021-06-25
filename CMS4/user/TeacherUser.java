package user;

import assignment.AssignmentHelper;
import assignment.AssignmentServices;
import assignment.SubmittedAssignmentHelper;
import assignment.SubmittedAssignmentServices;
import assignment.StudentAssignment;
import course.CourseHelper;
import course.CourseServices;
import enrolledCourse.EnrolledCourseHelper;
import enrolledCourse.EnrolledCourseServices;
import marks.MarksServices;
import marks.StudentMarksHelper;
import student.StudentHelper;
import teacher.Teacher;
import databaseClass.DatabaseInitiator;
import helper.*;
import teacher.TeacherHelper;
import teacher.TeacherInterface;
import student.StudentServices;
import teacher.TeacherServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeacherUser implements TeacherInterface {

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
    private DatabaseInitiator databaseInitiator = new DatabaseInitiator();
    TeacherHelper teacherHelper;

    @Override
    public Teacher isTeacherAccountValid()  {
        teacherList = databaseInitiator.getTeacherAccounts();
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
//                teacherHelper = new TeacherHelper(currentTeacher);
                return currentTeacher;
            }
        }
        return null;
    }

    public void welcome(Teacher teacher) throws IOException {
        System.out.println("Hello "+teacher.getTeacherName());
        databaseInitiator.initiateDatabase();

        enrolledCourseServices = new EnrolledCourseHelper(databaseInitiator.getEnrolledStudents());
        teacherServices = new TeacherHelper(databaseInitiator.getTeacherAccounts());
        studentServices = new StudentHelper(databaseInitiator.getStudentAccounts());
        assignmentServices = new AssignmentHelper(databaseInitiator.getAssignments(),
                databaseInitiator.getSubmittedAssignments());
        submittedAssignmentServices = new SubmittedAssignmentHelper(databaseInitiator.getSubmittedAssignments());

        courseServices = new CourseHelper(databaseInitiator.getCourseList());
        //enrolledCourseServices = new EnrolledCourseHelper(currentTeacher);
//        courseServices = new CourseHelper(currentTeacher);
//        assignmentServices = new AssignmentHelper(currentTeacher);
       // studentServices = new StudentHelper(currentTeacher);
//        submittedAssignmentServices = new SubmittedAssignmentHelper(currentTeacher);
        marksServices = new StudentMarksHelper(databaseInitiator.getStudentMarksList());
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
                case 1://get Student Details (done)
                    studentID = teacherServices.getStudentID(courseServices.getTeacherCourseList(currentTeacher),
                            enrolledCourseServices.getEnrolledCourses());
                    if(studentID!=null)
                        studentServices.getStudentDetails(studentID);
                    break;

                case 2:
                    //Modify Course Exam Marks (done)
                    marksServices.modifyCourseMarks(courseServices.getTeacherCourseList(currentTeacher), currentTeacher);

                    databaseInitiator.updateStudentMarkList(marksServices.getCourseMarksList());
                    marksServices.updateStudentMarkList(databaseInitiator.getStudentMarksList());
                    break;

                case 3:
                    //generate marks report of student -> (done)

                    String courseID = courseServices.getCourseID(currentTeacher);
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

                case 4: //allot attendance (done)
                    enrolledCourseServices.allotAttendance(courseServices.getTeacherCourseList(currentTeacher));
                    databaseInitiator.updateEnrolledCourse(enrolledCourseServices.getEnrolledCourses());
                    enrolledCourseServices.updateEnrolledCourses(databaseInitiator.getEnrolledStudents());
                    break;

                case 5://Get Your Details (done)
                    teacherServices.getTeacherDetails(currentTeacher);
                    break;

                case 6://Add new Assignment (done)
                    assignmentServices.addAssignment(courseServices.getTeacherCourseList(currentTeacher));
                    databaseInitiator.updateAssignmentsList(assignmentServices.getAssignmentsList());
                    assignmentServices.updateAssignmentList(databaseInitiator.getAssignments());
                    break;

                case 7: // Mark Student Assignment //to be done
                    List<StudentAssignment> tempList =
                            assignmentServices.markAssignment(courseServices.getTeacherCourseList(currentTeacher),
                            submittedAssignmentServices.getAssignmentList());

                    databaseInitiator.updateSubmittedAssignmentList(tempList);
                    submittedAssignmentServices.updateSubmittedAssignmentList(tempList);
//                    databaseInitiator.update
//                    submittedAssignmentServices.updateAssignmentDatabase(tempList);
                    break;

                case 8://change account password (done)

                    databaseInitiator.updateTeacherAccounts(teacherServices.
                            changeAccountPassword(currentTeacher));
                    teacherServices.updateTeacherAccounts(databaseInitiator.getTeacherAccounts());
                    break;

                case 9:
                    break;
            }
        }while(option!=9);
    }
}
