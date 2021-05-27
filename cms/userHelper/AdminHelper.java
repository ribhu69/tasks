package userHelper;

import database.AdminAccounts;
import user.CurrentAdmin;

public class AdminHelper extends AdminAccounts {

    public CurrentAdmin getAdmin(String id)
    {
        return super.getAdmin(id);
    }

    public boolean isDetailValid(String id, String password)
    {
        return super.isDetailValid(id,password);
    }
}
