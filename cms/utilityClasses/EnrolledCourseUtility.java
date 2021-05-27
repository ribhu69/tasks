package utilityClasses;

import database.EnrolledCourseDatabase;

import java.util.List;

public class EnrolledCourseUtility extends EnrolledCourseDatabase {
    public void checkAttendance(String studentID)
    {
        super.getAttendance(studentID);
    }

    public void getEnrolledStudentList(String courseID)
    {
        super.showEnrolledStudents(courseID);
    }

    public List<String> getEnrolledCourseList(String studentID){ return super.getEnrolledCourses(studentID);}

    public void getStudentEnrolledCourseList(String studentID) {super.getStudentEnrolledCourseName(studentID);}

    public void modifyStudentMarks(){};

    public void allotAttendance(String courseID){super.allotAttendance(courseID);}
}
