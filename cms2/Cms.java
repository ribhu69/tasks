import classes.DatabaseHelper;
import com.opencsv.exceptions.CsvValidationException;
import currentUser.CurrentAdmin;
import currentUser.CurrentStudent;
import currentUser.CurrentTeacher;

import java.io.IOException;
import java.util.Scanner;

public class Cms {
    public static void main(String[] args) throws IOException, CsvValidationException {

        Scanner sc = new Scanner(System.in);
        int option;
        String id;
        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.initiateDatabase();

        do {
            System.out.println("********** CMS System **********");
            System.out.println("You are a/an :\n1) Student\n2) Teacher\n3) Admin\n4) Exit");

            option = sc.nextInt();

            switch(option)
            {
                case 1:

                    System.out.println("Enter Student ID");
                    sc.nextLine();
                    id = sc.nextLine();
                    System.out.println("Enter password");
                    CurrentStudent currentStudent = new CurrentStudent();
                    if(currentStudent.isAccountValid(id,sc.nextLine()))
                    {
                        currentStudent.welcome();
                        databaseHelper.updateDatabase();
                    }
                    else
                        System.out.println("Invalid Credentials");
                    break;

                case 2:

                    System.out.println("Enter Teacher ID");
                    sc.nextLine();
                    id = sc.nextLine();
                    System.out.println("Enter password");
                    CurrentTeacher currentTeacher = new CurrentTeacher();
                    if(currentTeacher.isAccountValid(id,sc.nextLine()))
                    {
                        currentTeacher.welcome();
                        databaseHelper.updateDatabase();
                    }
                    else
                        System.out.println("Invalid Credentials");
                    break;


                case 3:

                    System.out.println("Enter Admin ID");
                    sc.nextLine();
                    id = sc.nextLine();
                    System.out.println("Enter Password");
                    CurrentAdmin currentAdmin = new CurrentAdmin();
                    if(currentAdmin.isAccountValid(id,sc.nextLine()))
                    {
                        currentAdmin.welcome();
                        databaseHelper.updateDatabase();
                    }

                    else
                        System.out.println("Invalid Credentials");
                    break;

                case 4:
                    break;

                default:
                    System.out.println("Enter Valid Choice");
            }

        }while(option!=4);

    }
}
