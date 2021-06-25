package databaseClass;

import assignment.Assignment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AssignmentDatabase {
    private static List<Assignment> assignmentList = new ArrayList<>();
    private final String assignmentDB = "src\\database\\AssignmentDatabase.csv";

    public void initiateAssignmentList() throws IOException {
        File inFile = new File(assignmentDB);
        Scanner inputFile = new Scanner(inFile);
        inputFile.nextLine();
        List<Assignment> tempAssignmentList = new ArrayList<>();
        String str;
        String[] tokens;
        while (inputFile.hasNext()) {
            str = inputFile.nextLine();         // read a line of text from the file
            tokens = str.split(",");            // split the line using commas as delimiter
            Assignment assignment = new Assignment(
                    tokens[0],                    //assignmentTitle
                    tokens[1],                    //courseID
                    Integer.parseInt(tokens[2]),  //courseTopicNumber
                    Integer.parseInt(tokens[3])); //assignmentNumber
            tempAssignmentList.add(assignment);
        }
        assignmentList = tempAssignmentList;
    }

    protected List<Assignment> updateAssignmentList(List<Assignment> updatedAssignmentList) throws IOException {
        FileWriter csvWriter = new FileWriter(assignmentDB);
        csvWriter.write("Assignment Title,Course ID,Course Topic Number,Assignment Number\n");

        for (int i = 0; i < updatedAssignmentList.size(); i++) {
            List<String> items = new ArrayList<>();
            items.add(updatedAssignmentList.get(i).getAssignmentTitle());
            items.add(updatedAssignmentList.get(i).getCourseId());
            items.add(String.valueOf(updatedAssignmentList.get(i).getCourseTopicNumber()));
            items.add(String.valueOf(updatedAssignmentList.get(i).getAssignmentNumber()));

            String item = items.stream().collect(Collectors.joining(","));
            csvWriter.write(item + "\n");
        }

        csvWriter.close();
        initiateAssignmentList();
        return assignmentList;
    }

    protected List<Assignment> getAssignmentList() {
        return assignmentList;
    }
}
