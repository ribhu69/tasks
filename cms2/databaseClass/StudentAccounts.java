package databaseClass;

import classes.Course;
import classes.Student;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class StudentAccounts {
    Scanner sc = new Scanner(System.in);

    private static List<Student> studentList = new ArrayList<>();
    private final String studentAccountDB = "D:\\Zoho\\Course Management System\\src\\database\\StudentAccounts.csv";
    private String[] userDetails;

    protected void initiateStudentList() {
        String[] studentDetails;
        List<Student> tempStudentList = new ArrayList<>();
        try
        {
            CSVReader reader = new CSVReaderBuilder(new FileReader(studentAccountDB)).withSkipLines(1).build();
            while ((studentDetails = reader.readNext())!=null)
            {

                Student student = new Student(
                        studentDetails[0], //studentName
                        Integer.parseInt(studentDetails[1]), //studentAge
                        studentDetails[2], //studentID
                        studentDetails[3], //Password
                        studentDetails[4], //branch
                        Integer.parseInt(studentDetails[5]),//year
                        Integer.parseInt(studentDetails[6]) //semester
                );

                tempStudentList.add(student);
            }
            studentList=tempStudentList;

        }
        catch (FileNotFoundException e) {
            System.out.printf("Course DB Does not Exist.");

        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
    }

    protected boolean isAccountValid(String userID, String password) throws IOException, CsvValidationException {
        CSVReader reader = new CSVReaderBuilder(new FileReader(studentAccountDB)).withSkipLines(1).build();
//        CSVReader reader = new CSVReader(new FileReader(adminAccountDB));

        while((userDetails = reader.readNext())!=null)
        {

            if(userDetails[2].equals(userID) && userDetails[3].equals(password))
            {

                return true;
//                break;
            }
        }
        reader.close();
        return false;
    }

    protected String[] getStudent()
    {
        String[] studentDetails = new String[6];
        studentDetails[0]=userDetails[0]; //name
        studentDetails[1]=userDetails[1]; //age
        studentDetails[2]=userDetails[2]; //reg id
        studentDetails[3]=userDetails[4]; //branch
        studentDetails[4]=userDetails[5]; //year
        studentDetails[5]=userDetails[6]; //sem
        return studentDetails;
    }

    protected void addStudent()
    {
        System.out.println("Enter Student Name: ");
//        sc.nextLine();
        String studentName = sc.nextLine();
        System.out.println("Enter Student Age");
        int age=sc.nextInt();
        System.out.println("Enter Student ID: ");
        sc.nextLine();
        String studentID=sc.nextLine();
        System.out.println("Enter new Password");
//        sc.nextLine();
        String password = sc.nextLine();
        System.out.println("Enter Student Department: ");
//        sc.nextLine();
        String studentDept=sc.nextLine();
        System.out.println("Year of Study: ");
        int year=sc.nextInt();
        System.out.println("Semester of Study: ");
        int sem=sc.nextInt();
        studentList.add(new Student(studentName,age,studentID,password,studentDept,year,sem));
        System.out.println("Student Added. Please Wait..");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    protected void removeStudent()
    {
        System.out.println("Enter Student ID");
        String studentID = sc.nextLine();
        for(Student i: studentList)
        {
            if(i.getStudentID().equals(studentID))
            {

                System.out.println("Student Name: "+i.getStudentName()+"");
                studentList.remove(i);
                System.out.println("Student Details Removed. Please Wait..");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
        System.out.println("Student Name does not Exists");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void getStudentInfo()
    {
        System.out.println("Enter Student ID");
        String studentId = sc.nextLine();
        for(Student i: studentList)
        {
            if(i.getStudentID().equals(studentId))

            {
                System.out.println("Student Name: "+i.getStudentName()+
                        "\nStudent ID: "+i.getStudentID() +
                        "\nStudent Department: "+i.getStudentDept()+
                        "\nStudent Year of Study: "+i.getStudentYear()+
                        "\nSemester: "+i.getSemester());
                return;
            }
        }
        System.out.println("Student Name does not Exists");
    }

    protected void changeAccountPassword(String studentID) {
        System.out.println("Enter Current Password");
        String currentPassword = sc.nextLine();

        for (Student i : studentList) {
            if (i.getStudentID().equals(studentID) && i.getPin().equals(currentPassword)) {

                do {
                    System.out.println("Enter New Password");
                    String newPassword = sc.nextLine();
                    if (newPassword.equals(i.getPin()))
                        System.out.println("Password Cannot be the Same as Previous Password\n");
                    else {
                        System.out.println("Re-enter New Password");
                        String verifyPassword = sc.nextLine();

                        if (verifyPassword.equals(newPassword)) {
                            i.setPassword(newPassword);
                            System.out.println("Password Changed");
                            return;
                        } else {
                            System.out.println("Password does not match");
                        }

                    }
                } while (true);

            }
        }
    }
    //FILE IO
    protected void updateStudentList()
    {

        try {

            CSVWriter writer = new CSVWriter(new FileWriter(studentAccountDB));

            // adding header to csv
            String[] header = { "Student Name","Age","StudentID","Password","Branch","Year","Semester" };
            writer.writeNext(header);

            for(int i=0;i<studentList.size();i++)
            {
                String[] studentListItem = new String[7];
                studentListItem[0]= studentList.get(i).getStudentName(); //name
                studentListItem[1]= String.valueOf(studentList.get(i).getStudentAge()); //age
                studentListItem[2]= studentList.get(i).getStudentID(); //studentID
                studentListItem[3]= String.valueOf(studentList.get(i).getPin()); //password
                studentListItem[4]= studentList.get(i).getStudentDept(); //branch
                studentListItem[5]= String.valueOf(studentList.get(i).getStudentYear()); //year
                studentListItem[6]= String.valueOf(studentList.get(i).getSemester()); //semester

                writer.writeNext(studentListItem);
            }
            writer.close();
            initiateStudentList();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
