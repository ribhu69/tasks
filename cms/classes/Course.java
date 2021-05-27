package classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Course {
    private String courseName;
    private String courseTeacher;
    private int courseYear;
    private int courseSemester;
    private int courseTeacherID;
    private String courseID;
    private List<String> courseTopics = new ArrayList<>();


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
        this.courseTeacher=courseTeacher;
        this.courseYear=courseYear;
        this.courseTeacherID=courseTeacherID;
        this.courseID = courseId;
        this.courseSemester=courseSemester;
    }

    public void modifyCourseTeacher(String courseTeacher)
    {
        this.courseTeacher=courseTeacher;
    }

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

    public void modifyCourseTopic() {
        System.out.println("Select Course to modify");
        int courseIndex = sc.nextInt();
        System.out.println("Enter new name");
        String newCourseName = sc.nextLine();
        if(((courseIndex-1)<=courseTopics.size()) && courseIndex>=1)
        {
            //replacing the list item
            courseTopics.set(courseIndex-1, newCourseName);
            System.out.println("Course Modified.");
        }
        else
        {
            System.out.println("Invalid Choice Selected");
        }
    }

    public void addCourseTopic()
    {
        System.out.println("Enter Topic");
        String courseTopicName = sc.nextLine();
        if(courseTopics.contains(courseTopicName))
        {
            System.out.println("Topic already Exists");
        }
        else
        {
            courseTopics.add(courseTopicName);
            System.out.println("Course Topic Added Successfully.");
        }
    }

    public void deleteCourseTopic()
    {
        System.out.println("Enter Topic Number to be deleted");
        int topicNumber = sc.nextInt();
        if((topicNumber-1)<courseTopics.size())
        {
            courseTopics.remove(topicNumber);
            System.out.println("Topic removed");
        }
        else
            System.out.println("Invalid Choice");
    }
}

