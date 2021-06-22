import databaseClass.DatabaseHelper;
import user.ActiveUser;
import user.AdminUser;
import user.StudentUser;
import user.TeacherUser;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Cms {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        int option = 0;
        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.initiateDatabase();


        do {
            try
            {
                System.out.println("********** CMS System **********");
                System.out.println("You are a/an :\n1) Student\n2) Teacher\n3) Admin\n4) Exit");

                option = sc.nextInt();

                switch(option)
                {
                    case 1:

                        StudentUser studentUser = new StudentUser();
                        ActiveUser student = new ActiveUser(studentUser);
                        break;

                    case 2:

                        TeacherUser teacherUser = new TeacherUser();
                        ActiveUser teacher = new ActiveUser(teacherUser);
                        break;

                    case 3:
                        AdminUser adminUser = new AdminUser();
                        ActiveUser admin = new ActiveUser(adminUser);
                        break;

                }
            }
            catch(InputMismatchException e)
                    
            {
                System.out.println("Enter An appropriate Choice.");
                sc.next();
            }

        }while(option!=4);
        sc.close();

    }
}