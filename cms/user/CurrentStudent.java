package user;

import utilityClasses.StudentUtility;

import java.util.Scanner;

public class CurrentStudent extends StudentUtility {
    private String studentName;
    private String studentID;
    private String studentDept;
    private int studentYear;
    private int semester;
    Scanner sc= new Scanner(System.in);

    public void setStudentID(String studentID) {this.studentID = studentID ;}
    public void setStudentName(String studentName) {this.studentName = studentName ;}
    public void setStudentDept(String studentDept) {this.studentDept=studentDept; }
    public void setStudentYear(int studentYear) { this.studentYear=studentYear; }
    public void setSemester(int semester) { this.semester=semester; }

    public void welcome()
    {
        System.out.println("Hello "+this.studentName);
        studentMenu();
    }
    private void studentMenu() {
        int option;
        do {
            System.out.println("Choose from below options" +
                    "\n1) Check Course Attendance" +
                    "\n2) Submit Assignment" +
                    "\n3) Get Assignment List" +
                    "\n4) Get Your Details"+
                    "\n5) Show Enrolled Courses"+
                    "\n6) Change Account Password"+
                    "\n7) Logout");
              option=sc.nextInt();
            switch (option)
            {
                case 1:
                    super.checkAttendance(studentID);
                    break;
                case 2: //to be done
                    super.submitAssignment(studentID);
                    break;

                case 3: //Get Assignment List
                    super.getStudentAssignments(studentID);
                    break;

                case 4:
                    getStudentDetails();
                    break;

                case 5:
                    super.getStudentEnrolledCourses(studentID);
                    break;

                case 6:
                    super.changeAccountPassword(studentID);
                    break;

                case 7:
                    break;


                default:
                    System.out.println("Enter a Valid Choice");
            }
        }while (option!=7);
    }

    private void getStudentDetails()
    {

        System.out.println("Student Name: "+this.studentName+
                "\nStudent ID: "+this.studentID +
                "\nStudent Department: "+this.studentDept+
                "\nStudent Year of Study: "+this.studentYear+
                "\nSemester: "+this.semester);
    }

}
