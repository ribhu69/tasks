package teacher;

import enrolledCourse.EnrolledStudent;

import java.io.IOException;
import java.util.List;

public interface TeacherServices {
    void getTeacherDetails(Teacher teacher);
    Teacher getExistingTeacher();
    Teacher getCourseTeacherAdmin();
    void addTeacher() throws IOException;
    List<Teacher> changeAccountPassword(Teacher teacher) throws IOException;
    String getStudentID(List<String> teacherCourseLists,List<EnrolledStudent> enrolledStudentsList);
    String getStudentID(String courseID,List<EnrolledStudent> enrolledStudentsList);

    List<Teacher> getTeacherList();
    void updateTeacherAccounts(List<Teacher> teacherList);





}
