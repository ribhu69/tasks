package interfaces.utilities;

import classes.StudentAssignment;

import java.io.IOException;
import java.util.List;

public interface AssignmentServices {

    void addAssignment(List<String> teacherCourseList) throws IOException;
    void showCourseAssignments(List<String> courseList);
    void submitAssignment(List<String> courseList,String studentID) throws IOException;
    List<StudentAssignment> markAssignment(List<String> teacherCourseList,List<StudentAssignment> studentAssignments);


}
