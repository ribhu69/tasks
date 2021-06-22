package interfaces.utilities;

import classes.Admin;
import classes.Course;
import classes.Teacher;

import java.io.IOException;
import java.util.List;
import java.util.List;

public interface CourseServices {

    List<String> getTeacherCourseList();
    String getCourseAdmin();
    String getCourseID();
    void modifyCourseTeacher() throws IOException;
    String deleteCourse() throws IOException;
    void iterateCourseList();
    String getCourseID(String courseName);
    String getCourseName(String courseID);
    Course addCourse() throws IOException;
    void allotCourseToTeacher(String courseID, Teacher teacher) throws IOException;
    Course getExistingCourse();
    String changeCourseTitle(List<Course> allCourseList) throws IOException;
    List<Course> getCourseListAdmin(Admin admin);
    Course getRequestedCourse();


}
