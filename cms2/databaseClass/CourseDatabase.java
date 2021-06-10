package databaseClass;

import classes.Course;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseDatabase {

    Scanner sc = new Scanner(System.in);
    private static List<Course> courseList = new ArrayList<>();
    private final String courseDB = "D:\\Zoho\\Course Management System\\src\\database\\CourseDatabase.csv";

    //FILE IO
    protected void initiateCourseList() {
        String[] courseDetails;
        List<Course> tempCourseList = new ArrayList<>();
        try
        {
            CSVReader reader = new CSVReaderBuilder(new FileReader(courseDB)).withSkipLines(1).build();
            while ((courseDetails = reader.readNext())!=null)
            {

                Course course = new Course(
                        courseDetails[0], //courseName
                        courseDetails[1], //courseID
                        courseDetails[2], //teacherName
                        Integer.parseInt(courseDetails[3]), //teacherID
                        Integer.parseInt(courseDetails[4]), //teacherYear
                        Integer.parseInt(courseDetails[5])); //teacherSemester
                tempCourseList.add(course);
            }
            courseList=tempCourseList;

        }
        catch (FileNotFoundException e) {
            System.out.printf("Course DB Does not Exist.");

        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
    }

    protected void showCourses()
    {
        for(Course i:courseList)
        {
            System.out.println("Course Name: "+i.getCourseName()+
                    "\nCourse Year: "+i.getCourseYear()+
                    "\nCourse Code: "+i.getCourseID()+
                    "\nCourse Teacher Name: "+i.getCourseTeacher()+
                    "\nCourse Teacher ID: "+i.getCourseTeacherID());
            System.out.println();
        }
    }

    protected String addCourse()
    {
        System.out.println(courseList.size());
        System.out.println("Enter Course ID");
        String courseID = sc.nextLine();
        for(Course i: courseList)
        {
            if(courseID.length()>1 && i.getCourseID().equals(courseID))
            {
                System.out.println("Course already exists");
                return null;
            }
        }
        System.out.println("Enter Course Name");
        String courseName= sc.nextLine();
        System.out.println("Enter Course Teacher:");
        String courseTeacher = sc.nextLine();
        System.out.println("Enter Course Teacher ID");
        int coursTeacherID = sc.nextInt();
        System.out.println("Enter Year");
        sc.nextLine();
        int courseYear = Integer.parseInt(sc.nextLine());
        System.out.println("Enter Semester");
        int courseSemester = sc.nextInt();

        courseList.add(new Course(courseName,courseID,courseTeacher,coursTeacherID,courseYear,courseSemester));
        System.out.println(courseList.size());
        return courseName;
    }

    protected String deleteCourse()
    {
        System.out.println("Enter Course ID");
        String courseID = sc.nextLine();
        int flag=1;
        for(Course i: courseList)
        {
            if(i.getCourseID().equals(courseID))
            {
                courseList.remove(i);
                flag=0;
                System.out.println("Course Removed.");
                return courseID;
            }
        }
        if(flag==1)
            System.out.println("No such course exists");

        return null;
    }

    //FILE IO
    protected void updateCourseList()
    {

        try {

            CSVWriter writer = new CSVWriter(new FileWriter(courseDB));

            // adding header to csv
            String[] header = { "Course Name","Course Code","Course Teacher","teacherID","year","semester" };
            writer.writeNext(header);

            for(int i=0;i<courseList.size();i++)
            {
                String[] courseListItem = new String[6];
                courseListItem[0]= courseList.get(i).getCourseName();
                courseListItem[1]= courseList.get(i).getCourseID();
                courseListItem[2]= courseList.get(i).getCourseTeacher();
                courseListItem[3]= String.valueOf(courseList.get(i).getCourseTeacherID());
                courseListItem[4]= String.valueOf(courseList.get(i).getCourseYear());
                courseListItem[5]= String.valueOf(courseList.get(i).getCourseSemester());

                writer.writeNext(courseListItem);
            }
            writer.close();
            initiateCourseList();

    } catch (IOException e) {
            e.printStackTrace();
        }

    }

    protected List<String> getTeacherCourseList(int teacherID)
    {
        List<String> teacherCourseList = new ArrayList<>();
        for(Course i:courseList)
        {
            if(i.getCourseTeacherID()==teacherID)
            {
                teacherCourseList.add(i.getCourseID());
            }
        }

        return teacherCourseList;
    }

    protected String changeCourseTitle()
    {
        System.out.println("Enter Existing Course ID");
        String courseID = sc.nextLine();
        for(Course i: courseList)
        {
            if(i.getCourseID().equals(courseID))
            {
                System.out.println("Course Exists.\nCurrent Course Name: "+i.getCourseName()+"\nEnter New Name of Course");
                String newCourseName = sc.nextLine();
                if(i.getCourseName().equals(newCourseName))
                {
                    System.out.println("Course Name is the same as previous name");
                }
                else
                {
                    i.setCourseName(newCourseName);
                    System.out.println("Course Name Updated.\nUpdated Course Name: "+i.getCourseName());
                    return newCourseName;
                }
            }
        }
        return null;
    }

    protected String getCurrentCourseID(String newCourseName)
    {
        for(Course i: courseList)
        {
            if(i.getCourseName().equals(newCourseName))
            {
                return i.getCourseID();
            }
        }
        return null;
    }

    protected void modifyCourseTeacher()
    {
        int courseFound=0;
        System.out.println("Enter Course ID");
        String courseId = sc.nextLine();
        for(Course i: courseList)
        {
            if(i.getCourseID().equals(courseId))
            {
                courseFound=1;
                System.out.println("Current Teacher: "+ i.getCourseTeacher());
                System.out.println("Enter New Name");
                i.modifyCourseTeacher(sc.nextLine());
                System.out.println("Enter Teacher ID");
                i.setCourseTeacherID(sc.nextInt());
                System.out.println("Course Teacher Updated");
                break;
            }
        }
        if(courseFound==0)
            System.out.println("Course Code "+courseId+" does not exist.");
    }

}
