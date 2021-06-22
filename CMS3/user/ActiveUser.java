package user;

import classes.Admin;
import classes.Student;
import classes.Teacher;
import databaseClass.DatabaseInitiator;
import interfaces.AdminInterface;
import interfaces.StudentInterface;
import interfaces.TeacherInterface;
import interfaces.UserInterface;

import java.io.IOException;

public class ActiveUser implements UserInterface {

    private StudentInterface student;
    private TeacherInterface teacher;
    private AdminInterface admin;
    Student currentStudent = null;
    Teacher currentTeacher = null;
    Admin currentAdmin = null;
    DatabaseInitiator databaseInitiator = new DatabaseInitiator();


    //ActiveUser ------> Student User Object in ActiveUser.
    public ActiveUser(StudentInterface student) throws IOException {
        this.student = student;
        currentStudent = studentAccountValid();
        if(currentStudent!=null)
        {

            databaseInitiator.initiateDatabase();
            invokeStudent(currentStudent);
        }
        else
        {
            System.out.println("Invalid Credentials");
        }
    }

    /*
    studentAccountValid() ------> Checks if student Account is valid.
    If so, then returns a Student Object. It is declared in UserInterface.
    */

    @Override
    public Student studentAccountValid() throws IOException {
        return student.isStudentAccountValid();
    }

    //Gets invoked if studentAccountValid() returns a non-null Student Object
    private void invokeStudent(Student existingStudent) throws IOException {
        student.welcome(existingStudent);
    }


    //Teacher User Object in ActiveUser
    public ActiveUser(TeacherInterface teacher) throws IOException {
        this.teacher = teacher;
        currentTeacher=teacherAccountValid();
        if(currentTeacher!=null)
        {
            databaseInitiator.initiateDatabase();
            invokeTeacher(currentTeacher);
        }
        else
        {
            System.out.println("Invalid Credentials");
        }


    }

    private void invokeTeacher(Teacher existingTeacher) throws IOException {
        teacher.welcome(existingTeacher);
    }

    @Override
    public Teacher teacherAccountValid() throws IOException {
        return teacher.isTeacherAccountValid();
    }


    //Admin User Object in ActiveUser
    public ActiveUser(AdminInterface admin) throws IOException {
        this.admin = admin;
        currentAdmin=adminAccountValid();
        if(currentAdmin!=null)
        {
            databaseInitiator.initiateDatabase();
            invokeAdmin(currentAdmin);
        }
        else
        {
            System.out.println("Invalid Credentials");
        }

    }

    private void invokeAdmin(Admin existingAdmin) throws IOException {
        admin.welcome(existingAdmin);
    }


    @Override
    public Admin adminAccountValid() throws IOException {
        return admin.isAdminAccountValid();
    }

}
