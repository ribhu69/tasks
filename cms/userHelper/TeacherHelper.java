package userHelper;


import database.TeacherAccounts;
import user.CurrentTeacher;


public class TeacherHelper extends TeacherAccounts {
    public CurrentTeacher getTeacher(String id)
    {
        return super.getTeacher(id);
    }

}
