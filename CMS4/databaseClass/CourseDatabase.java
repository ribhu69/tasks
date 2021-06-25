package databaseClass;

import course.Course;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CourseDatabase{

    private static List<Course> courseList = new ArrayList<>();
    private final String courseDB = "src\\database\\CourseDatabase.csv";
    public void initiateCourseList() throws IOException {
        File inFile = new File(courseDB);
        Scanner inputFile = new Scanner(inFile);
        inputFile.nextLine();
        List<Course> tempCourseList = new ArrayList<>();
        String str;
        String[] tokens;
        while (inputFile.hasNext()) {
            str = inputFile.nextLine();         // read a line of text from the file
            tokens = str.split(",");            // split the line using commas as delimiter

            Course course = new Course(
                    tokens[0], //courseName
                    tokens[1], //courseID
                    tokens[2], //teacherName
                    Integer.parseInt(tokens[3]), //teacherID
                    Integer.parseInt(tokens[4]), //teacherYear
                    Integer.parseInt(tokens[5])); //teacherSemester
            tempCourseList.add(course);
        }
        courseList = tempCourseList;
    }

    protected List<Course> updateCourseList(List<Course> updatedCourseList) throws IOException {
        FileWriter csvWriter = new FileWriter(courseDB);
        csvWriter.write("Course Name,Course ID,Course Teacher,teacherID,year,semester\n");

        for(int i=0;i<courseList.size();i++)
        {
            List<String> items = new ArrayList<>();
            items.add(courseList.get(i).getCourseName());
            items.add(courseList.get(i).getCourseID());
            items.add(courseList.get(i).getCourseTeacher());
            items.add(String.valueOf(courseList.get(i).getCourseTeacherID()));
            items.add(String.valueOf(courseList.get(i).getCourseYear()));
            items.add(String.valueOf(courseList.get(i).getCourseSemester()));
            String item = items.stream().collect(Collectors.joining(","));
            csvWriter.write(item + "\n");

        }

        csvWriter.close();
        initiateCourseList();
        return courseList;
    }


    protected List<Course> getCourseList(){ return courseList;}

}