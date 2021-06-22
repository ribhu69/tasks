package helper;


import classes.*;
import databaseClass.TeacherAccounts;
import interfaces.services.TeacherServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class TeacherHelper extends TeacherAccounts implements TeacherServices{
    private Teacher teacher;
    private Admin admin;
    Scanner sc = new Scanner(System.in);
    private List<Teacher> teacherList;

    public TeacherHelper(Teacher teacher)
    {
        if(super.isTeacherValid(teacher))
        {
            this.teacher = teacher;
            teacherList = super.getTeacherList();
        }

        this.teacher = null;

    }
    
    public TeacherHelper(Admin admin)
    {
        this.admin= admin;
        teacherList = super.getTeacherList();
    }

    @Override
    public void getTeacherDetails() {
        if(teacher!=null)
        {
            System.out.println("Teacher Name: "+teacher.getTeacherName()+"\nTeacher ID: "+teacher.getTeacherId());
        }
        else
            System.out.println("Invalid Teacher Operation.");
    }

    @Override
    public Teacher getExistingTeacher() {
        if(admin!=null)
        {
            int teacherNumber,flag=1;
            for(int i=0;i<teacherList.size();i++)
            {
                System.out.println((i+1)+") Teacher ID: "+teacherList.get(i).getTeacherName()+
                        "\nCourse Name: "+teacherList.get(i).getTeacherId());
                System.out.println();
            }
            System.out.println("Choose from above Registered Teachers: ");

            do {
                teacherNumber = sc.nextInt();
                if(teacherNumber<=0 ||teacherNumber>teacherList.size())
                {
                    System.out.println("Please Enter a valid Choice");
                }
                else flag=0;

            }while(flag!=0);

            int teacherID = teacherList.get(teacherNumber-1).getTeacherId();
            System.out.println("Chosen Course ID: "+teacherID);

            for(Teacher i:teacherList)
            {
                if(i.getTeacherId() == teacherID)
                    return i;
            }

        }
        else
            System.out.println("Invalid Admin Operation");
        return null;
    }

    @Override
    public void addTeacher() throws IOException {
        if (admin != null) {
            int generatedTeacherId = -1;
            int existingIdCount = 0;
            int flag=1;
            String teacherName;
            do {
                System.out.println("Enter Full Teacher Name");
                teacherName = sc.nextLine();

                if(teacherName.length()<5)
                    System.out.println("Teacher Name should be of minimum length 5.");
                else
                    flag=0;

            }while (flag!=0);

                flag=1;
            do {
                generatedTeacherId = generateTeacherId();

                if(existingIdCount == teacherList.size())
                    flag=0;

                if(doesAdminIdExist(generatedTeacherId))
                {
                    generatedTeacherId = generateTeacherId();
                    existingIdCount+=1;
                }
                else
                {
                    flag=0;
                }
            }while (flag!=0);
            flag=1;

            if(generatedTeacherId==-1)
            {
                System.out.println("Teacher ID Range is Full");
                existingIdCount=0;
            }

            else
            {
                System.out.println("Generated Teacher ID: "+generatedTeacherId);
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
                        teacherList.add(new Teacher(teacherName,generatedTeacherId,password));
                        updateDatabase(teacherList);
                        System.out.println("New Teacher Added");
                        System.out.println("Teacher Details:\n"+"Teacher Name: "+teacherName+
                                "\nTeacher ID:"+generatedTeacherId);
                        return;
                    }
                }while (true);
            }
        } else System.out.println("Invalid Admin Operation.");
    }

    @Override
    public Teacher getCourseTeacherAdmin()
    {
        if(admin!=null)
        {
            int teacherNumber,flag=1;
            for(int i=0;i<teacherList.size();i++)
            {
                System.out.println((i+1)+") Teacher ID: "+teacherList.get(i).getTeacherName()+
                        "\nTeacher Name: "+teacherList.get(i).getTeacherId());
                System.out.println();
            }
            System.out.println("Choose from above Registered Teachers to be assigned: ");

            do {
                teacherNumber = sc.nextInt();
                if(teacherNumber<=0 || teacherNumber>teacherList.size())
                {
                    System.out.println("Please Enter a valid Choice");
                }
                else flag=0;

            }while(flag!=0);

            int teacherID = teacherList.get(teacherNumber-1).getTeacherId();
            System.out.println("Chosen Teacher ID: "+teacherID);

            for(Teacher i:teacherList)
            {
                if(i.getTeacherId()==teacherID)
                    return i;
            }
        }
        else
            System.out.println("Invalid Admin Operation");
        return null;
    }


    @Override
    public String getStudentID(List<String> teacherCourseLists, List<EnrolledStudent> enrolledStudentsList)
    {
        String studentID;
        int studentIdNumber=0;
        int flag=1, courseNumber;
        List<String> studentList = new ArrayList<>();
        System.out.println("** Your Assigned Courses **");
        for(int i=0;i<teacherCourseLists.size();i++)
        {
            System.out.println((i+1)+") "+teacherCourseLists.get(i).toUpperCase());
        }
        System.out.println("Select Course: ");

        do {
            courseNumber = sc.nextInt();
            if(courseNumber<=0 || courseNumber>teacherCourseLists.size())
            {
                System.out.println("Please Enter a valid Choice");
            }
            else flag=0;

        }while(flag!=0);
        flag=1;
        String courseID = teacherCourseLists.get(courseNumber-1);

        int courseStudentCount=0;

        for(EnrolledStudent i:enrolledStudentsList)
        {
            if(i.getCourseId().equals(courseID))
                courseStudentCount+=1;
        }

        if(courseStudentCount>0)
        {
//found here
            for(int j=0;j<enrolledStudentsList.size();j++)
            {
                if(enrolledStudentsList.get(j).getCourseId().equals(courseID))
                {
                    studentList.add(enrolledStudentsList.get(j).getStudentID());
                }
            }

            System.out.println("Select Student ID ");

            for(int j=0;j<studentList.size();j++)
            {
                System.out.println(j+1+") "+studentList.get(j));
            }


            do {
                studentIdNumber = sc.nextInt();
                if(studentIdNumber<=0 || studentIdNumber>studentList.size())
                {
                    System.out.println("Please Enter a valid Choice");
                }
                else flag=0;

            }while(flag!=0);

            studentID = studentList.get(studentIdNumber-1);
            System.out.println(studentID);

            return studentID;
        }
        else
            System.out.println("No Students Enrolled for the selected course.");
        return null;
    }


    @Override
    public String getStudentID(String courseID,List<EnrolledStudent> enrolledStudentsList)
    {
        String studentID;
        int studentIdNumber=0;
        int flag=1;
        int courseStudentCount=0;

        List<String> studentList = new ArrayList<>();
        for(EnrolledStudent i:enrolledStudentsList)
        {
            if(i.getCourseId().equals(courseID))
            {
                courseStudentCount+=1;
                studentList.add(i.getStudentID());
            }
        }

        if(courseStudentCount>0)
        {

            System.out.println("Select Student ID ");

            for(int j=0;j<studentList.size();j++)
            {
                System.out.println(j+1+") "+studentList.get(j));
            }

            do {
                studentIdNumber = sc.nextInt();
                if(studentIdNumber<=0 || studentIdNumber  > enrolledStudentsList.size())
                {
                    System.out.println("Please Enter a valid Choice");
                }
                else flag=0;

            }while(flag!=0);

            studentID = studentList.get(studentIdNumber-1);
            System.out.println(studentID);

            return studentID;
        }
        else
            System.out.println("No Students Enrolled for the selected course.");
        return null;
    }

    @Override
    public void changeAccountPassword(String teacherID) throws IOException {
        PasswordHelper passwordHelper = new PasswordHelper();
        System.out.println("Enter Current Password");
        String currentPassword = passwordHelper.encryptPassword(sc.nextLine());

        for(Teacher i:teacherList)
        {
            if(i.getTeacherPin().equals(currentPassword))
            {
                do {
                    System.out.println("Enter New Password");
                    String newPassword = passwordHelper.encryptPassword(sc.nextLine());
                    if (newPassword.equals(teacher.getTeacherPin()))
                        System.out.println("Password Cannot be the Same as Previous Password\n");
                    else {
                        System.out.println("Re-enter New Password");
                        String verifyPassword = passwordHelper.encryptPassword(sc.nextLine());

                        if (verifyPassword.equals(newPassword))
                        {
                            teacher.setTeacherPin(newPassword);
                            System.out.println("Password Changed");
                            updateDatabase(teacherList);
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


    private boolean doesAdminIdExist(int generatedId)
    {
        for(Teacher i: teacherList)
        {
            if(i.getTeacherId()== generatedId)
                return true;
        }
        return false;
    }

    private int generateTeacherId()
    {
        //min -> min range of value for adminId
        //max -> max range of value for adminId
        return ThreadLocalRandom.current().nextInt(1000, 4999 + 1);
    }
    
    private void updateDatabase(List<Teacher> teacherList) throws IOException {
        this.teacherList = super.updateTeacherAccounts(teacherList);
    }
}