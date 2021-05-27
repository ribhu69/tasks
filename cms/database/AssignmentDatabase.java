package database;

import classes.Assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AssignmentDatabase {
    Scanner sc = new Scanner(System.in);

    SubmittedAssignment submittedAssignment = new SubmittedAssignment();


    private static List<Assignment> assignmentList = new ArrayList<>();

    public void initiateAssignmentList()
    {
        assignmentList.add(new Assignment("Applied Science title","ge101",1,1));
        assignmentList.add(new Assignment("Applied Science title","ge101",2,2));
        assignmentList.add(new Assignment("Maths title","me101",3,3));
        assignmentList.add(new Assignment("Mechanics title","cv108",2,2));
        assignmentList.add(new Assignment("Mechanics title","cv108",1,1));
        assignmentList.add(new Assignment("Mechanics title","cv108",3,3));
        assignmentList.add(new Assignment("x title","sc102",1,1));
        assignmentList.add(new Assignment("x title","sc102",2,2));
        assignmentList.add(new Assignment("Physics title","ge103",1,1));
        assignmentList.add(new Assignment("Physics title","ge103",3,3));
        assignmentList.add(new Assignment("Physics title","ge103",2,2));
    }

    protected List<Assignment> getAssignmentList()
    {
        return assignmentList;
    }
    protected void addAssignment()
    {
        System.out.println("Enter Course Code");
        String courseCode = sc.nextLine();
        System.out.println("Enter Course Topic Number");
        int courseTopic = sc.nextInt();
        System.out.println("Enter Assignment Title");
        sc.nextLine();
        String assignmentTitle  = sc.nextLine();
        System.out.println("Enter Assignment Number");
        int assignmentNumber = sc.nextInt();

        assignmentList.add(new Assignment(assignmentTitle,courseCode,courseTopic,assignmentNumber));
        System.out.println("New Assignment Added");
    }

    protected void getAssignmentList(String courseCode)
    {
        for(Assignment i:assignmentList)
        {
            if(i.getCourseCode().equals(courseCode))
            {
                System.out.println("Assignment Title: "+i.getAssignmentTitle()+
                        "\nCourse Topic Number:"+i.getCourseTopicNumber()+
                        "\nAssignment Number: "+i.getAssignmentNumber());
//                if(submittedAssignment.isAssignmentSubmitted(i.getCourseCode(),i.get))
                System.out.println();
            }
        }
    }

    protected void getStudentAssignmentList(String courseCode, String studentID)
    {
        for(Assignment i:assignmentList)
        {
            if(i.getCourseCode().equals(courseCode))
            {
                System.out.println("Assignment Title: "+i.getAssignmentTitle()+
                        "\nCourse Topic Number:"+i.getCourseTopicNumber()+
                        "\nAssignment Number: "+i.getAssignmentNumber());
                if(submittedAssignment.isAssignmentSubmitted(i.getCourseCode(),studentID,i.getCourseTopicNumber()))
                    System.out.println("Assignment Status: Submitted");
                else
                    System.out.println("Assignment Status: Not Submitted");
                System.out.println();
            }
        }
    }
}
