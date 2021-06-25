package assignment;

import java.util.ArrayList;
import java.util.List;

public class SubmittedAssignmentHelper implements SubmittedAssignmentServices {
    private List<StudentAssignment> studentAssignments;

    public SubmittedAssignmentHelper(List<StudentAssignment> studentAssignments)
    {
        this.studentAssignments = studentAssignments;
    }

    @Override
    public List<StudentAssignment> getSubmittedAssignmentList(String studentID) {
        List<StudentAssignment> assignments = new ArrayList<>();
        for(StudentAssignment i:studentAssignments)
        {
            if(i.getStudentID().equals(studentID))
            {
                assignments.add(i);
            }
        }
        return assignments;
    }

    @Override
    public List<StudentAssignment> getAssignmentList() {
        return studentAssignments;
    }

    @Override
    public void getAssignmentMarks(String studentID) {
        int studentEntryFound=0;
        for(StudentAssignment i:studentAssignments)
        {
            if(i.getStudentID().equals(studentID))
            {
                studentEntryFound=1;
                System.out.println("Course Code: "+i.getCourseCode().toUpperCase()+
                        "\nCourse Topic Number: "+i.getTopicNumber());

                if(i.getAssignmentMarks()==-1)
                    System.out.println("Assignment Not Marked By Course Teacher");
                else
                    System.out.println("Marks Obtained Out of 10: "+i.getAssignmentMarks());
                System.out.println();
            }

        }
        if(studentEntryFound==0)
            System.out.println("No Assignments Found/Submitted.");
    }

    @Override
    public int getCumulativeAssignmentMarks(String courseID, String studentID)
    {
        int cumulativeAssignmentMarks = 0;
        for(StudentAssignment i:studentAssignments)
        {
            if(i.getCourseCode().equals(courseID) && i.getStudentID().equals(studentID) && i.getAssignmentMarks()>0)
                cumulativeAssignmentMarks+= i.getAssignmentMarks();
        }
        return cumulativeAssignmentMarks;
    }


    @Override
    public void submitAssignment(String courseCode, String studentID, int topicNumber) {
        StudentAssignment newStudentAssignment = new StudentAssignment(courseCode,studentID,topicNumber);
        studentAssignments.add(newStudentAssignment);
    }

    @Override
    public void updateSubmittedAssignmentList(List<StudentAssignment> studentAssignmentList) {
        this.studentAssignments = studentAssignmentList;
    }


    @Override
    public void updateAssignmentDatabase(List<StudentAssignment> studentAssignmentList) {

            this.studentAssignments = studentAssignmentList;

    }
}
