package currentUser;

import com.opencsv.exceptions.CsvValidationException;
import interfaces.LoginInterface;
import helper.AdminHelper;
import java.io.IOException;
import java.util.Scanner;

public class CurrentAdmin extends AdminHelper implements LoginInterface {

    private String adminName;
    private String adminID;

    Scanner sc= new Scanner(System.in);

    @Override
    public void welcome() throws IOException, CsvValidationException {
        getCurrentAdmin();
        System.out.println("Hello "+this.adminName);
        adminMenu();
    }

    private void adminMenu() {
        int option;
        do {
            System.out.println("Choose from below options" +
                    "\n--------------------------" +
                    "\n1) Add New Course" +
                    "\n2) Modify Existing Course" +
                    "\n3) Modify Course Teacher" +
                    "\n4) Delete Course" +
                    "\n--------------------------" +
                    "\n5) Add New Student" +
                    "\n6) Remove Student" +
                    "\n7) Get List of Course" +
                    "\n8) Get List of Enrolled Students" +
                    "\n9) Logout");

            option = sc.nextInt();
            switch (option) {
                case 1: //Add New Course
                {
                    super.addCourse();
                    break;
                }
//
                case 2: //Modify Existing Course
                {
                    super.modifyExistingCourse();
                    break;
                }
                case 3: //modify course teacher
                {
                    super.modifyCourseTeacher();
                    break;
                }
                case 4: //delete a course
                    super.deleteCourse();
                    break;
//
                case 5://add Student
                    super.addStudentAccount();
                    break;
//
                case 6: //remove student
                    super.removeStudent();
                    break;
//
                case 7: //display course list
                    super.getCourseList();
                    break;
//
                case 8: //to be done
                    super.getEnrolledStudentList();
//
                case 9:
                    break;

            }

        } while (option != 9);
    }


    @Override
    public boolean isAccountValid(String id, String password) throws IOException, CsvValidationException {
        //AdminAccounts
            return super.isAccountValid(id,password);
    }

    private void getCurrentAdmin()
    {
        String[] adminDetails = super.getAdmin();
        adminName = adminDetails[0];
        adminID = adminDetails[1];
    }
}
