package interfaces;

import classes.Admin;

import java.io.IOException;

public interface AdminInterface {

    void welcome(Admin admin) throws IOException;
    Admin isAdminAccountValid() throws IOException;
}
