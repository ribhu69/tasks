package databaseClass;

import classes.StudentAssignment;
import classes.Teacher;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SubmittedAssignment {

    Scanner sc = new Scanner(System.in);
    private static List<StudentAssignment> submittedAssignment = new ArrayList<>();
    private final String submittedAssignmentDB = "F:\\Zoho\\CMS3\\CMS3\\src\\database\\SubmittedAssignment.csv";

    //FILE IO
    public void initiateSubmittedAssignmentList() throws FileNotFoundException {

        List<StudentAssignment> tempSubmittedAssignmentList = new ArrayList<>();
        File inFile = new File(submittedAssignmentDB);
        Scanner inputFile = new Scanner(inFile);
        inputFile.nextLine();
        String str;
        String[] tokens;
        while (inputFile.hasNext()) {
            str = inputFile.nextLine();         // read a line of text from the file
            tokens = str.split(",");            // split the line using commas as delimiter
            StudentAssignment studentAssignment =
                    new StudentAssignment(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
            studentAssignment.setAssignmentMarks(Integer.parseInt(tokens[3]));
            tempSubmittedAssignmentList.add(studentAssignment);
        }
        submittedAssignment = tempSubmittedAssignmentList;
    }


    //FILE IO
    public List<StudentAssignment> updateSubmittedAssignment(List<StudentAssignment> updatedSubmittedAssignment)
            throws IOException {
        FileWriter csvWriter = new FileWriter(submittedAssignmentDB);
        csvWriter.write("Course Code,Student ID,Topic Number,Marks\n");

        for (int i = 0; i < submittedAssignment.size(); i++) {
            List<String> items = new ArrayList<>();
            items.add(submittedAssignment.get(i).getCourseCode());
            items.add(submittedAssignment.get(i).getStudentID());
            items.add(String.valueOf(submittedAssignment.get(i).getTopicNumber()));
            items.add(String.valueOf(submittedAssignment.get(i).getAssignmentMarks()));
            String item = items.stream().collect(Collectors.joining(","));
            csvWriter.write(item + "\n");
        }

        csvWriter.close();
        initiateSubmittedAssignmentList();
        return submittedAssignment;
    }

    protected List<StudentAssignment> getSubmittedAssignmentList()
    {
        return submittedAssignment;
    }
}
