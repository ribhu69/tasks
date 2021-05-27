package user;


import utilityClasses.AdminUtility;
import utilityClasses.EnrolledCourseUtility;

import java.util.Scanner;

public class CurrentAdmin extends AdminUtility {
    private String adminName;
    private String adminID;
    Scanner sc= new Scanner(System.in);

    public void setAdminID(String adminID)
    {
        this.adminID= adminID;
    }
    public void setAdminName(String adminName)
    {
        this.adminName=adminName;
    }


    public void welcome()
    {
        System.out.println("Hello "+this.adminName);
        adminMenu();
    }

    private void adminMenu()
    {
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
                    "\n8) Get List of Enrolled Students"+
                    "\n9) Exit");

            option = sc.nextInt();
            switch(option)
            {
                case 1: //Add New Course
                {
                    super.addCourse();
                    break;
                }

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
                    super.removeCourse();
                    break;

                case 5://add Student
                    super.addStudentAccount();
                    break;

                case 6: //remove student
                    super.removeStudent();
                    break;

                case 7: //display course list
                    super.getCourseList();
                    break;

                case 8: //to be done
                    super.getEnrolledStudentList();

                case 9:
                    break;

            }

        }while(option!=9);
    }

}
