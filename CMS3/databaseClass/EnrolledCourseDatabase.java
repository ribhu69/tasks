package databaseClass;

import classes.CourseMarks;
import classes.EnrolledStudent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EnrolledCourseDatabase{
    private static List<EnrolledStudent> enrolledStudentsList = new ArrayList<>();
    private final String enrolledCourseDB = "F:\\Zoho\\CMS3\\CMS3\\src\\database\\EnrolledCourseDatabase.csv";

    public void initializeEnrolledCourseList() throws IOException {
        File inFile = new File(enrolledCourseDB);
        Scanner inputFile = new Scanner(inFile);
        inputFile.nextLine();
        List<EnrolledStudent> tempEnrolledCourseList = new ArrayList<>();
        String str;
        String[] tokens;
        while (inputFile.hasNext()) {
            str = inputFile.nextLine();         // read a line of text from the file
            tokens = str.split(",");            // split the line using commas as delimiter
            EnrolledStudent enrolledStudent = new EnrolledStudent(
                    tokens[0],                    //courseName
                    tokens[1],                    //courseID
                    Integer.parseInt(tokens[2]),  //totalClasses
                    tokens[3],                    //studentID
                    Integer.parseInt(tokens[4])); //attendance
            tempEnrolledCourseList.add(enrolledStudent);
        }
        enrolledStudentsList = tempEnrolledCourseList;
    }

    protected List<EnrolledStudent> updateEnrolledCourseList(List<EnrolledStudent> updatedEnrolledCourseList) throws IOException {
        FileWriter csvWriter = new FileWriter(enrolledCourseDB);
        csvWriter.write("Course Name,Course Code,Total Classes,studentID,attendance\n");

        for (int i = 0; i < updatedEnrolledCourseList.size(); i++) {
            List<String> items = new ArrayList<>();
            items.add(updatedEnrolledCourseList.get(i).getCourseName());
            items.add(updatedEnrolledCourseList.get(i).getCourseId());
            items.add(String.valueOf(updatedEnrolledCourseList.get(i).getTotalClasses()));
            items.add(updatedEnrolledCourseList.get(i).getStudentID());
            items.add(String.valueOf(updatedEnrolledCourseList.get(i).getAttendance()));


            String item = items.stream().collect(Collectors.joining(","));
            csvWriter.write(item + "\n");
        }

        csvWriter.close();
        initializeEnrolledCourseList();
        return enrolledStudentsList;
    }

    protected List<EnrolledStudent> getEnrolledStudentsList() {
        return enrolledStudentsList;
    }
}