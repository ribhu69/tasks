package student;

import enrolledCourse.EnrolledStudent;

import java.io.IOException;
import java.util.List;

public interface StudentServices {
    void getStudentDetails(Student student);
    void getStudentDetails(String studentID);

    String getStudentIdAdmin();
    String getStudentCourseID(List<EnrolledStudent> courseList, String studentID);
    void getStudentEnrolledCourses(List<EnrolledStudent> courseList, Student student);

    void updateStudentAccounts(List<Student> studentList);
    List<Student> changeAccountPassword(Student student) throws IOException;
    void addStudent() throws IOException;
    void removeStudent() throws IOException;
    void iterateStudentList();
    List<Student> getStudentByDetails();

    List<Student> getStudentList();
    void updateStudentList(List<Student> studentList);



}
