package database;

import classes.Course;
import classes.CourseMarks;
import classes.EnrolledStudent;

import javax.sound.midi.Soundbank;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MarksDatabase {
    private static List<CourseMarks> courseMarksList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    DecimalFormat numberFormat = new DecimalFormat("#.00");

    public void initializeMarksDatabase()
    {
        courseMarksList.add(new CourseMarks("Applied Science","ge101","Vivek Singla","100101",30,35));
        courseMarksList.add(new CourseMarks("Applied Science","ge101","Vivek Singla","100103",25,22));
        courseMarksList.add(new CourseMarks("Applied Science","ge101","Vivek Singla","100105",39,18));
        courseMarksList.add(new CourseMarks("Applied Science","ge101","Vivek Singla","100107",50,28));
        courseMarksList.add(new CourseMarks("Applied Science","ge101","Vivek Singla","100104",30,69));

        courseMarksList.add(new CourseMarks("Engg Mathematics","me101","R.Madhavan","100102",45,39));
        courseMarksList.add(new CourseMarks("Engg Mathematics","me101","R.Madhavan","100104",28,30));
        courseMarksList.add(new CourseMarks("Engg Mathematics","me101","R.Madhavan","100106",35,29));
        courseMarksList.add(new CourseMarks("Engg Mathematics","me101","R.Madhavan","100107",30,35));
        courseMarksList.add(new CourseMarks("Engg Mathematics","me101","R.Madhavan","100103",28,37));

        courseMarksList.add(new CourseMarks("Engg Physics","ge103","G.Suri","100103",50,18));
        courseMarksList.add(new CourseMarks("Engg Physics","ge103","G.Suri","100101",40,22));
        courseMarksList.add(new CourseMarks("Engg Physics","ge103","G.Suri","100102",30,34));
        courseMarksList.add(new CourseMarks("Engg Physics","ge103","G.Suri","100106",20,20));
        courseMarksList.add(new CourseMarks("Engg Physics","ge103","G.Suri","100107",10,18));
        courseMarksList.add(new CourseMarks("Engg Physics","ge103","G.Suri","100105",35,30));

        courseMarksList.add(new CourseMarks("Engg Mechanics","cv108","N.Wajgi","100102",45,39));
        courseMarksList.add(new CourseMarks("Engg Mechanics","cv108","N.Wajgi","100103",38,30));
        courseMarksList.add(new CourseMarks("Engg Mechanics","cv108","N.Wajgi","100105",27,28));
        courseMarksList.add(new CourseMarks("Engg Mechanics","cv108","N.Wajgi","100104",36,22));
        courseMarksList.add(new CourseMarks("Engg Mechanics","cv108","N.Wajgi","100107",49,23));
        courseMarksList.add(new CourseMarks("Engg Mechanics","cv108","N.Wajgi","100106",45,34));

        courseMarksList.add(new CourseMarks("x","sc102","S.Raman","100101",40,37));
        courseMarksList.add(new CourseMarks("x","sc102","S.Raman","100102",39,32));
        courseMarksList.add(new CourseMarks("x","sc102","S.Raman","100103",50,26));
        courseMarksList.add(new CourseMarks("x","sc102","S.Raman","100106",28,29));
        courseMarksList.add(new CourseMarks("x","sc102","S.Raman","100107",33,38));
        courseMarksList.add(new CourseMarks("x","sc102","S.Raman","100104",45,30));

    }

    private double calculatePercentage(int scoredMarks, int totalMarks)
    {
        int courseScore = scoredMarks;
        int courseTotal = totalMarks;
        double score = (courseScore/ (float) courseTotal)*100;
        return score;
    }

    private double cumulativePercentage(int scoredAssignmentMarks, int totalAssignmentMarks, int scoredExamMarks, int totalExamMarks)
    {
        int cumulativeScored = scoredAssignmentMarks+scoredExamMarks;
        int cumulativeTotal = totalAssignmentMarks+totalExamMarks;
        double cumulativePercentage = (cumulativeScored/(float)cumulativeTotal)*100;
        return cumulativePercentage;
    }

    protected void getStudentMarksReport()
    {
        System.out.println("Enter Student ID");
        String studentID = sc.nextLine();
        System.out.println("*** Student Marks Report ***");
        System.out.println();
        for(CourseMarks i: courseMarksList)
        {
            if(i.getStudentID().equals(studentID))
            {
                System.out.println("Course Name: "+i.getCourseName()+
                        "\nSubject Score: ("+i.getExamScoredMarks() +"/" + i.getExamTotalMarks() +")"+
                        "\nCumulative Assignment Score: (" +i.getScoredAssignmentMarks()+"/"+i.getTotalAssignmentMarks()+")" +
                        "\nCumulative Percentage: "+
                        numberFormat.format(cumulativePercentage(i.getScoredAssignmentMarks(),i.getTotalAssignmentMarks(),
                                i.getExamScoredMarks(),i.getExamTotalMarks()))+"%");
                System.out.println("-------------------------------------------");
            }

        }
    }

    protected void modifyCourseMarks()
    {
        System.out.println("Enter Course ID");
        String courseID = sc.nextLine();
        System.out.println("Enter Student ID");
        String studentID = sc.nextLine();
        for(CourseMarks i: courseMarksList)
        {
            if(i.getCourseID().equals(courseID) && i.getStudentID().equals(studentID))
            {
                System.out.println("Student Course Name: "+i.getCourseName()+"\nMarks in Course Exam: "+i.getExamScoredMarks());
                System.out.println("Enter new Marks");
                int marks = sc.nextInt();
                i.setScoredExamMarks(marks);
                System.out.println("Updated Marks in Course Exam: "+i.getExamScoredMarks());
            }
        }
    }

    protected void allotAssignmentMarks(String courseID, String studentID){
        for(CourseMarks i: courseMarksList)
        {
            int marks, flag=1;
            if(i.getCourseID().equals(courseID) && i.getStudentID().equals(studentID))
            {
                System.out.println("Enter Marks for Student: "+i.getStudentID()+" Out of 10");
                do {

                    marks = sc.nextInt();
                    if(marks>10)
                    {
                        System.out.println("Incorrect Range of Marks.\nPlease Try Try Again");
                    }
                    else
                        flag=0;
                }while(flag!=0);
                i.setScoredAssignmentMarks(marks);
                System.out.println("Updated Marks: "+i.getScoredAssignmentMarks());
                System.out.println();
            }
        }
    }
}
