package helper;

import databaseClass.EnrolledCourseDatabase;

import java.util.List;

public class EnrolledCourseHelper extends EnrolledCourseDatabase {

    public void checkAttendance(String studentID)
    {
        super.getAttendance(studentID);
    }

    public void initiateEnrolledCourseDatabase()
    {
        super.initializeEnrolledCourseList();
    }

    public void updateDatabase()
    {
        super.updateEnrolledCourseList();
    }

    public void getEnrolledStudentList(String courseID) { super.showEnrolledStudents(courseID);}

    public void allotAttendance(String courseID){super.allotAttendance(courseID);}

    public List<String> getEnrolledCourseList(String studentID){ return super.getEnrolledCourses(studentID);}

    public void getStudentEnrolledCourseList(String studentID) {super.getStudentEnrolledCourseName(studentID);}

}


