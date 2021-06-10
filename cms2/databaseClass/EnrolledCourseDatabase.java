package databaseClass;

import classes.EnrolledStudent;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnrolledCourseDatabase {

    Scanner sc = new Scanner(System.in);
    private static List<EnrolledStudent> enrolledStudentsList = new ArrayList<>();
    DecimalFormat numberFormat = new DecimalFormat("#.00");
    private final String enrolledCourseDB = "D:\\Zoho\\Course Management System\\src\\database\\EnrolledCourseDatabase.csv";

    //FILE IO
    protected void initializeEnrolledCourseList() {
        String[] enrolledCourseDetails;
        List<EnrolledStudent> tempEnrolledCourseList = new ArrayList<>();
        try
        {
            CSVReader reader = new CSVReaderBuilder(new FileReader(enrolledCourseDB)).withSkipLines(1).build();
            while ((enrolledCourseDetails = reader.readNext())!=null)
            {

                EnrolledStudent enrolledStudent = new EnrolledStudent(
                        enrolledCourseDetails[0], //courseName
                        enrolledCourseDetails[1], //courseID
                        Integer.parseInt(enrolledCourseDetails[2]), //totalClasses
                        enrolledCourseDetails[3], //studentID
                        Integer.parseInt(enrolledCourseDetails[4]) //attendance
                        );

                tempEnrolledCourseList.add(enrolledStudent);
            }
            enrolledStudentsList=tempEnrolledCourseList;

        }
        catch (FileNotFoundException e) {
            System.out.printf("Course DB Does not Exist.");

        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
    }

    protected List<String> getEnrolledCourses(String studentID)
    {
        List<String> courseList = new ArrayList<>();
        for(EnrolledStudent i:enrolledStudentsList)
        {
            if(i.getStudentID().equals(studentID))
            {
                courseList.add(i.getCourseId());
            }
        }
        return courseList;
    }

    protected void getAttendance(String studentID)
    {

        for(EnrolledStudent i:enrolledStudentsList)
        {
            if(i.getStudentID().equals(studentID))
            {
                int totalClasses = i.getTotalClasses();
                int attendedClasses = i.getAttendance();
                double attendance = (attendedClasses/(float)totalClasses)*100;
                System.out.println("Subject Name: "+i.getCourseName()+"\nAttendance: "+ numberFormat.format(attendance)+"%");
                System.out.println();
            }
        }
    }

    protected void allotAttendance(String courseID)
    {
        int option;
        if(courseExists(courseID))
        {
            System.out.println("Total Students in "+courseID+": "+studentCount(courseID));
            do
            {
                System.out.println("Choose from the Following: \n1) Allot Full Attendance in Course" +
                        "\n2) Allot Attendance Iteratively"+
                        "\n3) Modify Student Attendance in Course"+
                        "\n4) Return to Previous Menu");
                option=sc.nextInt();
                switch (option)
                {
                    case 1:
                        allotFullAttendance(courseID);
                        System.out.println("Attendance Alloted to All Students");
                        System.out.println("--------------------------------------------");
                        break;

                    case 2:
                        allotIterativeAttendance(courseID);
                        break;
                    case 3:
                        modifyStudentAttendance(courseID);
                        break;

                    case 4:
                        break;

                    default:
                        System.out.println("Enter a valid Choice");

                }
            }while(option!=4);

        }
        else
        {
            System.out.println("Course Not in Database");
        }

    }

    private void modifyStudentAttendance(String courseID)
    {
        System.out.println("Enter Student ID");
        sc.nextLine();
        String studentID = sc.nextLine();

        for(EnrolledStudent i:enrolledStudentsList)
        {
            if(i.getStudentID().equals(studentID) && i.getCourseId().equals(courseID))
            {
                System.out.println("Current Attendance: "+i.getAttendance());
                System.out.println("Enter Value to Modify Attendance");
                i.setAttendance(sc.nextInt());
                System.out.println("Updated Attendance: "+i.getAttendance());
                return;
            }
        }
    }

    private int studentCount(String courseId)
    {
        int count=0;
        for(EnrolledStudent i:enrolledStudentsList)
        {
            if(i.getCourseId().equals(courseId))
                count++;
        }
        return count;
    }

    private boolean courseExists(String courseID)
    {
        for (EnrolledStudent i:enrolledStudentsList)
        {
            if(i.getCourseId().equals(courseID))
                return true;
        }
        return false;
    }

    //allots attendance to everyone
    private void allotFullAttendance(String courseID){
        for(EnrolledStudent i:enrolledStudentsList)
        {
            if(i.getCourseId().equals(courseID))
            {
                i.incrementAttendance();
            }
        }
    }

    private void allotIterativeAttendance(String courseID)
    {
        int attendanceValue=0,flag;
        for(EnrolledStudent i:enrolledStudentsList)
        {
            flag=1;
            if(i.getCourseId().equals(courseID))
            {
                do {
                    System.out.println("Current Attendance for Student: "+i.getAttendance());
                    System.out.println();
                    System.out.println("Enter Attendance for Student: "+i.getStudentID() + "\nNote: Range of Attendance (0-5)");
                    attendanceValue = sc.nextInt();
                    if(attendanceValue<=0 || attendanceValue>5)
                    {
                        System.out.println("Please Enter a valid Choice");
                    }
                    else
                    {
                        i.incrementAttendance(attendanceValue);
                        System.out.println("Updated Attendance for Student: "+i.getAttendance());
                        flag=0;
                        attendanceValue=0;
                        System.out.println("--------------------------------------------");
                    }

                }while(flag!=0);
            }
        }
        System.out.println("Attendance alloted to Students");

    }

    protected void getStudentEnrolledCourseName(String studentID)
    {
        int studentFound=0;
        for(EnrolledStudent i: enrolledStudentsList)
        {
            if(i.getStudentID().equals(studentID))
            {
                studentFound=1;
                System.out.println("Course Name: "+i.getCourseName()+"\nCourse ID:"+i.getCourseId());
                System.out.println();
            }
        }
        if(studentFound==0)
            System.out.println("No Enrolled Courses found for Student ID: "+studentID);
    }

    //FILE IO
    protected void updateEnrolledCourseList()
    {

        try {

            CSVWriter writer = new CSVWriter(new FileWriter(enrolledCourseDB));

            // adding header to csv
            String[] header = { "Course Name","Course Code","Total Classes","studentID","attendance" };
            writer.writeNext(header);

            for(int i=0;i<enrolledStudentsList.size();i++)
            {
                String[] courseListItem = new String[5];
                courseListItem[0]= enrolledStudentsList.get(i).getCourseName(); //courseName
                courseListItem[1]= enrolledStudentsList.get(i).getCourseId(); //courseID
                courseListItem[2]= String.valueOf(enrolledStudentsList.get(i).getTotalClasses()); //totalClasses
                courseListItem[3]= enrolledStudentsList.get(i).getStudentID(); //studentID
                courseListItem[4]= String.valueOf(enrolledStudentsList.get(i).getAttendance()); //attendance

                writer.writeNext(courseListItem);
            }
            writer.close();
            initializeEnrolledCourseList();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected void showEnrolledStudents(String courseID)
    {
        int flag=1;

        for(EnrolledStudent i: enrolledStudentsList)
        {
            if(i.getCourseId().equals(courseID))
            {
                flag=0;
                System.out.println("Student ID: "+i.getStudentID());
            }
        }
        if(flag==1)
        {
            System.out.println("No Such Course Exists");
        }
    }
}
