package helper;

import classes.Admin;
import classes.EnrolledStudent;
import classes.Student;
import classes.Teacher;
import databaseClass.EnrolledCourseDatabase;
import interfaces.utilities.EnrolledCourseServices;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class EnrolledCourseHelper extends EnrolledCourseDatabase implements EnrolledCourseServices
{
    private List<EnrolledStudent> enrolledStudents;
    private Student student;
    private Teacher teacher;
    private Admin admin;
    DecimalFormat numberFormat = new DecimalFormat("#.00");
    Scanner sc =new Scanner(System.in);
    public EnrolledCourseHelper(Student student)
    {
        this.student = student;
        enrolledStudents = super.getEnrolledStudentsList();

    }
    public EnrolledCourseHelper(Teacher teacher)
    {
        this.teacher = teacher;
        enrolledStudents = super.getEnrolledStudentsList();
    }

    public EnrolledCourseHelper(Admin admin)
    {
        this.admin = admin;
        enrolledStudents = super.getEnrolledStudentsList();
    }
    @Override
    public List<String> getStudentEnrolledCourses(){

        List<String> studentEnrolledCourseList = new ArrayList<>();
        int studentFound=0;
        for(EnrolledStudent i: enrolledStudents)
        {
            if(i.getStudentID().equals(student.getStudentID()))
            {
                studentFound=1;
               studentEnrolledCourseList.add(i.getCourseId());
            }
        }
        if(studentFound==0)
        {
            System.out.println("No Enrolled Courses found for Student ID: "+student.getStudentID());
            return null;
        }
        else
            return studentEnrolledCourseList;
    }

    @Override
    public List<String> getStudentEnrolledCourses(String studentID){

        List<String> studentEnrolledCourseList = new ArrayList<>();
        int studentFound=0;
        for(EnrolledStudent i: enrolledStudents)
        {
            if(i.getStudentID().equals(studentID))
            {
                studentFound=1;
                studentEnrolledCourseList.add(i.getCourseId());
            }
        }
        if(studentFound==0)
        {
            System.out.println("No Enrolled Courses found for Student ID: "+student.getStudentID());
            return null;
        }
        else
            return studentEnrolledCourseList;
    }

    @Override
    public void showEnrolledCourses() {
        for(EnrolledStudent i: enrolledStudents)
        {
            if(i.getStudentID().equals(student.getStudentID()))
            {
                System.out.println("Course Name: "+i.getCourseName()+"\nCourse ID:"+i.getCourseId().toUpperCase());
                System.out.println();
            }
        }
    }

    @Override
    public List<String> getCourseList() {
        List<String> enrolledCourseList = new ArrayList<>();
        for(EnrolledStudent i:enrolledStudents)
        {
            if(i.getStudentID().equals(student.getStudentID()))
                enrolledCourseList.add(i.getCourseId());
        }
        return enrolledCourseList;
    }

    @Override
    public void getStudentAttendance() {

        for(EnrolledStudent i:enrolledStudents)
        {
            if(i.getStudentID().equals(student.getStudentID()))
            {
                int totalClasses = i.getTotalClasses();
                int attendedClasses = i.getAttendance();
                double attendance = (attendedClasses/(float)totalClasses)*100;
                System.out.println("Course Name: "+i.getCourseName()+
                        "\nCourse ID: "+i.getCourseId().toUpperCase()+
                        "\nCourse Attendance: "+ numberFormat.format(attendance)+"%");
                System.out.println();
            }
        }
    }

    @Override
    public void allotAttendance(List<String> courseList) throws IOException {

        int flag=1, courseNumber;
        System.out.println("** Your Assigned Courses **");
        for(int i = 0; i< courseList.size(); i++)
        {
            System.out.println((i+1)+") "+ courseList.get(i));
        }
        System.out.println("Select Course: ");

        do {
            courseNumber = sc.nextInt();
            if(courseNumber<=0 || courseNumber> courseList.size())
            {
                System.out.println("Please Enter a valid Choice");
            }
            else flag=0;

        }while(flag!=0);

        String courseID = courseList.get(courseNumber-1);

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

    @Override
    public void removeStudent(String studentID, String courseID) throws IOException {
        if(admin!=null)
        {
            for(EnrolledStudent i:enrolledStudents)
            {
                if(i.getStudentID().equals(studentID) && i.getCourseId().equals(courseID))

                {
                    enrolledStudents.remove(i);
                    enrolledStudents = super.updateEnrolledCourseList(enrolledStudents);
                    System.out.println("Student Removed Successfully.");
                    return;
                }
            }
        }
        else System.out.println("Invalid Admin Operation.");

    }

    @Override
    public List<EnrolledStudent> getEnrolledCourses() {
        return super.getEnrolledStudentsList();

    }
    ///not complete
    @Override
    public void addStudentToCourse(String courseName, String courseID, int totalClasses,List<Student> studentList) throws IOException {
       if(admin!=null)
       {
           for(Student i:studentList)
           {
               enrolledStudents.add(new EnrolledStudent(courseName,courseID,totalClasses,i.getStudentID(),0));
           }
           enrolledStudents = super.updateEnrolledCourseList(enrolledStudents);
       }
       else
           System.out.println("Invalid Admin Operation");
    }

    private void allotIterativeAttendance(String courseID) throws IOException {
        int attendanceValue=0,flag;
        for(EnrolledStudent i:enrolledStudents)
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

        enrolledStudents = super.updateEnrolledCourseList(enrolledStudents);
        System.out.println("Attendance alloted to Students");

    }

    private void allotFullAttendance(String courseID) throws IOException {
        for(EnrolledStudent i:enrolledStudents)
        {
            if(i.getCourseId().equals(courseID))
            {
                i.incrementAttendance();
            }
        }
        enrolledStudents = super.updateEnrolledCourseList(enrolledStudents);
    }

    private void modifyStudentAttendance(String courseID) throws IOException {
        System.out.println("Enter Student ID");
        sc.nextLine();
        String studentID = sc.nextLine();

        for(EnrolledStudent i:enrolledStudents)
        {
            if(i.getStudentID().equals(studentID) && i.getCourseId().equals(courseID))
            {
                System.out.println("Current Attendance: "+i.getAttendance());
                System.out.println("Enter Value to Modify Attendance");
                i.setAttendance(sc.nextInt());
                System.out.println("Updated Attendance: "+i.getAttendance());
                enrolledStudents = super.updateEnrolledCourseList(enrolledStudents);
                return;
            }
        }

    }

    private boolean courseExists(String courseID)
    {
        for (EnrolledStudent i:enrolledStudents)
        {
            if(i.getCourseId().equals(courseID))
                return true;
        }
        return false;
    }

    private int studentCount(String courseId)
    {
        int count=0;
        for(EnrolledStudent i:enrolledStudents)
        {
            if(i.getCourseId().equals(courseId))
                count++;
        }
        return count;
    }

}