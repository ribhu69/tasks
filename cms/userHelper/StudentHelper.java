package userHelper;

import database.StudentAccounts;
import user.CurrentStudent;

public class StudentHelper extends StudentAccounts{

    public boolean isDetailValid(String id,String password)
    {
        return super.isDetailValid(id,password);

    }
    public CurrentStudent getStudent(String id)
    {
        return super.getStudent(id);
    }

    //admin implementations
    public void addStudentAccount(){super.addStudent();}

    //admin implementations
    public void removeStudentAccount() {super.removeStudent();}

    //admin implementations
    public void getStudentInfo(){super.getStudentInfo();}
}
