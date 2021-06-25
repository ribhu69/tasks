package assignment;

import teacher.Teacher;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class AssignmentHelper  implements AssignmentServices {
    private Teacher teacher;
    private SubmittedAssignmentServices submittedAssignmentServices;
    private List<Assignment> assignmentList;
    private List<StudentAssignment> studentAssignments;

    Scanner sc = new Scanner(System.in);

    public AssignmentHelper(List<Assignment> assignmentList,List<StudentAssignment> studentAssignments)
    {
        this.assignmentList = assignmentList;
        this.studentAssignments = studentAssignments;
        submittedAssignmentServices = new SubmittedAssignmentHelper(studentAssignments);
    }


    public void addAssignment(List<String> teacherCourseList) {
        int flag=1, courseNumber;
        System.out.println("** Your Assigned Courses **");
        for(int i = 0; i< teacherCourseList.size(); i++)
        {
            System.out.println((i+1)+") "+ teacherCourseList.get(i).toUpperCase());
        }
        System.out.println("Select Course: ");

        do {
            courseNumber = sc.nextInt();
            if(courseNumber<=0 || courseNumber> teacherCourseList.size())
            {
                System.out.println("Please Enter a valid Choice");
            }
            else flag=0;

        }while(flag!=0);

        int assignmentExists=0;
        String courseCode = teacherCourseList.get(courseNumber-1);
        System.out.println("Selected Course: "+courseCode.toUpperCase());
        System.out.println("Enter Course Topic Number");
        int courseTopic = sc.nextInt();
        System.out.println("Enter Assignment Number");
        int assignmentNumber = sc.nextInt();
        for(Assignment i:assignmentList)
        {
            if(i.getCourseId().equals(courseCode) &&
                    i.getCourseTopicNumber() == courseTopic &&
                    i.getAssignmentNumber() == assignmentNumber)
            {
                System.out.println("Assignment Already Exists for\n Course Code: "+i.getCourseId()+
                        "\nCourse Topic Number"+i.getCourseTopicNumber()+
                        "\nAssignment Number:"+i.getAssignmentNumber());
                assignmentExists=1;
                break;
            }
        }
        if(assignmentExists==0)
        {
            System.out.println("Enter Assignment Title");
            sc.nextLine();
            String assignmentTitle  = sc.nextLine();


            assignmentList.add(new Assignment(assignmentTitle,courseCode,courseTopic,assignmentNumber));
            System.out.println("New Assignment Added");
        }
        else
        {
            System.out.println("Redirecting to Previous Menu");
        }
    }

    @Override
    public void showCourseAssignments(List<String> courseList) {
        for (String s : courseList) {
            showListedCourseAssignments(s);
        }
    }

    @Override
    public void submitAssignment(List<String> courseList,String studentID) throws IOException {
        int courseNumber, flag=1, topicNumber;

        System.out.println("** Choose Course Code from the following courses **");
        for(int i = 0; i< courseList.size(); i++)
        {
            System.out.println((i+1)+") "+ courseList.get(i).toUpperCase());
        }

        do {
            courseNumber = sc.nextInt();
            if(courseNumber<=0 || courseNumber> courseList.size())
            {
                System.out.println("Please Enter a valid Choice");
            }
            else flag=0;

        }
        while(flag!=0);
        flag=1;
        //gets courseID based on user choice from indexed options
        String courseID = courseList.get(courseNumber-1);

        //gets assignment count for the selected course
        int assignmentCount = getAssignmentCount(courseID);
//
//        studentAssignments = submittedAssignmentServices.getSubmittedAssignmentList(studentID);
        int submittedAssignmentCount = submittedAssignmentCount(courseID,studentID);

        //gets studentAssignments which are submitted already


        if(assignmentCount>1)
        {

            System.out.println("** Your Assignments Summary **\n");

            //shows assignments and their submission status
            getStudentAssignmentList(courseID,studentID);

            if(assignmentCount==submittedAssignmentCount)
            {
                System.out.println("Choose from the following:\n1) Return to previous Menu");
                int option;
                do{
                    option = sc.nextInt();
                    if(option==1)
                        break;
                    else
                        System.out.println("Choose Correct Option");
                }while (option!=1);

            }
            else
            {
                System.out.println("Choose from the following:\n1) Submit Assignment\n2) Return to previous Menu");

                int option = sc.nextInt();
                switch (option)
                {
                    case 1:

                        do {
                            System.out.println("Enter Course Assignment Number to Submit");
                            topicNumber = sc.nextInt();

                            if(isStudentAssignmentSubmitted(courseID,studentID,topicNumber))
                            {
                                System.out.println("Assignment Already Submitted\n--------------------------");
                            }

                            //
                            else if(!containsAssignment(courseID,topicNumber))
                                System.out.println("Enter Correct Assignment Topic Number\n--------------------------");
                            else
                            {
                                submittedAssignmentServices.submitAssignment(courseID,studentID,topicNumber);
                                studentAssignments = submittedAssignmentServices.getSubmittedAssignmentList(studentID);
                                System.out.println("Assignment Submitted\n--------------------------");

                                flag=0;
                            }
                        }
                        while (flag!=0);

                    case 2:
                        break;
                }
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


    @Override
    public List<StudentAssignment> markAssignment(List<String> teacherCourseLists,
                                                  List<StudentAssignment> studentAssignments) {

        int flag=1, courseNumber;
        System.out.println("** Your Assigned Courses **");
        for(int i = 0; i< teacherCourseLists.size(); i++)
        {
            System.out.println((i+1)+") "+ teacherCourseLists.get(i).toUpperCase());
        }
        System.out.println("Select Course: ");

        do {
            courseNumber = sc.nextInt();
            if(courseNumber<=0 || courseNumber> teacherCourseLists.size())
            {
                System.out.println("Please Enter a valid Choice");
            }
            else flag=0;

        }while(flag!=0);

        String courseID = teacherCourseLists.get(courseNumber-1);

        int assignmentExistStatus=0;
        int submittedAssignmentCount=0, markedAssignmentCount=0;

        for(StudentAssignment i:studentAssignments)
        {
            int marks, check=1;
            if(i.getCourseCode().equals(courseID))
            {
                assignmentExistStatus=1;
                submittedAssignmentCount+=1;
                if(i.getAssignmentMarks()<0)
                {
                    System.out.println("Student ID: "+i.getStudentID()+
                            "\nCourse Code: "+i.getCourseCode().toUpperCase()+
                            "\nAssignment Topic Number: "+i.getTopicNumber()+
                            "\n-----------------------------------------------");
                    System.out.println("Enter Marks in Range 0-10");

                    do {

                        marks = sc.nextInt();
                        if(marks>10)
                        {
                            System.out.println("Incorrect Range of Marks.\nPlease Try Try Again");
                        }
                        else
                            check=0;
                    }while(check!=0);
                    i.setAssignmentMarks(marks);
                    System.out.println("Updated Marks: "+i.getAssignmentMarks());
                    System.out.println();
                }
                else
                    markedAssignmentCount+=1;
            }
        }

        if(assignmentExistStatus==0)
            System.out.println("No submissions found Course Code: "+courseID.toUpperCase());

        if((submittedAssignmentCount>0 && markedAssignmentCount>0) && submittedAssignmentCount==markedAssignmentCount)
            System.out.println("All Submissions for Course Code: "+courseID.toUpperCase()+" are marked.");

        return studentAssignments;
    }

    @Override
    public void updateAssignmentList(List<Assignment> assignmentList) {
        this.assignmentList = assignmentList;
    }

    @Override
    public List<Assignment> getAssignmentsList() {
        return assignmentList;
    }

    @Override
    public List<StudentAssignment> getSubmittedAssignmentList() {
        return studentAssignments;
    }

    private int getAssignmentCount(String courseId) {
        int count=0;
        for(Assignment i:assignmentList)
        {
            if(i.getCourseId().equals(courseId))
                count++;
        }
        return count;
    }

    private void getStudentAssignmentList(String courseCode, String studentID)
    {

        for(Assignment i:assignmentList)
        {
            if(i.getCourseId().equals(courseCode))
            {
                System.out.println("Assignment Title: "+i.getAssignmentTitle()+
                        "\nCourse Topic Number:"+i.getCourseTopicNumber()+
                        "\nAssignment Number: "+i.getAssignmentNumber());
                int courseTopicNumber = i.getCourseTopicNumber();
                if(isStudentAssignmentSubmitted(courseCode,studentID,courseTopicNumber))

                    System.out.println("Assignment Status: Submitted");
                else
                    System.out.println("Assignment Status: Not Submitted");
                System.out.println();
            }
        }
    }

    private void showListedCourseAssignments(String courseID)
    {
        for(Assignment i: assignmentList)
        {
            if(i.getCourseId().equals(courseID))
            {
                System.out.println("Assignment Title: "+i.getAssignmentTitle()+
                        "\nAssignment Number: "+i.getAssignmentNumber()+
                        "\nAssignment Course Topic Number: "+i.getCourseTopicNumber());
                System.out.println("-----------------------------------------------");
            }
        }
    }

    private boolean isStudentAssignmentSubmitted(String courseCode ,String studentID, int topicNumber)
    {
        for(StudentAssignment i:studentAssignments)
        {
            if(i.getCourseCode().equals(courseCode) && i.getStudentID().equals(studentID) && i.getTopicNumber()==topicNumber)
                return true;
        }
        return false;
    }

    private int submittedAssignmentCount(String courseID,String studentID)
    {
        int count=0;
        for(StudentAssignment i:studentAssignments)
        {
            if(i.getCourseCode().equals(courseID) && i.getStudentID().equals(studentID))
                count+=1;
        }
        return count;
    }

    private boolean containsAssignment(String courseId, int courseTopicNumber)
    {
        for(Assignment i:assignmentList)
        {
            if(i.getCourseId().equals(courseId) && i.getCourseTopicNumber()==courseTopicNumber)
                return true;
        }
        return false;
    }
}