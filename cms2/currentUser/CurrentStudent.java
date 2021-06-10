package currentUser;

import com.opencsv.exceptions.CsvValidationException;
import interfaces.LoginInterface;
import helper.StudentHelper;
import java.io.IOException;
import java.util.Scanner;

public class CurrentStudent extends StudentHelper implements LoginInterface {

    private String studentName;
    private String studentID;
    private String studentDept;
    private int age;
    private int studentYear;
    private int semester;

    Scanner sc = new Scanner(System.in);

    @Override
    public void welcome() {
        getCurrentStudent();
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
//
                case 3: //Get Assignment List
                    super.getStudentAssignments(studentID);
                    break;
//
                case 4:
                    getStudentDetails();
                    break;
//
                case 5:
                    super.getStudentEnrolledCourses(studentID);
                    break;
//
                case 6:
                    super.changeAccountPassword(studentID);
                    break;
//
                case 7:
                    break;


                default:
                    System.out.println("Enter a Valid Choice");
            }
        }while (option!=7);
    }

    @Override
    public boolean isAccountValid(String id, String password) throws IOException, CsvValidationException {
        return super.isAccountValid(id,password);
    }

    private void getCurrentStudent()
    {
        String[] studentDetails = super.getStudent();
        studentName = studentDetails[0];
        age = Integer.parseInt(studentDetails[1]);
        studentID = studentDetails[2];
        studentDept = studentDetails[3];
        studentYear = Integer.parseInt(studentDetails[4]);
        semester = Integer.parseInt(studentDetails[5]);
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
