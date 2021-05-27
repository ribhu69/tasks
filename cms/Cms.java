import classes.DatabaseHelper;
import user.CurrentAdmin;
import user.CurrentStudent;
import user.CurrentTeacher;
import userHelper.AdminHelper;
import userHelper.StudentHelper;
import userHelper.TeacherHelper;

import java.util.Scanner;

public class Cms {
    public static void main(String[] args) {
        DatabaseHelper databaseHelper = new DatabaseHelper();
        databaseHelper.initiateDatabase();

        Scanner sc = new Scanner(System.in);
        int option;
        String id;

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
                    StudentHelper studentHelper = new StudentHelper();
                    if(studentHelper.isDetailValid(id,sc.nextLine()))
                    {
                        CurrentStudent student = studentHelper.getStudent(id);
                        student.welcome();
                    }

                    else
                        System.out.println("Invalid Credentials");
                    break;

                case 2:

                    System.out.println("Enter Teacher ID");
                    sc.nextLine();
                    id = sc.nextLine();
                    System.out.println("Enter password");
                    TeacherHelper teacherHelper = new TeacherHelper();
                    if(teacherHelper.isDetailValid(id,sc.nextLine()))
                    {
                        CurrentTeacher teacher = teacherHelper.getTeacher(id);
                        teacher.welcome();
                    }
                    else
                        System.out.println("Invalid Credentials");
                    break;

                case 3:

                    System.out.println("Enter AdminID");
                    sc.nextLine();
                    id = sc.nextLine();
                    System.out.println("Enter password");
                    AdminHelper adminHelper = new AdminHelper();
                    if(adminHelper.isDetailValid(id,sc.nextLine()))
                    {
                        CurrentAdmin admin = adminHelper.getAdmin(id);
                        admin.welcome();
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


