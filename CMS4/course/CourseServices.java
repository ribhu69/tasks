package course;

import course.Course;
import teacher.Teacher;

import java.io.IOException;
import java.util.List;

public interface CourseServices {

    List<String> getTeacherCourseList(Teacher teacher);
    String getCourseAdmin();
    String getCourseID(Teacher teacher);
    void modifyCourseTeacher() throws IOException;
    String deleteCourse() throws IOException;
    void iterateCourseList();
    String getCourseID(String courseName);
    String getCourseName(String courseID);
    Course addCourse() throws IOException;
    void allotCourseToTeacher(String courseID, Teacher teacher) throws IOException;
    Course getExistingCourse();
    String changeCourseTitle(List<Course> allCourseList) throws IOException;
    List<Course> getCourseList();
    Course getRequestedCourse();

    void updateCourseList(List<Course> courseList);


}
