package enrolledCourse;

import enrolledCourse.EnrolledStudent;
import student.Student;

import java.io.IOException;
import java.util.List;

public interface EnrolledCourseServices {
    List<String> getStudentEnrolledCourses(Student student);

    List<String> getStudentEnrolledCourses(String studentID);

    void addStudentToCourse(String courseName, String courseID, int totalClasses, List<Student> studentList) throws IOException;
    void showEnrolledCourses(Student student);
    List<String> getCourseList(Student student);
    void getStudentAttendance(Student student);
    void allotAttendance(List<String> courseList) throws IOException;
    void removeStudent(String studentID, String courseID) throws IOException;


    void updateEnrolledCourses(List<EnrolledStudent> enrolledStudents);
    List<EnrolledStudent> getEnrolledCourses();
}
