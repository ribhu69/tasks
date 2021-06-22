package databaseClass;

import classes.Student;
import classes.Teacher;
import helper.PasswordHelper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StudentAccounts{
    private static List<Student> studentList = new ArrayList<>();
    private final String studentAccountDB = "F:\\Zoho\\CMS3\\CMS3\\src\\database\\StudentAccounts.csv";
    private boolean isDatabaseInitiated = false;

    private PasswordHelper passwordHelper = new PasswordHelper();


    public void initializeStudentAccounts() throws IOException {
        File inFile = new File(studentAccountDB);
        Scanner inputFile = new Scanner(inFile);
        inputFile.nextLine();
        List<Student> tempStudentList = new ArrayList<>();
        String str;
        String[] tokens;
        while (inputFile.hasNext()) {
            str = passwordHelper.decryptPassword(inputFile.nextLine());         // read a line of text from the file
            tokens = str.split(",");            // split the line using commas as delimiter
            tokens[3] = passwordHelper.encryptPassword(tokens[3]);
            Student student = new Student(
                    tokens[0],                      //studentName
                    Integer.parseInt(tokens[1]),    //studentAge
                    tokens[2],                      //studentID
                    tokens[3],                      //Password
                    tokens[4],                      //department
                    Integer.parseInt(tokens[5]),    //year
                    Integer.parseInt(tokens[6]));   //semester
            tempStudentList.add(student);
        }
        studentList = tempStudentList;

    }
    protected List<Student> updateStudentAccounts(List<Student> updatedStudentAccountList) throws IOException {

        FileWriter csvWriter = new FileWriter(studentAccountDB);
        csvWriter.write("Student Name,Age,StudentID,Password,Branch,Year,Semester\n");

        for (int i = 0; i < updatedStudentAccountList.size(); i++) {
            List<String> items = new ArrayList<>();
            items.add(updatedStudentAccountList.get(i).getStudentName());
            items.add(String.valueOf(updatedStudentAccountList.get(i).getStudentAge()));
            items.add(updatedStudentAccountList.get(i).getStudentID());
            items.add(passwordHelper.decryptPassword(updatedStudentAccountList.get(i).getPin()));
            items.add(updatedStudentAccountList.get(i).getStudentDept());
            items.add(String.valueOf(updatedStudentAccountList.get(i).getStudentYear()));
            items.add(String.valueOf(updatedStudentAccountList.get(i).getSemester()));
            String item = items.stream().collect(Collectors.joining(","));
            item = passwordHelper.encryptPassword(item);
            csvWriter.write(item + "\n");
        }

        csvWriter.close();
        initializeStudentAccounts();
        return studentList;
    }

    protected List<Student> getStudentList()
    {
        return studentList;
    }
}