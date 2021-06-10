package currentUser;

import com.opencsv.exceptions.CsvValidationException;
import interfaces.LoginInterface;
import helper.TeacherHelper;

import java.io.IOException;
import java.util.Scanner;

public class CurrentTeacher extends TeacherHelper implements LoginInterface {

    private String teacherName;
    private String teacherID;
    Scanner sc= new Scanner(System.in);

    @Override
    public void welcome() {

        getCurrentTeacher();
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
//
                case 2:
                    super.modifyCourseMarks();
                    break;
//
                case 3:
                    super.getStudentReport();
                    break;
//
                case 4:
                    super.allotAttendance(teacherID);
                    break;
//
                case 5:
                    getTeacherDetails();
                    break;
//
                case 6:
                    super.addAssignment();
                    break;
//
                case 7:
                    super.markStudentAssignment(teacherID);
                case 8:
                    break;
            }
        }while(option!=8);
    }

    @Override
    public boolean isAccountValid(String id, String password) throws IOException, CsvValidationException {
        //TeacherAccounts
        return super.isAccountValid(id,password);
    }

    private void getCurrentTeacher()
    {
        String[] teacherDetails = super.getTeacher();
        teacherName = teacherDetails[0];
        teacherID = teacherDetails[1];
    }

    private void getTeacherDetails()
    {
        System.out.println("Teacher Name: "+this.teacherName+"\nTeacher ID: "+this.teacherID);
    }

}
