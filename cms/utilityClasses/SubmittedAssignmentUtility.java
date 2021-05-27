package utilityClasses;

import database.SubmittedAssignment;

import java.util.List;

public class SubmittedAssignmentUtility extends SubmittedAssignment {
    protected void submitStudentAssignment(String courseCode, String studentID, int topicNumber)
    {
        super.submitAssignment(courseCode,studentID,topicNumber);
    }

    public List<String> getStudentIdList(String courseID, int topicNumber)
    {
        return super.getStudentID(courseID,topicNumber);
    }
}
