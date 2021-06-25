package marks;

import marks.CourseMarks;
import teacher.Teacher;

import java.io.IOException;
import java.util.List;

public interface MarksServices {

    void modifyCourseMarks(List<String> teacherCourses, Teacher teacher) throws IOException;
    List<CourseMarks> getCourseMarksList();
    void updateStudentMarkList(List<CourseMarks> courseMarksList);
    void generateStudentReport(int assignmentMarks, String studentID,String courseID);
    void updateCourseTeacher(String courseID, int teacherID) throws IOException;
}
