package user;


import utilityClasses.TeacherUtility;

import java.util.Scanner;

public class CurrentTeacher extends TeacherUtility {
    private String teacherName;
    private String teacherID;
    Scanner sc= new Scanner(System.in);
//    private List<String> courseTeacherList = new ArrayList<>();


    public void setTeacherID(String teacherID)
    {
        this.teacherID= teacherID;
    }
    public void setTeacherName(String teacherName)
    {
        this.teacherName=teacherName;
    }

    public void welcome()
    {
        System.out.println("Hello "+this.teacherName);
        teacherMenu();
    }

    private void teacherMenu()
    {
        int option;
        do {
            System.out.println("Choose from below options" +
                    "\n1) Get Student Details" +
                    "\n2) Modify Student Marks for a Course" +
                    "\n3) Generate Marks report of Student" +
                    "\n4) Allot Attendance" +
                    "\n5) Get Your Details"+
                    "\n6) Add New Assignment "+
                    "\n7) Mark Student Assignment"+
                    "\n8) Logout.");

            option = sc.nextInt();
            switch (option)
            {
                case 1:
                    super.getStudentInfo();
                    break;

                case 2:

                    //not yet implemented
                    super.modifyCourseMarks();
//                enrolledCourseUtility.modifyStudentMarks();
                    break;

                case 3:
                    super.getStudentReport();
                    break;

                case 4:
                    super.allotAttendance(teacherID);
                    break;

                case 5:
                    getTeacherDetails();
                    break;

                case 6:
                    super.addAssignment();
                    break;

                case 7:
                    super.markStudentAssignment(teacherID);
                case 8:
                    break;

                default:
                    System.out.println("Enter A Valid Choice");
            }
        }while(option!=8);
    }

    private void getTeacherDetails()
    {
        System.out.println("Teacher Name: "+this.teacherName+"\nTeacher ID: "+this.teacherID);
    }


}
