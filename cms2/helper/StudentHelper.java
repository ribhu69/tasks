package helper;

import databaseClass.StudentAccounts;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class StudentHelper extends StudentAccounts {

    Scanner sc= new Scanner(System.in);
    EnrolledCourseHelper enrolledCourseHelper = new EnrolledCourseHelper();
    AssignmentHelper assignmentHelper = new AssignmentHelper();
    SubmittedAssignmentHelper submittedAssignmentHelper = new SubmittedAssignmentHelper();

    protected void addStudent(){super.addStudent();}

    public void initiateStudentDatabase(){super.initiateStudentList();}

    public void updateDatabase() {super.updateStudentList();}

    public void removeStudent(){super.removeStudent();}

    public void getStudentInfo(){super.getStudentInfo();}

    protected void checkAttendance(String studentID)
    {
        enrolledCourseHelper.checkAttendance(studentID);
    }

    protected void submitAssignment(String studentID)
    {
        int courseNumber, flag=1;
        List<String> enrolledCourseList = enrolledCourseHelper.getEnrolledCourseList(studentID);
        System.out.println("** Enter Course Code from the following courses **");
//        sc.nextLine();
        for(int i=0;i<enrolledCourseList.size();i++)
        {
            System.out.println((i+1)+") "+enrolledCourseList.get(i).toUpperCase());
        }

        do {
            courseNumber = sc.nextInt();
            if(courseNumber<=0 || courseNumber>enrolledCourseList.size())
            {
                System.out.println("Please Enter a valid Choice");
            }
            else flag=0;

        }while(flag!=0);
        String courseID = enrolledCourseList.get(courseNumber-1);

        int assignmentCount = assignmentHelper.getAssignmentCount(courseID);
//        System.out.println(assignmentCount);

        if(assignmentCount>1)
        {
            System.out.println("** Your Assignments Summary **\n");
            assignmentHelper.getStudentAssignmentList(courseID,studentID);

            System.out.println("Choose from the following:\n1) Submit Assignment\n2) Return to previous Menu");

            int option = sc.nextInt();
            switch (option)
            {
                case 1:
                    System.out.println("Enter Course Assignment Number to Submit");
                    do {

                    }
                    while (flag!=0);
                    int topicNumber = sc.nextInt();
                    submittedAssignmentHelper.submitStudentAssignment(courseID,studentID,topicNumber);
                    break;

                case 2:
                    break;
            }
        }

        else
        {
            System.out.println("No Assignment Exists for the Course");
        }

        System.out.println("Please Wait...");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    protected void getStudentAssignments(String studentID)
    {
        List<String> enrolledCourseList = enrolledCourseHelper.getEnrolledCourseList(studentID);
        if(enrolledCourseList.size()<1)
            System.out.println("Not Enrolled in Any Course");
        else
        {
            for (String s : enrolledCourseList)
                assignmentHelper.getAssignmentList(s);
        }
    }

    protected void getStudentEnrolledCourses(String studentID)
    {
        enrolledCourseHelper.getStudentEnrolledCourseList(studentID);
    }
}
