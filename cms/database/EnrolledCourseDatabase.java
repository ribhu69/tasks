package database;

import classes.EnrolledStudent;
import utilityClasses.EnrolledCourseUtility;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EnrolledCourseDatabase {
    private static List<EnrolledStudent> enrolledStudentsList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    DecimalFormat numberFormat = new DecimalFormat("#.00");


    public void initializeEnrolledCourseDatabase()
    {
        enrolledStudentsList.add(new EnrolledStudent("Applied Science","ge101",60,"100101",45));
        enrolledStudentsList.add(new EnrolledStudent("Applied Science","ge101",60,"100102",42));
        enrolledStudentsList.add(new EnrolledStudent("Applied Science","ge101",60,"100103",34));
        enrolledStudentsList.add(new EnrolledStudent("Applied Science","ge101",60,"100104",46));
        enrolledStudentsList.add(new EnrolledStudent("Applied Science","ge101",60,"100105",41));
        enrolledStudentsList.add(new EnrolledStudent("Applied Science","ge101",60,"100106",42));
        enrolledStudentsList.add(new EnrolledStudent("Engg Mathematics","me101",60,"100101",33));
        enrolledStudentsList.add(new EnrolledStudent("Engg Mathematics","me101",60,"100103",35));
        enrolledStudentsList.add(new EnrolledStudent("Engg Mathematics","me101",60,"100105",45));
        enrolledStudentsList.add(new EnrolledStudent("Engg Mathematics","me101",60,"100104",45));
        enrolledStudentsList.add(new EnrolledStudent("Engg Physics","ge103",60,"100105",25));
        enrolledStudentsList.add(new EnrolledStudent("Engg Physics","ge103",60,"100102",31));
        enrolledStudentsList.add(new EnrolledStudent("Engg Physics","ge103",60,"100103",36));
        enrolledStudentsList.add(new EnrolledStudent("Engg Physics","ge103",60,"100104",15));
        enrolledStudentsList.add(new EnrolledStudent("Engg Physics","ge103",60,"100107",14));
        enrolledStudentsList.add(new EnrolledStudent("Molecular Chemistry","ge102",60,"100103",45));
        enrolledStudentsList.add(new EnrolledStudent("Molecular Chemistry","ge102",60,"100103",45));
        enrolledStudentsList.add(new EnrolledStudent("Molecular Chemistry","ge102",60,"100103",45));
        enrolledStudentsList.add(new EnrolledStudent("Molecular Chemistry","ge102",60,"100103",45));
        enrolledStudentsList.add(new EnrolledStudent("Molecular Chemistry","ge102",60,"100103",45));
        enrolledStudentsList.add(new EnrolledStudent("Molecular Chemistry","ge102",60,"100103",45));
        enrolledStudentsList.add(new EnrolledStudent("Molecular Chemistry","ge102",60,"100103",45));
        enrolledStudentsList.add(new EnrolledStudent("X","sc102",60,"100101",45));
        enrolledStudentsList.add(new EnrolledStudent("X","sc102",60,"100102",45));
        enrolledStudentsList.add(new EnrolledStudent("X","sc102",60,"100103",45));
        enrolledStudentsList.add(new EnrolledStudent("X","sc102",60,"100104",45));

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

    protected void getStudentEnrolledCourseName(String studentID)
    {
        for(EnrolledStudent i: enrolledStudentsList)
        {
            if(i.getStudentID().equals(studentID))
            {
                System.out.println("Course Name: "+i.getCourseName()+"\nCourse ID:"+i.getCourseId());
                System.out.println();
            }
        }
    }




}
