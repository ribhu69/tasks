package classes;

import java.util.Scanner;

public class CourseMarks {
    private String courseName;
    private String courseFaculty;
    private String courseID;
    private int courseTotalMarks;
    private int scoredMarks;
    private int scoredExamMarks;
    private int scoredAssignmentMarks;
    private int examTotalMarks = 40;
    private int totalAssignmentMarks = 60;
    private String enrolledStudentID;

    Scanner sc= new Scanner(System.in);
    public CourseMarks(String courseName, String courseID, String courseFaculty, String enrolledStudentID, int scoredAssignmentMarks, int scoredExamMarks)
    {
        this.courseName=courseName;
        this.courseID = courseID;
        this.courseFaculty=courseFaculty;
        this.enrolledStudentID = enrolledStudentID;
        this.scoredAssignmentMarks = scoredAssignmentMarks;
        this.scoredExamMarks = scoredExamMarks;
    }

//    public int getCourseTotalMarks() {return this.courseTotalMarks;}

    public int getExamTotalMarks() { return this.examTotalMarks;}

    public int getExamScoredMarks() { return this.scoredExamMarks;}

    public void setScoredExamMarks(int scoredExamMarks) {
        this.scoredExamMarks = scoredExamMarks;
    }

    public int getCourseScoredMarks() {return this.scoredMarks;}

    public String getCourseID() {return this.courseID;}

    public String getCourseName() {return this.courseName;}

    public String getStudentID() {return this.enrolledStudentID;}

    public void setStudentMarks(int scoredMarks) {this.scoredMarks = scoredMarks;}

    public void setScoredAssignmentMarks(int marks)
    {
        this.scoredAssignmentMarks += marks;
    }

    public String getCourseFaculty() {
        return courseFaculty;
    }

    public int getScoredAssignmentMarks(){
        return this.scoredAssignmentMarks;
    }

    public int getTotalAssignmentMarks() {return this.totalAssignmentMarks;}

//    protected void modifyStudentMarks()
//    {
//        System.out.println("Enter Student ID");
//        sc.nextLine();
//        String studentId = sc.nextLine();
//        System.out.println("Enter course marks to modify");
//
//    }
}
