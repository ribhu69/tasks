package database;

import classes.Student;
import user.CurrentStudent;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class StudentAccounts {
    Scanner sc=new Scanner(System.in);
    private static List<Student> studentList = new ArrayList<>();


    public void initiateStudentAccounts()
    {
        studentList.add(new Student("Manoj Prasad",22, "100101","111","CT",3,5));
        studentList.add(new Student("Vijay Devganj",23, "100102","222","IT",3,5));
        studentList.add(new Student("Harish Bajaj",24, "100103","333","ME",3,6));
        studentList.add(new Student("Kartar Singh",21, "100104","444","IT",4,7));
        studentList.add(new Student("Pramod Shende",23, "100105","555","CT",4,8));
        studentList.add(new Student("Arjun Mishra",25, "100106","666","IT",3,5));
        studentList.add(new Student("Arjun Kuman",23, "100107","777","IT",4,8));
    }



    protected boolean isDetailValid(String studentID, String password)
    {
        for(Student i: studentList)
        {
            if(i.getStudentID().equals(studentID) && i.getPin().equals(password))
            {
//                System.out.println("yo true");
                return true;
            }
        }
        return false;
    }

    protected CurrentStudent getStudent(String studentID)
    {
        CurrentStudent student = new CurrentStudent() ;
        for(Student i: studentList)
        {
            if(i.getStudentID().equals(studentID))

            {
                student.setStudentID(i.getStudentID());
                student.setStudentName(i.getStudentName());
                student.setStudentDept(i.getStudentDept());
                student.setStudentYear(i.getStudentYear());
                student.setSemester(i.getSemester());
            }
        }
        return student;
    }

    protected void addStudent()
    {
        System.out.println("Enter Student Name: ");
//        sc.nextLine();
        String studentName = sc.nextLine();
        System.out.println("Enter Student Age");
        int age=sc.nextInt();
        System.out.println("Enter Student ID: ");
        sc.nextLine();
        String studentID=sc.nextLine();
        System.out.println("Enter new Password");
//        sc.nextLine();
        String password = sc.nextLine();
        System.out.println("Enter Student Department: ");
//        sc.nextLine();
        String studentDept=sc.nextLine();
        System.out.println("Year of Study: ");
        int year=sc.nextInt();
        System.out.println("Semester of Study: ");
        int sem=sc.nextInt();
        studentList.add(new Student(studentName,age,studentID,password,studentDept,year,sem));
        System.out.println("Student Added. Please Wait..");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void removeStudent()
    {
        System.out.println("Enter Student ID");
        String studentID = sc.nextLine();
        for(Student i: studentList)
        {
            if(i.getStudentID().equals(studentID))
            {

                System.out.println("Student Name: "+i.getStudentName()+"");
                studentList.remove(i);
                System.out.println("Student Details Removed. Please Wait..");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
            System.out.println("Student Name does not Exists");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    protected void getStudentInfo()
    {
        System.out.println("Enter Student ID");
        String studentId = sc.nextLine();
        for(Student i: studentList)
        {
            if(i.getStudentID().equals(studentId))

            {
                System.out.println("Student Name: "+i.getStudentName()+
                        "\nStudent ID: "+i.getStudentID() +
                        "\nStudent Department: "+i.getStudentDept()+
                        "\nStudent Year of Study: "+i.getStudentYear()+
                        "\nSemester: "+i.getSemester());
                return;
            }
        }
            System.out.println("Student Name does not Exists");
        }

    protected void changeAccountPassword(String studentID)
    {
        System.out.println("Enter Current Password");
        String currentPassword = sc.nextLine();

        for(Student i:studentList)
        {
            if(i.getStudentID().equals(studentID) && i.getPin().equals(currentPassword))
            {

                do
                {
                    System.out.println("Enter New Password");
                    String newPassword = sc.nextLine();
                    if(newPassword.equals(i.getPin()))
                        System.out.println("Password Cannot be the Same as Previous Password\n");
                    else
                    {
                        System.out.println("Re-enter New Password");
                        String verifyPassword = sc.nextLine();

                        if(verifyPassword.equals(newPassword))
                        {
                            i.setPassword(newPassword);
                            System.out.println("Password Changed");
                            return;
                        }
                        else
                        {
                            System.out.println("Password does not match");
                        }

                    }
                } while(true);

            }
        }
 }
}
