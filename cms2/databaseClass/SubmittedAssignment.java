package databaseClass;

import classes.Assignment;
import classes.StudentAssignment;
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

public class SubmittedAssignment {

    Scanner sc = new Scanner(System.in);
    private static List<StudentAssignment> submittedAssignment = new ArrayList<>();
    private final String submittedAssignmentDB = "D:\\Zoho\\Course Management System\\src\\database\\SubmittedAssignment.csv";

    //FILE IO
    protected void initiateSubmittedAssignmentList() {
        String[] courseDetails;
        List<StudentAssignment> tempSubmittedAssignmentList = new ArrayList<>();
        try
        {
            CSVReader reader = new CSVReaderBuilder(new FileReader(submittedAssignmentDB)).withSkipLines(1).build();
            while ((courseDetails = reader.readNext())!=null)
            {

                StudentAssignment studentAssignment = new StudentAssignment(
                        courseDetails[0], //courseCode
                        courseDetails[1], //StudentID
                        Integer.parseInt(courseDetails[2])); //courseTopicNumber
                tempSubmittedAssignmentList.add(studentAssignment);
            }
            submittedAssignment=tempSubmittedAssignmentList;

        }
        catch (FileNotFoundException e) {
            System.out.printf("Submitted Assignment DB Does not Exist.");

        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
    }

    protected void submitAssignment(String courseCode, String studentID, int topicNumber)
    {

        if(isAssignmentSubmitted(courseCode,studentID,topicNumber))
        {
            System.out.println("Assignment Already Submitted");
        }
        else
        {
            submittedAssignment.add(new StudentAssignment(courseCode,studentID,topicNumber));
            System.out.println("Assignment Submitted");
        }

    }

    protected boolean isAssignmentSubmitted(String courseCode, String studentID, int topicNumber)
    {
        for(StudentAssignment i:submittedAssignment)
            if((i.getCourseCode().equals(courseCode) && i.getStudentID().equals(studentID)) && (i.getTopicNumber()==topicNumber))
                return true;
        return false;
    }

    protected List<String> getStudentID(String courseCode, int topicNumber)
    {
        List<String> studentIdList = new ArrayList<>();
        for(StudentAssignment i:submittedAssignment)
        {
            if(i.getCourseCode().equals(courseCode) && i.getTopicNumber()==topicNumber)
            {
                studentIdList.add(i.getStudentID());
            }
        }
        return studentIdList;
    }

    //FILE IO
    protected void updateSubmittedAssignmentList()
    {

        try {

            CSVWriter writer = new CSVWriter(new FileWriter(submittedAssignmentDB));

            // adding header to csv
            String[] header = { "Course Name","Student ID","Topic Number" };
            writer.writeNext(header);

            for(int i=0;i<submittedAssignment.size();i++)
            {
                String[] submittedAssignmentListItem = new String[3];
                submittedAssignmentListItem[0]= submittedAssignment.get(i).getCourseCode(); //courseCode
                submittedAssignmentListItem[1]= submittedAssignment.get(i).getStudentID(); //StudentID
                submittedAssignmentListItem[2]= String.valueOf(submittedAssignment.get(i).getTopicNumber()); //courseTopicNumber
                writer.writeNext(submittedAssignmentListItem);
            }
            writer.close();
            initiateSubmittedAssignmentList();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
