package classes;

public class Admin {

    private String adminName;
    private String adminID;
    private String password;


    public Admin(String adminName, String adminID, String password)
    {
        this.adminID=adminID;
        this.adminName=adminName;
        this.password = password;
    }

    public String getAdminID()
    {
        return this.adminID;
    }

    public String getAdminName() {return this.adminName;}

//    public String getAdminPin()
//    {
//        return this.password;
//    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
