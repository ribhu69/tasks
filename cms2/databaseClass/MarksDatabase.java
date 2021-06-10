package databaseClass;

import classes.Course;
import classes.CourseMarks;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MarksDatabase {

    Scanner sc = new Scanner(System.in);
    private static List<CourseMarks> courseMarksList = new ArrayList<>();
    private final String marksDB = "D:\\Zoho\\Course Management System\\src\\database\\MarksDatabase.csv";
    DecimalFormat numberFormat = new DecimalFormat("#.00");

    //FILE IO
    protected void initiateCourseMarksList() {
        String[] courseMarksDetails;
        List<CourseMarks> tempCourseMarksList = new ArrayList<>();
        try
        {
            CSVReader reader = new CSVReaderBuilder(new FileReader(marksDB)).withSkipLines(1).build();
            while ((courseMarksDetails = reader.readNext())!=null)
            {

                CourseMarks courseMarks = new CourseMarks(
                        courseMarksDetails[0], //courseName
                        courseMarksDetails[1], //courseID
                        courseMarksDetails[2], //courseFaculty
                        courseMarksDetails[3], //enrolledStudentID
                        Integer.parseInt(courseMarksDetails[4]), //scoredAssignmentMarks (INT)
                        Integer.parseInt(courseMarksDetails[5])); //scoredExamMarks (INT)
                        tempCourseMarksList.add(courseMarks);
            }
            courseMarksList=tempCourseMarksList;

        }
        catch (FileNotFoundException e) {
            System.out.printf("Course DB Does not Exist.");

        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
    }

    protected void getStudentMarksReport()
    {
        System.out.println("Enter Student ID");
        String studentID = sc.nextLine();
        System.out.println("*** Student Marks Report ***");
        System.out.println();
        for(CourseMarks i: courseMarksList)
        {
            if(i.getStudentID().equals(studentID))
            {
                System.out.println("Course Name: "+i.getCourseName()+
                        "\nSubject Score: ("+i.getExamScoredMarks() +"/" + i.getExamTotalMarks() +")"+
                        "\nCumulative Assignment Score: (" +i.getScoredAssignmentMarks()+"/"+i.getTotalAssignmentMarks()+")" +
                        "\nCumulative Percentage: "+
                        numberFormat.format(cumulativePercentage(i.getScoredAssignmentMarks(),i.getTotalAssignmentMarks(),
                                i.getExamScoredMarks(),i.getExamTotalMarks()))+"%");
                System.out.println("-------------------------------------------");
            }

        }
    }

    private double cumulativePercentage(int scoredAssignmentMarks, int totalAssignmentMarks, int scoredExamMarks, int totalExamMarks)
    {
        int cumulativeScored = scoredAssignmentMarks+scoredExamMarks;
        int cumulativeTotal = totalAssignmentMarks+totalExamMarks;
        double cumulativePercentage = (cumulativeScored/(float)cumulativeTotal)*100;
        return cumulativePercentage;
    }

    protected void allotAssignmentMarks(String courseID, String studentID){
        for(CourseMarks i: courseMarksList)
        {
            int marks, flag=1;
            if(i.getCourseID().equals(courseID) && i.getStudentID().equals(studentID))
            {
                System.out.println("Enter Marks for Student: "+i.getStudentID()+" Out of 10");
                do {

                    marks = sc.nextInt();
                    if(marks>10)
                    {
                        System.out.println("Incorrect Range of Marks.\nPlease Try Try Again");
                    }
                    else
                        flag=0;
                }while(flag!=0);
                i.setScoredAssignmentMarks(marks);
                System.out.println("Updated Marks: "+i.getScoredAssignmentMarks());
                System.out.println();
            }
        }
    }




    protected void modifyCourseMarks()
    {
        System.out.println("Enter Course ID");
        String courseID = sc.nextLine();
        System.out.println("Enter Student ID");
        String studentID = sc.nextLine();
        for(CourseMarks i: courseMarksList)
        {
            if(i.getCourseID().equals(courseID) && i.getStudentID().equals(studentID))
            {
                System.out.println("Student Course Name: "+i.getCourseName()+"\nMarks in Course Exam: "+i.getExamScoredMarks());
                System.out.println("Enter new Marks");
                int marks = sc.nextInt();
                i.setScoredExamMarks(marks);
                System.out.println("Updated Marks in Course Exam: "+i.getExamScoredMarks());
            }
        }
    }


    //FILE IO
    protected void updateCourseMarksList()
    {

        try {

            CSVWriter writer = new CSVWriter(new FileWriter(marksDB));

            // adding header to csv
            String[] header = { "Course Name","Course ID","Faculty Name","Student ID","Scored Assignment Marks","Scored Exam Marks" };
            writer.writeNext(header);

            for(int i=0;i<courseMarksList.size();i++)
            {
                String[] courseMarksListItem = new String[6];
                courseMarksListItem[0]= courseMarksList.get(i).getCourseName();
                courseMarksListItem[1]= courseMarksList.get(i).getCourseID();
                courseMarksListItem[2]= courseMarksList.get(i).getCourseFaculty();
                courseMarksListItem[3]= String.valueOf(courseMarksList.get(i).getStudentID());
                courseMarksListItem[4]= String.valueOf(courseMarksList.get(i).getScoredAssignmentMarks());
                courseMarksListItem[5]= String.valueOf(courseMarksList.get(i).getExamScoredMarks());

                writer.writeNext(courseMarksListItem);
            }
            writer.close();
            initiateCourseMarksList();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
