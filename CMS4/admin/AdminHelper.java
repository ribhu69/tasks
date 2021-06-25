package admin;

import helper.PasswordHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class AdminHelper implements AdminServices {

    private static List<Admin> adminAccountsList;

    private final int max=700, min=799;
    Scanner sc = new Scanner(System.in);

    public AdminHelper(List<Admin> adminAccountsList)
    {
        this.adminAccountsList = adminAccountsList;
    }

    @Override
    public void addNewAdmin() {

            int nameFlag=1;
            int existingIdCount=0;
            String adminName="";
            int generatedAdminId=-1;
            int flag=1;
            System.out.println("Enter Admin Name");
            {
                do {
                    adminName = sc.nextLine();
                    if(adminName.length()<3)
                        System.out.println("Password Should Have minimum length of 3");
                    else
                    {
                        nameFlag=0;
                    }

                }while (nameFlag!=0);
            }

            do {
                generatedAdminId = generateAdminId();

                if(existingIdCount == adminAccountsList.size())
                    flag=0;

                if(doesAdminIdExist(generatedAdminId))
                {
                    generatedAdminId = generateAdminId();
                    existingIdCount+=1;
                }
                else
                {
                    flag=0;
                }
            }while (flag!=0);

            if(generatedAdminId==-1)
            {
                System.out.println("Admin ID Range is Full");
                existingIdCount=0;
            }

            else
            {
                System.out.println("Generated Admin ID: "+generatedAdminId);
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
                            adminAccountsList.add(new Admin(adminName,String.valueOf(generatedAdminId),password));
                            //updateDatabase(adminAccountsList);
                            System.out.println("New Admin Added");
                            return;
                        }
                    }while (true);
            }

    }

    @Override
    public void removeAdmin(Admin admin) {

            int chosenAdminID, flag=1;
            List<String> adminList = new ArrayList<>();
            for(Admin i: adminAccountsList)
            {
                if(!i.getAdminID().equals(admin.getAdminID()))
                {
                    adminList.add(i.getAdminID());
                }
            }
            System.out.println("Choose Admin ID to be removed");
            for(int i=0;i<adminList.size();i++)
            {
                System.out.println(i+1+") "+adminList.get(i));
            }
            System.out.println("Select Admin ID: ");

            do {
                chosenAdminID = sc.nextInt();
                if(chosenAdminID<=0 || chosenAdminID>adminList.size())
                {
                    System.out.println("Please Enter a valid Choice");
                }
                else flag=0;

            }while(flag!=0);
            String adminId = adminList.get(chosenAdminID-1);
            removeAdminAccount(adminId);
    }

    @Override
    public List<Admin> getAdminList() {
        return adminAccountsList;
    }

    @Override
    public void updateAdminList(List<Admin> adminList) {
        adminAccountsList = adminList;
    }

    private boolean doesAdminIdExist(int generatedId)
    {
        for(Admin i: adminAccountsList)
        {
            if(i.getAdminID().equals(String.valueOf(generatedId)))
                return true;
        }
        return false;
    }

    private int generateAdminId()
    {
        //min -> min range of value for adminId
        //max -> max range of value for adminId
        return ThreadLocalRandom.current().nextInt(700, 799 + 1);
    }

    private void removeAdminAccount(String adminID) {
        for (Admin i:adminAccountsList)
        {
            if(i.getAdminID().equals(adminID))
            {
                adminAccountsList.remove(i);
                System.out.println("Admin (Admin ID: "+adminID+") removed successfully.");

                return;
            }

        }
    }


}
