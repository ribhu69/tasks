package interfaces.services;

import classes.Course;
import classes.EnrolledStudent;
import classes.Teacher;

import java.io.IOException;
import java.util.List;

public interface TeacherServices {
    void getTeacherDetails();
    Teacher getExistingTeacher();
    Teacher getCourseTeacherAdmin();
    void addTeacher() throws IOException;
    void changeAccountPassword(String teacherID) throws IOException;
    String getStudentID(List<String> teacherCourseLists,List<EnrolledStudent> enrolledStudentsList);
    String getStudentID(String courseID,List<EnrolledStudent> enrolledStudentsList);

}
