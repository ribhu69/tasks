package interfaces.utilities;

import classes.EnrolledStudent;
import classes.Student;
import databaseClass.EnrolledCourseDatabase;

import java.io.IOException;
import java.util.List;

public interface EnrolledCourseServices {
    List<String> getStudentEnrolledCourses();

    List<String> getStudentEnrolledCourses(String studentID);

    void addStudentToCourse(String courseName, String courseID, int totalClasses, List<Student> studentList) throws IOException;
    void showEnrolledCourses();
    List<String> getCourseList();
    void getStudentAttendance();
    void allotAttendance(List<String> courseList) throws IOException;
    void removeStudent(String studentID, String courseID) throws IOException;


    List<EnrolledStudent> getEnrolledCourses();
}
