package helper;

import classes.*;
import databaseClass.SubmittedAssignment;
import interfaces.utilities.SubmittedAssignmentServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SubmittedAssignmentHelper extends SubmittedAssignment implements SubmittedAssignmentServices {

    private Student student;
    private Admin admin;
    private Teacher teacher;
    private List<StudentAssignment> studentAssignments;

    public SubmittedAssignmentHelper(Student student)
    {
        this.student = student;
        studentAssignments = super.getSubmittedAssignmentList();
    }

    public SubmittedAssignmentHelper(Teacher teacher)
    {
        this.teacher = teacher;
        studentAssignments = super.getSubmittedAssignmentList();
    }

    public SubmittedAssignmentHelper(Admin admin)
    {
        this.admin = admin;
        studentAssignments = super.getSubmittedAssignmentList();
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
        return super.getSubmittedAssignmentList();
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
    public void submitAssignment(String courseCode, String studentID, int topicNumber) throws IOException {
        StudentAssignment newStudentAssignment = new StudentAssignment(courseCode,studentID,topicNumber);
        studentAssignments.add(newStudentAssignment);
        updateDatabase(studentAssignments);
    }

    @Override
    public void updateAssignmentDatabase(List<StudentAssignment> studentAssignmentList) throws IOException {

        if(teacher!=null)
        {
            this.studentAssignments = super.updateSubmittedAssignment(studentAssignmentList);
        }
        else System.out.println("Invalid Teacher Operation.");
    }

    private void updateDatabase(List<StudentAssignment> studentList) throws IOException {
        this.studentAssignments = super.updateSubmittedAssignment(studentList);
    }
}
