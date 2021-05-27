package utilityClasses;

import classes.Course;
import userHelper.CourseHelper;
import userHelper.StudentHelper;
import userHelper.TeacherHelper;

import java.util.List;
import java.util.Scanner;


public class TeacherUtility extends TeacherHelper {
    Scanner sc = new Scanner(System.in);

    StudentHelper studentHelper = new StudentHelper();
    EnrolledCourseUtility enrolledCourseUtility = new EnrolledCourseUtility();
    MarksUtility marksUtility = new MarksUtility();
    AssignmentUtility assignmentUtility = new AssignmentUtility();
    CourseHelper courseHelper = new CourseHelper();

    public void getStudentInfo()
    {
       studentHelper.getStudentInfo();
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

        enrolledCourseUtility.allotAttendance(courseID);

    }

    protected void getStudentReport()
    {
     marksUtility.getStudentReport();
    }

    protected void addAssignment()
    {
        assignmentUtility.addAssignment();
    }

    protected void modifyCourseMarks() {marksUtility.modifyCourseMarks();}

    protected void markStudentAssignment(String teacherID) { assignmentUtility.markStudentAssignment(teacherID);}



}
