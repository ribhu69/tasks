package assignment;

import java.io.IOException;
import java.util.List;

public interface AssignmentServices {

    void addAssignment(List<String> teacherCourseList) throws IOException;
    void showCourseAssignments(List<String> courseList);
    void submitAssignment(List<String> courseList,String studentID) throws IOException;
    List<StudentAssignment> markAssignment(List<String> teacherCourseList, List<StudentAssignment> studentAssignments);

    void updateAssignmentList(List<Assignment> assignmentList);


    List<Assignment> getAssignmentsList();

    List<StudentAssignment> getSubmittedAssignmentList();


}
