package databaseClass;

import classes.CourseMarks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StudentMarksDatabase {
    private static List<CourseMarks> courseMarksList = new ArrayList<>();
    private final String marksDB = "F:\\Zoho\\CMS3\\CMS3\\src\\database\\StudentMarksDatabase.csv";

    public void initializeMarksDatabase() throws IOException {
        File inFile = new File(marksDB);
        Scanner inputFile = new Scanner(inFile);
        inputFile.nextLine();
        List<CourseMarks> tempCourseMarksList = new ArrayList<>();
        String str;
        String[] tokens;
        while (inputFile.hasNext()) {
            str = inputFile.nextLine();         // read a line of text from the file
            tokens = str.split(",");            // split the line using commas as delimiter
            CourseMarks courseMarks = new CourseMarks(
                    tokens[0], //Course Name
                    tokens[1], //Course ID
                    tokens[2], //Teacher ID
                    tokens[3], //Student ID
                    Integer.parseInt(tokens[4])); //Scored Exam Marks
            tempCourseMarksList.add(courseMarks);
        }
        courseMarksList = tempCourseMarksList;
    }

    protected List<CourseMarks> updateMarksDatabase(List<CourseMarks> updatedCourseMarksList) throws IOException {
        FileWriter csvWriter = new FileWriter(marksDB);
        csvWriter.write("Course Name,Course ID,Course Teacher ID,Student ID,Scored Exam Marks\n");

        for (int i = 0; i < updatedCourseMarksList.size(); i++) {
            List<String> items = new ArrayList<>();
            items.add(updatedCourseMarksList.get(i).getCourseName());
            items.add(updatedCourseMarksList.get(i).getCourseID());
            items.add(updatedCourseMarksList.get(i).getCourseTeacherID());
            items.add(updatedCourseMarksList.get(i).getStudentID());
            items.add(String.valueOf(updatedCourseMarksList.get(i).getExamScoredMarks()));


            String item = items.stream().collect(Collectors.joining(","));
            csvWriter.write(item + "\n");
        }

        csvWriter.close();
        initializeMarksDatabase();
        return courseMarksList;
    }

    protected List<CourseMarks> getMarksDB() {
        return courseMarksList;
    }
}
