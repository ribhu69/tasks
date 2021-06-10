package helper;

import classes.StudentAssignment;
import databaseClass.SubmittedAssignment;

import java.util.List;

public class SubmittedAssignmentHelper extends SubmittedAssignment {

    public void initiateSubmittedAssignmentList(){super.initiateSubmittedAssignmentList();}

    public void updateDatabase(){super.updateSubmittedAssignmentList();}

    public List<String> getStudentIdList(String courseID, int topicNumber)
    {
        return super.getStudentID(courseID,topicNumber);
    }

    protected void submitStudentAssignment(String courseCode, String studentID, int topicNumber)
    {
        super.submitAssignment(courseCode,studentID,topicNumber);
    }
}
