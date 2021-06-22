package interfaces.services;

import classes.Admin;
import classes.EnrolledStudent;
import classes.Student;

import java.io.IOException;
import java.util.List;

public interface StudentServices {
    void getStudentDetails();
    void getStudentDetails(String studentID);

    String getStudentIdAdmin();
    String getStudentCourseID(List<EnrolledStudent> courseList,String studentID);
    void getStudentEnrolledCourses(List<EnrolledStudent> courseList);
    void changeAccountPassword(String studentID) throws IOException;
    void addStudent(Admin admin) throws IOException;
    void removeStudent(Admin admin) throws IOException;
    void iterateStudentList();
    List<Student> getStudentByDetails();

}
