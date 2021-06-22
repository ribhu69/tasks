package interfaces.utilities;

import classes.Assignment;
import classes.StudentAssignment;

import java.io.IOException;
import java.util.List;

public interface SubmittedAssignmentServices {

    List<StudentAssignment> getSubmittedAssignmentList(String studentID);

    List<StudentAssignment> getAssignmentList();

    void getAssignmentMarks(String studentID);

    void submitAssignment(String courseCode, String studentID, int topicNumber) throws IOException;

    void updateAssignmentDatabase(List<StudentAssignment> studentAssignments) throws IOException;

    int getCumulativeAssignmentMarks(String courseID, String studentID);


}
