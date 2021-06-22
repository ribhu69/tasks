package databaseClass;

import classes.Admin;
import classes.Assignment;
import classes.Course;
import classes.Student;
import helper.PasswordHelper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AdminAccounts{
    private static List<Admin> adminList = new ArrayList<>();
    private final String adminAccountDB = "F:\\Zoho\\CMS3\\CMS3\\src\\database\\AdminAccounts.csv";
    private PasswordHelper passwordHelper = new PasswordHelper();


    public void initiateAdminAccounts() throws IOException {
        File inFile = new File(adminAccountDB);
        Scanner inputFile = new Scanner(inFile);
        inputFile.nextLine();
        List<Admin> tempAdminList = new ArrayList<>();
        String str;
        String[] tokens;
        while (inputFile.hasNext()) {
            str = passwordHelper.decryptPassword(inputFile.nextLine());         // read a line of text from the file
            tokens = str.split(",");            // split the line using commas as delimiter
            tokens[2] = passwordHelper.encryptPassword(String.valueOf(tokens[2]));
            Admin admin = new Admin(
                    tokens[0], //adminID
                    tokens[1], //adminName
                    tokens[2]); //adminPassword;

            tempAdminList.add(admin);
        }
        adminList = tempAdminList;
    }

    protected List<Admin> updateAdminList(List<Admin> updatedAdminList) throws IOException {
        {
            FileWriter csvWriter = new FileWriter(adminAccountDB);
            csvWriter.write("adminName,AdminID,AdminPWD\n");

            for(int i=0;i<updatedAdminList.size();i++)
            {
                List<String> items = new ArrayList<>();
                items.add(updatedAdminList.get(i).getAdminName());
                items.add(updatedAdminList.get(i).getAdminID());
                items.add(passwordHelper.decryptPassword(updatedAdminList.get(i).getAdminPin()));
                String item = items.stream().collect(Collectors.joining(","));
                item = passwordHelper.encryptPassword(item  );
                csvWriter.write(item + "\n");
            }

            csvWriter.close();
            initiateAdminAccounts();
            return adminList;
        }
    }

    protected boolean isAdminValid(Admin admin)
    {
        for(Admin i:adminList)
        {
            if(i.getAdminID().equals(admin.getAdminID()))
                return true;

        }
        return false;
    }
    protected List<Admin> getAdminList()
    {
        return adminList;
    }



}