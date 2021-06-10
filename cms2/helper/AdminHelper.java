package helper;
import databaseClass.AdminAccounts;
import java.util.Scanner;

public class AdminHelper extends AdminAccounts {

    Scanner sc = new Scanner(System.in);
    CourseHelper courseHelper = new CourseHelper();
    CourseTopicHelper courseTopicHelper = new CourseTopicHelper();
    StudentHelper studentHelper = new StudentHelper();
    EnrolledCourseHelper enrolledCourseHelper = new EnrolledCourseHelper();

    protected void addCourse()
    {
        String courseName = courseHelper.addCourse();
        if(courseName!=null)
        {
            String courseID = courseHelper.getCourseID(courseName);
            if(courseID!=null)
            {
                courseTopicHelper.addCourseTopic(courseName,courseID);
            }
        }
    }

    protected void getEnrolledStudentList()
    {
        System.out.println("Enter course id");
        String courseID = sc.nextLine();
        enrolledCourseHelper.getEnrolledStudentList(courseID);
    }

    protected void addStudentAccount()
    {
        studentHelper.addStudent();
    }

    protected void deleteCourse() {
        String courseName = courseHelper.deleteCourse();
        if(courseName!=null)
        {
            String courseID = courseHelper.getCourseID(courseName);
            if(courseID!=null)
            {
                courseTopicHelper.removeCourseTopic(courseID);
            }
        }
    }


    public void getCourseList() { courseHelper.getCourseList();}

    protected void removeStudent() {studentHelper.removeStudent();}

    protected void modifyCourseTeacher() {
        courseHelper.modifyCourseTeacher();
    }

    protected void modifyExistingCourse() {
        System.out.println("Choose from below options" +
                "\n1) Change Course Name" +
                "\n2) Change Course Topic" +
                "\n3) Add New Course Topic" +
                "\n4) Delete Course Topic");

        int option = sc.nextInt();
        switch (option) {
            case 1:

                String newCourseName, courseID;

                newCourseName = courseHelper.changeCourseTitle();
                if(newCourseName!=null)
                {
                    courseID = courseHelper.getCourseID(newCourseName);
                    if(courseID!=null)
                    {
                        courseTopicHelper.changeCourseTitle(newCourseName,courseID);
                    }
                }
                else
                {
                    System.out.println("Error");
                }
                break;

            case 2:
                courseTopicHelper.changeCourseTopic();
                break;

            case 3:
                courseTopicHelper.addNewCourseTopic();
                break;
            case 4:
                courseTopicHelper.deleteSpecificCourseTopic();
        }
    }

}
