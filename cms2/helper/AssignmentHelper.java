package helper;

import classes.Assignment;
import classes.Course;
import databaseClass.AssignmentDatabase;

import java.util.List;
import java.util.Scanner;

public class AssignmentHelper extends AssignmentDatabase {

    Scanner sc = new Scanner(System.in);
    CourseHelper courseHelper = new CourseHelper();
    SubmittedAssignmentHelper submittedAssignmentHelper = new SubmittedAssignmentHelper();
    MarksUtility marksUtility = new MarksUtility();

    public void initiateAssignmentDatabase() {super.initiateAssignmentList();}

    public void updateDatabase(){super.updateAssignmentList();}

    public int getAssignmentCount(String courseId) { return super.getAssignmentCount(courseId);}

    protected void markStudentAssignment(String teacherID){

        int flag=1, courseNumber;
        List<Assignment> assignmentList = super.getAssignmentList();
        List<String> teacherCourseList = courseHelper.getTeacherCourseList(Integer.parseInt(teacherID));
        System.out.println("** Your Assigned Courses **");
        for(int i=0;i<teacherCourseList.size();i++)
        {
            System.out.println((i+1)+") "+teacherCourseList.get(i));
        }
        System.out.println("Select Course: ");

        do {
            courseNumber = sc.nextInt();
            if(courseNumber<=0 || courseNumber>teacherCourseList.size())
            {
                System.out.println("Please Enter a valid Choice");
            }
            else flag=0;

        }while(flag!=0);

        String courseID = teacherCourseList.get(courseNumber-1);

        int assignmentExistStatus=1;
        for(Assignment i: assignmentList)
        {


            if(i.getCourseCode().equals(courseID))
            {
                assignmentExistStatus=0;
                System.out.println("Assignment Title: "+i.getAssignmentTitle()+"     Assignment Topic Number: "+i.getCourseTopicNumber());
                System.out.println("Enter Topic Number");
                int topicNumber = sc.nextInt();
                List<String> studentIDList = submittedAssignmentHelper.getStudentIdList(courseID,topicNumber);
//                System.out.println(studentIDList);

                if(studentIDList.size()<1)
                {
                    System.out.println("Please Check Topic Number & Try Again.");
                    return;
                }
                else
                {
                    for(String studentId: studentIDList)
                    {
                        marksUtility.allotAssignmentMarks(courseID,studentId);
                    }
                }
            }
        }
        if(assignmentExistStatus==1)
        {
            System.out.println("No Assignment Exists for The Subject");
        }

    }

    protected void addAssignment()
    {
        super.addAssignment();
    }

    protected void getStudentAssignmentList(String courseCode,String studentID)
    {
        super.getStudentAssignmentList(courseCode,studentID);
    }

    public void getAssignmentList(String courseID)
    {
        super.getAssignmentList(courseID);
    }

}
