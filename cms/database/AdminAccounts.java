package database;

import classes.Admin;
import user.CurrentAdmin;

import java.util.ArrayList;
import java.util.List;

public class AdminAccounts {
    private static List<Admin> adminList = new ArrayList<>();


    public void initiateAdminAccounts()
    {
        adminList.add(new Admin("admin1","789","101"));
        adminList.add(new Admin("admin2","787","202"));
        adminList.add(new Admin("admin3","786","303"));

    }

    protected boolean isDetailValid(String teacherID, String password)
    {
        for(Admin i: adminList)
        {
            if(String.valueOf(i.getAdminID()).equals(teacherID) && i.getAdminPin().equals(password))
                return true;
        }
        return false;
    }

    protected CurrentAdmin getAdmin(String adminID)
    {
        CurrentAdmin admin = new CurrentAdmin();
        for(Admin i: adminList)
        {
            if(String.valueOf(i.getAdminID()).equals(adminID))

            {

                admin.setAdminID(String.valueOf(i.getAdminID()));
                admin.setAdminName(i.getAdminName());
            }
        }
        return admin;
    }
}
