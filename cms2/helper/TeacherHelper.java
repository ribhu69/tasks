package helper;

import classes.Assignment;
import classes.Course;
import databaseClass.TeacherAccounts;

import java.util.List;
import java.util.Scanner;

public class TeacherHelper extends TeacherAccounts {

    Scanner sc = new Scanner(System.in);
    StudentHelper studentHelper = new StudentHelper();
    MarksUtility marksUtility = new MarksUtility();
    EnrolledCourseHelper enrolledCourseHelper = new EnrolledCourseHelper();
    CourseHelper courseHelper = new CourseHelper();
    AssignmentHelper assignmentHelper = new AssignmentHelper();
    protected void getStudentInfo() {
        studentHelper.getStudentInfo();
    }

    protected void modifyCourseMarks() {marksUtility.modifyCourseMarks();}

    protected void getStudentReport()
    {
        marksUtility.getStudentReport();
    }


    protected void markStudentAssignment(String teacherID) { assignmentHelper.markStudentAssignment(teacherID);}

    protected void addAssignment(){

        assignmentHelper.addAssignment();

    }

    protected void allotAttendance(String teacherID)
    {
        int flag=1, courseNumber;
        List<String> teacherCourseList = courseHelper.getTeacherCourseList(Integer.parseInt(teacherID));


        System.out.println("** Your Assigned Courses **");
        for(int i=0;i<teacherCourseList.size();i++)
        {
            System.out.println((i+1)+") "+teacherCourseList.get(i));
        }
        System.out.println("Select Course: ");

        do {
            courseNumber = sc.nextInt();
            if(courseNumber<=0 || courseNumber>teacherCourseList.size())
            {
                System.out.println("Please Enter a valid Choice");
            }
            else flag=0;

        }while(flag!=0);

        String courseID = teacherCourseList.get(courseNumber-1);

        enrolledCourseHelper.allotAttendance(courseID);

    }
}
