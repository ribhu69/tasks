package database;

import classes.Student;
import classes.StudentAssignment;

import java.util.ArrayList;
import java.util.List;

public class SubmittedAssignment {
    private static List<StudentAssignment> submittedAssignment = new ArrayList<>();

    public void initializeSubmittedAssignment()
    {
        submittedAssignment.add(new StudentAssignment("ge101","100101",1));
        submittedAssignment.add(new StudentAssignment("ge101","100105",1));
        submittedAssignment.add(new StudentAssignment("me101","100104",1));
        submittedAssignment.add(new StudentAssignment("ge101","100103",1));

    }

    protected void submitAssignment(String courseCode, String studentID, int topicNumber)
    {
        for(StudentAssignment i:submittedAssignment)
        {
            if((i.getCourseCode().equals(courseCode) && i.getStudentID().equals(studentID)) && i.getTopicNumber()==topicNumber)
            {
                System.out.println("Assignment Already Submitted");
                return;
            }
        }
        submittedAssignment.add(new StudentAssignment(courseCode,studentID,topicNumber));
        System.out.println("Assignment Submitted");

    }

    protected boolean isAssignmentSubmitted(String courseCode, String studentID, int topicNumber)
    {
        for(StudentAssignment i:submittedAssignment)
            if((i.getCourseCode().equals(courseCode) && i.getStudentID().equals(studentID)) && (i.getTopicNumber()==topicNumber))
                return true;
        return false;
    }

    protected List<String> getStudentID(String courseCode, int topicNumber)
    {
        List<String> studentIdList = new ArrayList<>();
        for(StudentAssignment i:submittedAssignment)
        {
            if(i.getCourseCode().equals(courseCode) && i.getTopicNumber()==topicNumber)
            {
                studentIdList.add(i.getStudentID());
            }
        }
        return studentIdList;
    }



}
