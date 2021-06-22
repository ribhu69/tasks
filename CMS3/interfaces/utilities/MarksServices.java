package interfaces.utilities;

import classes.Course;
import classes.Teacher;

import java.io.IOException;
import java.util.List;

public interface MarksServices {

    void modifyCourseMarks(List<String> teacherCourses) throws IOException;
    void generateStudentReport(int assignmentMarks, String studentID,String courseID);
    void updateCourseTeacher(String courseID, int teacherID) throws IOException;
}
