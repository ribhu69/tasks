package databaseClass;

import classes.Assignment;
import classes.Course;
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

public class AssignmentDatabase {

    Scanner sc = new Scanner(System.in);
    private static List<Assignment> assignmentList = new ArrayList<>();
    private final String assignmentDB = "D:\\Zoho\\Course Management System\\src\\database\\AssignmentDatabase.csv";
    SubmittedAssignment submittedAssignment = new SubmittedAssignment();

    //FILE IO
    protected void initiateAssignmentList() {
        String[] courseDetails;
        List<Assignment> tempAssignmentList = new ArrayList<>();
        try
        {
            CSVReader reader = new CSVReaderBuilder(new FileReader(assignmentDB)).withSkipLines(1).build();
            while ((courseDetails = reader.readNext())!=null)
            {

                Assignment assignment = new Assignment(
                        courseDetails[0], //courseName
                        courseDetails[1], //courseID
                        Integer.parseInt(courseDetails[2]), //courseTopicNumber
                        Integer.parseInt(courseDetails[3])); //assignmentNumber
                        tempAssignmentList.add(assignment);
            }
            assignmentList=tempAssignmentList;

        }
        catch (FileNotFoundException e) {
            System.out.printf("Assignment DB Does not Exist.");

        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
    }

    protected void addAssignment()
    {
        System.out.println("Enter Course Code");
        String courseCode = sc.nextLine();
        System.out.println("Enter Course Topic Number");
        int courseTopic = sc.nextInt();
        System.out.println("Enter Assignment Title");
        sc.nextLine();
        String assignmentTitle  = sc.nextLine();
        System.out.println("Enter Assignment Number");
        int assignmentNumber = sc.nextInt();

        assignmentList.add(new Assignment(assignmentTitle,courseCode,courseTopic,assignmentNumber));
        System.out.println("New Assignment Added");
    }

    protected int getAssignmentCount(String courseId) {
        int count=0;
        for(Assignment i:assignmentList)
        {
            if(i.getCourseCode().equals(courseId))
                count++;
        }
        return count;
    }

    protected void getStudentAssignmentList(String courseCode, String studentID)
    {
        for(Assignment i:assignmentList)
        {
            if(i.getCourseCode().equals(courseCode))
            {
                System.out.println("Assignment Title: "+i.getAssignmentTitle()+
                        "\nCourse Topic Number:"+i.getCourseTopicNumber()+
                        "\nAssignment Number: "+i.getAssignmentNumber());
                if(submittedAssignment.isAssignmentSubmitted(i.getCourseCode(),studentID,i.getCourseTopicNumber()))
                    System.out.println("Assignment Status: Submitted");
                else
                    System.out.println("Assignment Status: Not Submitted");
                System.out.println();
            }
        }
    }

    protected List<Assignment> getAssignmentList()
    {
        return assignmentList;
    }

    protected void getAssignmentList(String courseCode)
    {
        for(Assignment i:assignmentList)
        {
            if(i.getCourseCode().equals(courseCode))
            {
                System.out.println("Assignment Title: "+i.getAssignmentTitle()+
                        "\nCourse Topic Number:"+i.getCourseTopicNumber()+
                        "\nAssignment Number: "+i.getAssignmentNumber());
//                if(submittedAssignment.isAssignmentSubmitted(i.getCourseCode(),i.get))
                System.out.println();
            }
        }
    }


    //FILE IO
    protected void updateAssignmentList()
    {

        try {

            CSVWriter writer = new CSVWriter(new FileWriter(assignmentDB));

            // adding header to csv
            String[] header = { "Assignment Title","Course ID","Course Topic Number","Assignment Number" };
            writer.writeNext(header);

            for(int i=0;i<assignmentList.size();i++)
            {
                String[] assignmentListItem = new String[4];

                assignmentListItem[0]= assignmentList.get(i).getAssignmentTitle(); //Assignment Title
                assignmentListItem[1]= assignmentList.get(i).getCourseCode(); //Course ID
                assignmentListItem[2]= String.valueOf(assignmentList.get(i).getCourseTopicNumber()); //Course Topic Number
                assignmentListItem[3]= String.valueOf(assignmentList.get(i).getAssignmentNumber()); //Assignment Number

                writer.writeNext(assignmentListItem);
            }
            writer.close();
//            initiateAssignmentList();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
