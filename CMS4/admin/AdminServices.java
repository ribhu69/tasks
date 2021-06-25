package admin;

import java.io.IOException;
import java.util.List;

public interface AdminServices {

    void addNewAdmin() throws IOException;
    void removeAdmin(Admin admin) throws IOException;

    List<Admin> getAdminList();
    void updateAdminList(List<Admin> adminList);
}
