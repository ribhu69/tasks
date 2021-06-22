package helper;

import classes.Admin;
import classes.EnrolledStudent;
import classes.Student;
import classes.Teacher;
import databaseClass.StudentAccounts;
import interfaces.services.StudentServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class StudentHelper extends StudentAccounts implements StudentServices {
    private Student student;
    private Teacher teacher;
    private Admin admin;
    private List<Student> studentList;
    Scanner sc = new Scanner(System.in);

    public StudentHelper(Student student)
    {
        this.student = student;
        studentList = super.getStudentList();
    }

    public StudentHelper(Teacher teacher)
    {
        this.teacher = teacher;
        studentList = super.getStudentList();
    }

    public StudentHelper(Admin admin)
    {
        this.admin = admin;
        studentList = super.getStudentList();
    }


    @Override
    public void getStudentDetails() {

                System.out.println("Student Name: "+student.getStudentName()+
                        "\nStudent ID: "+student.getStudentID() +
                        "\nStudent Department: "+student.getStudentDept()+
                        "\nStudent Year of Study: "+student.getStudentYear()+
                        "\nSemester: "+student.getSemester());
        }

    @Override
    public void getStudentDetails(String studentID)
    {

        for(Student i:studentList)
        {
            if(i.getStudentID().equals(studentID))
            {
                System.out.println("Student Name: "+i.getStudentName()+
                        "\nStudent ID: "+i.getStudentID() +
                        "\nStudent Department: "+i.getStudentDept()+
                        "\nStudent Year of Study: "+i.getStudentYear()+
                        "\nSemester: "+i.getSemester());
            }
        }
    }

    @Override
    public String getStudentIdAdmin() {
        if(admin!=null)
        {
            int studentNumber,flag=1;
            for(int i=0;i<studentList.size();i++)
            {
                System.out.println((i+1)+") Student ID: "+studentList.get(i).getStudentID().toUpperCase()+
                        "\nStudent Name: "+studentList.get(i).getStudentName());
                System.out.println();
            }
            System.out.println("Choose from above Registered Courses: ");

            do {
                studentNumber = sc.nextInt();
                if(studentNumber<=0 || studentNumber>studentList.size())
                {
                    System.out.println("Please Enter a valid Choice");
                }
                else flag=0;

            }while(flag!=0);

            String studentID = studentList.get(studentNumber-1).getStudentID();
            System.out.println("Chosen Course ID: "+studentID);

            return studentID;
        }

        else System.out.println("Invalid Admin Operation.");

        return null;
    }


    public void getStudentEnrolledCourses(List<EnrolledStudent> courseList) {
        int studentFound=0;
        for(EnrolledStudent i:courseList)
        {
            if(i.getStudentID().equals(student.getStudentID()))
            {
                studentFound=1;
                System.out.println("Course Name: "+i.getCourseName()+"\nCourse ID:"+i.getCourseId().toUpperCase());
                System.out.println();
            }
        }
        if(studentFound==0)
            System.out.println("No Enrolled Courses found for Student ID: "+student.getStudentID());
    }

    public String getStudentCourseID(List<EnrolledStudent> courseList,String studentID)
    {
        if(admin!=null)
        {
            int courseNumber,flag=1;

            List<EnrolledStudent> studentEnrolledCourses = new ArrayList<>();
            for(EnrolledStudent i:courseList)
            {
                if(i.getStudentID().equals(studentID))
                    studentEnrolledCourses.add(i);
            }

            for(int i=0;i<studentEnrolledCourses.size();i++)
            {
                System.out.println((i+1)+") Course ID: "+studentEnrolledCourses.get(i).getCourseId().toUpperCase()+
                        "\nCourse Name: "+studentEnrolledCourses.get(i).getCourseName());
                System.out.println();
            }
            System.out.println("Choose from above Registered Courses: ");

            do {
                courseNumber = sc.nextInt();
                if(courseNumber<=0 || courseNumber>studentEnrolledCourses.size())
                {
                    System.out.println("Please Enter a valid Choice");
                }
                else flag=0;

            }while(flag!=0);

            String studentCourseID = studentEnrolledCourses.get(courseNumber-1).getCourseId();
            System.out.println("Chosen Course ID: "+studentCourseID);
            return studentCourseID;
        }
        else
            System.out.println("Invalid Admin Operation");
        return null;
    }

    @Override
    public void changeAccountPassword(String studentID) throws IOException {
        PasswordHelper passwordHelper = new PasswordHelper();
        System.out.println("Enter Current Password");
        String currentPassword = passwordHelper.encryptPassword(sc.nextLine());

        for(Student i:studentList)
        {
            if(i.getPin().equals(currentPassword))
            {
                do {
                    System.out.println("Enter New Password");
                    String newPassword = passwordHelper.encryptPassword(sc.nextLine());
                    if (newPassword.equals(student.getPin()))
                        System.out.println("Password Cannot be the Same as Previous Password\n");
                    else {
                        System.out.println("Re-enter New Password");
                        String verifyPassword = passwordHelper.encryptPassword(sc.nextLine());

                        if (verifyPassword.equals(newPassword))
                        {
                            student.setPassword(newPassword);
                            System.out.println("Password Changed");
                            updateDatabase(studentList);
                            return;
                        }
                        else
                        {
                            System.out.println("Password does not match");
                        }

                    }
                } while (true);
            }
            else
            {
                System.out.println("Credentials do not match.");
            }
        }
    }

    @Override
    public void addStudent(Admin admin) throws IOException {
        if(admin!=null)
        {

            //name
            //dept
            //age
            //yos
            //yoa
            //
            char deptID='0';
            String yearOfAdmn="";
            System.out.println("Enter Student Name: ");
            String studentName = sc.nextLine();
            int flag=1;
            String departmentID = "";
            do {
                System.out.print("Enter Student Department: ");
                departmentID = sc.nextLine();

                if(departmentID.length()<1)
                    System.out.println("Enter Appropriate Department ID. Please Try Again.");
                else
                {
                    deptID = newStudentDept(sc.nextLine());
                    if(deptID=='x')
                        System.out.println("Enter Correct department ID");
                    else
                        flag=0;
                }

            }while (flag!=0);
            flag=1;


            System.out.println("Enter Student Age");
            int age = 0;
            do {
                
                try
                {
                    age=sc.nextInt();
                    sc.nextLine();
                    if(String.valueOf(age).length()<2 || age<18)
                        System.out.println("Enter an appropriate age value. Try Again");
                    else 
                        flag=0;
                }
                
                catch (InputMismatchException e)
                {
                    System.out.println("Age should be numeric and greater than 18.");
                    sc.next();
                }
                
            }while(flag!=0);
            flag=1;
//



            System.out.println("Enter Year of Study");

            int studyYear=0;
            do {
                try
                {
                    studyYear = sc.nextInt();
                    if(String.valueOf(studyYear).length()>1)
                        System.out.println("Year of Study should be a single digit integer. Please Try Again.");
                    else
                        flag=0;

                }
                catch (InputMismatchException e)
                {
                    System.out.println("Year of Study should be a single digit integer.");
                    sc.next();
                }

            }while (flag!=0);
            flag=1;

            System.out.println("Year of Admission: ");
            int yearOfAdmission;
            do {
                try
                {
                    yearOfAdmission = sc.nextInt();
                    if(String.valueOf(yearOfAdmission).length()==4)
                    {
                        yearOfAdmn = String.valueOf(yearOfAdmission);
                        char[] yearExtractor = yearOfAdmn.toCharArray();
                        yearOfAdmn = yearExtractor[2] +String.valueOf(yearExtractor[3]);
                        flag=0;
                    }

                    else
                        System.out.println("Please Enter Year in YYYY format.");
                }
                catch (InputMismatchException e)
                {
                    System.out.println("Please Enter integer value in YYYY format.");
                    sc.next();
                }

            }while (flag!=0);
            flag=1;


            int option;
            int sem=0;
            System.out.println("Semester of Study:\n1)Odd\n2)Even.");
            option = sc.nextInt();
            do {
                switch (option)
                {
                    case 1:
                        sem = (studyYear*2)-1;
                        flag=0;
                        break;

                    case 2:
                        sem = (studyYear*2);
                        flag=0;
                    default:
                        System.out.println("Enter Correct Value.");
                }
            }while (flag!=0);
            flag=1;
            String generatedStudentID;
            do {
                generatedStudentID = yearOfAdmn+deptID+generateStudentId();
                if(doesStudentIdExist(generatedStudentID))
                    generatedStudentID = yearOfAdmn+deptID+generateStudentId();
                else
                    flag=0;
            }while (flag!=0);

            System.out.println("Generated Student ID: "+generatedStudentID);

            sc.nextLine();
            PasswordHelper passwordHelper = new PasswordHelper();
            String password;
            do {
                System.out.println("Enter New Password: ");
                password = sc.nextLine();
                if(password.length()<3)
                    System.out.println("Password Should Have minimum length of 3");
                else
                {
                    password = passwordHelper.encryptPassword(password);
                    studentList.add(new Student(studentName,age,generatedStudentID,password,departmentID,studyYear,sem));
                    updateDatabase(studentList);
                    System.out.println("Student Added. Please Wait..");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return;
                }
            }while (true);
        }
    }

    @Override
    public void removeStudent(Admin admin) throws IOException {
        if(admin!=null)
        {
            int flag=1;
            for(int i=0;i<studentList.size();i++)
            {
                System.out.println(i+1+") "+studentList.get(i).getStudentID()+"\nStudent Name: "+studentList.get(i).getStudentName());
            }
            int studentIdNumber;
            System.out.println("Select Student ID: ");

            do {
                studentIdNumber = sc.nextInt();
                if(studentIdNumber<=0 || studentIdNumber> studentList.size())
                {
                    System.out.println("Please Enter a valid Choice");
                }
                else flag=0;

            }while(flag!=0);
            String studentID = studentList.get(studentIdNumber-1).getStudentID();
            removeStudent(studentID);
            updateDatabase(studentList);
            System.out.println("Student Removed.");
        }
    }

    @Override
    public void iterateStudentList() {
        if(admin!=null)
        {
            for(Student i:studentList)
            {
                System.out.println("Student Name: "+i.getStudentName()+
                        "\nStudent ID: "+i.getStudentID()+
                        "\nStudent Department: "+i.getStudentDept());
                System.out.println("--------------------------------------");
            }
        }
    }

    @Override
    public List<Student> getStudentByDetails() {
        if(admin!=null)
        {
            int flag=1;
            List<Student> studentByDetailList = new ArrayList<>();
            String dept;
            do {
                System.out.println("Enter Department");
                dept = sc.nextLine();
                if(dept.length()<1 || dept.length()>3)
                {
                    System.out.println("Enter Appropriate Department ID");
                }
                else flag=0;
            }while (flag!=0);
            flag=1;

            int studyYear=0;
            do {
                try
                {
                    System.out.println("Enter Year of Study");
                    studyYear = sc.nextInt();
                    if(String.valueOf(studyYear).length()>1)
                        System.out.println("Year of Study should be a single digit integer. Please Try Again.");
                    else
                        flag=0;

                }
                catch (InputMismatchException e)
                {
                    System.out.println("Year of Study should be a single digit integer.");
                    sc.next();
                }

            }while (flag!=0);
            flag=1;


            int semester=0;
            do {
                try
                {
                    System.out.println("Enter Semester of Study");
                    semester = sc.nextInt();
                    if(String.valueOf(studyYear).length()>1)
                        System.out.println("Semester of Study should be a single digit integer. Please Try Again.");
                    else
                        flag=0;

                }
                catch (InputMismatchException e)
                {
                    System.out.println("Semester of Study should be a single digit integer.");
                    sc.next();
                }

            }while (flag!=0);
            flag=1;
            for(Student i:studentList)
            {
                if(i.getStudentDept().equals(dept) && i.getStudentYear() == studyYear && i.getSemester() == semester)
                    studentByDetailList.add(i);
            }
                return studentByDetailList;
        }
        return null;
    }

    private void removeStudent(String studentID)
    {
        for(Student i:studentList)
        {
            if(i.getStudentID().equals(studentID))
            {
                studentList.remove(i);
                return;
            }
        }
    }
    private char newStudentDept(String dept)
    {
        if(dept.toUpperCase().equals("CT"))
            return  '1';
        if(dept.toUpperCase().equals("IT"))
            return  '2';
        if(dept.toUpperCase().equals("ME"))
            return  '3';
        if(dept.toUpperCase().equals("ETC"))
            return  '4';
        return 'x';
    }

    private boolean doesStudentIdExist(String generatedId)
    {
        for(Student i: studentList)
        {
            if(i.getStudentID().equals(generatedId))
                return true;
        }
        return false;
    }

    private int generateStudentId()
    {
        //min -> min range of value for adminId
        //max -> max range of value for adminId
        return ThreadLocalRandom.current().nextInt(100, 999+1);
    }


    private void updateDatabase(List<Student> studentList) throws IOException {
        this.studentList = super.updateStudentAccounts(studentList);
    }
}