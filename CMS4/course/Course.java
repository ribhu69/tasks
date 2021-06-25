package course;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Course {
    private String courseName;
    private String courseTeacher;
    private final int courseYear;
    private final int courseSemester;
    private int courseTeacherID;
    private final String courseID;
    private final List<String> courseTopics = new ArrayList<>();


    Scanner sc = new Scanner(System.in);


    public void iterateCourseTopics()
    {

        for(int i=0;i<courseTopics.size();i++)
        {
            System.out.println((i+1) + ") "+courseTopics.get(i));
        }
    }

    public int getCourseYear()
    {return this.courseYear;}

    public Course(String courseName,String courseId,String courseTeacher,int courseTeacherID, int courseYear,int courseSemester)
    {
        this.courseName=courseName;
        this.courseID = courseId;
        this.courseTeacher=courseTeacher;
        this.courseTeacherID=courseTeacherID;
        this.courseYear=courseYear;
        this.courseSemester=courseSemester;
    }

    public void modifyCourseTeacher(String courseTeacher)
    {
        this.courseTeacher=courseTeacher;
    }


    public void setCourseTeacherID(int teacherID) {this.courseTeacherID=teacherID;}
    public String getCourseName()
    {
        return this.courseName;
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseTeacher()
    {
        return this.courseTeacher;
    }

    public void setCourseName(String courseName) { this.courseName=courseName; }

    public int getCourseSemester() {
        return courseSemester;
    }

    public int getCourseTeacherID() {
        return courseTeacherID;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }
}

