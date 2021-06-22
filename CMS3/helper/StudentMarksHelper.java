package helper;

import classes.*;
import databaseClass.StudentMarksDatabase;
import interfaces.utilities.MarksServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentMarksHelper extends StudentMarksDatabase implements MarksServices {

    private Admin admin;
    private Teacher teacher;
    private List<CourseMarks> studentMarksList;
    Scanner sc = new Scanner(System.in);
    public StudentMarksHelper(Teacher teacher)
    {
        this.teacher = teacher;
        studentMarksList = super.getMarksDB();
    }

    public StudentMarksHelper(Admin admin)
    {
        this.admin = admin;
        studentMarksList = super.getMarksDB();
    }

    @Override
    public void modifyCourseMarks(List<String> teacherCourseList) throws IOException {

        int courseNumber,flag=1;
        String studentID="";
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

        String courseCode = teacherCourseList.get(courseNumber-1);

        flag=1;

        List<String> studentsList = new ArrayList<>();
        int i=0, databaseEmpty=1;
        for(int j=0;j<studentMarksList.size();j++)
        {
            if(studentMarksList.get(j).getCourseID().equals(courseCode) &&
                    studentMarksList.get(j).getCourseTeacherID().equals(String.valueOf(teacher.getTeacherId())))
            {
                databaseEmpty=0;
                System.out.println(i+1+") "+studentMarksList.get(j).getStudentID());
                i=i+1;
                studentsList.add(studentMarksList.get(j).getStudentID());
            }
        }

        if(databaseEmpty==1)
            System.out.println("No Entries Found for mentioned Course ID");

        else
        {
            System.out.println("Select Student ID from above");
            int studentIdNumber=0;
            do {
                studentIdNumber = sc.nextInt();
                if(studentIdNumber<=0 || studentIdNumber>studentMarksList.size())
                {
                    System.out.println("Please Enter a valid Choice");
                }
                else flag=0;

            }while(flag!=0);

            studentID = studentsList.get(studentIdNumber-1);

            for(CourseMarks k: studentMarksList)
            {
                if(k.getCourseTeacherID().equals(String.valueOf(teacher.getTeacherId()))
                        && k.getCourseID().equals(courseCode)
                        && k.getStudentID().equals(studentID))
                {
                    System.out.println("Current Marks for Student ID: "+studentID+
                            "\nCourse ID: "+k.getCourseID()+
                            "\nCourse Marks: "+k.getExamScoredMarks());
                    System.out.println("Enter Modified Marks");
                    int studentMark = sc.nextInt();
                    k.setScoredExamMarks(studentMark);
                    System.out.println("Updated Marks for Student ID: "+k.getExamScoredMarks());
                }
            }
            updateDatabase(studentMarksList);
        }
    }

    @Override
    public void generateStudentReport(int assignmentMarks, String studentID, String courseID) {
        for(CourseMarks i: studentMarksList)
        {
            if(i.getStudentID().equals(studentID) && i.getCourseID().equals(courseID))
            {
                System.out.println("Marks Report for Student ID: "+studentID);
                System.out.println("Course ID: "+courseID.toUpperCase());
                System.out.println("Marks in Exam: "+i.getExamScoredMarks()+"/"+i.getExamTotalMarks());
                System.out.println("Cumulative Assignment Marks: "+assignmentMarks+"/60");
                System.out.println();
            }
        }
    }

    @Override
    public void updateCourseTeacher(String courseID, int teacherID) throws IOException {
        if(admin!=null)
        {
            for(CourseMarks i:studentMarksList)
            {
                if(i.getCourseID().equals(courseID))
                {
                    i.setCourseTeacherID(String.valueOf(teacherID));
                }
            }
            updateDatabase(studentMarksList);
        }
        else System.out.println("Invalid Admin Operation.");
    }


    private void updateDatabase(List<CourseMarks> studentMarks) throws IOException {
        this.studentMarksList = super.updateMarksDatabase(studentMarks);
    }
}
