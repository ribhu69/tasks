package database;

import classes.Teacher;
import user.CurrentTeacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherAccounts {
    private static List<Teacher> teacherList = new ArrayList<>();


    public void initiateTeacherAccounts()
    {
        teacherList.add(new Teacher("R.Madhavan",1000,"pass1"));
        teacherList.add(new Teacher("Vivek Singla",2000,"pass2"));
        teacherList.add(new Teacher("A.B.Bhake",3000,"pass3"));
        teacherList.add(new Teacher("N.Wajgi",4000,"pass4"));
        teacherList.add(new Teacher("G.Suri",5000,"pass5"));
        teacherList.add(new Teacher("S.Raman",6000,"pass5"));

    }

    public boolean isDetailValid(String teacherID, String password)
    {
        for(Teacher i: teacherList)
        {
            if(String.valueOf(i.getTeacherId()).equals(teacherID) && i.getTeacherPin().equals(password))
                return true;
        }
        return false;
    }

    protected CurrentTeacher getTeacher(String teacherID)
    {
        CurrentTeacher teacher = new CurrentTeacher();
        for(Teacher i: teacherList)
        {
            if(String.valueOf(i.getTeacherId()).equals(teacherID))

            {
                teacher.setTeacherID(String.valueOf(i.getTeacherId()));
                teacher.setTeacherName(i.getTeacherName());
            }
        }
        return teacher;
    }



}
