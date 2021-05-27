package utilityClasses;


import database.CourseTopicDatabase;
import userHelper.AdminHelper;
import userHelper.CourseHelper;
import userHelper.StudentHelper;

import java.util.Scanner;

public class AdminUtility extends AdminHelper {
    Scanner sc = new Scanner(System.in);

    AdminHelper adminHelper = new AdminHelper();
    CourseHelper courseHelper = new CourseHelper();
    StudentHelper studentHelper = new StudentHelper();
    CourseTopicUtility courseTopicUtility = new CourseTopicUtility();

    protected void addCourse()
    {
        String courseName = courseHelper.addCourse();
        String courseID = courseHelper.getCourseID(courseName);
        courseTopicUtility.addCourseTopic(courseName,courseID);
    }
    protected void removeCourse()
    {
        String courseName = courseHelper.deleteCourse();
        courseTopicUtility.deleteCourseTopic(courseName);

    }
    protected void modifyExistingCourse()
    {
        courseHelper.modifyExistingCourse();
    }

    protected void modifyCourseTeacher()
    {
        courseHelper.modifyCourseTeacher();
    }

    protected void addStudentAccount()
    {
        studentHelper.addStudentAccount();
    }

    protected void removeStudent()
    {
        studentHelper.removeStudentAccount();
    }

    protected void getCourseList()
    {
        courseHelper.getCourseList();
    }

    protected void getEnrolledStudentList()
    {
        System.out.println("Enter course id");
        String courseID = sc.nextLine();
        EnrolledCourseUtility enrolledCourseUtility = new EnrolledCourseUtility();
        enrolledCourseUtility.getEnrolledStudentList(courseID);

    }


}

