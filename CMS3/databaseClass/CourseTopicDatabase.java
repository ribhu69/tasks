package databaseClass;

import classes.Topic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class
CourseTopicDatabase{

    private static List<Topic> courseTopicList = new ArrayList<>();
    private final String courseTopicDB = "F:\\Zoho\\CMS3\\CMS3\\src\\database\\CourseTopicDatabase.csv";

    public void initiateCourseTopicList() throws IOException {
        File inFile = new File(courseTopicDB);
        Scanner inputFile = new Scanner(inFile);
        inputFile.nextLine();
        List<Topic> tempCourseTopicList = new ArrayList<>();
        String str;
        String[] tokens;
        while (inputFile.hasNext()) {
            str = inputFile.nextLine();         // read a line of text from the file
            tokens = str.split(",");            // split the line using commas as delimiter
            Topic topic = new Topic(
                    tokens[0],                    //topicName
                    tokens[1],                    //courseName
                    tokens[2]);                   //courseID
            tempCourseTopicList.add(topic);
        }
        courseTopicList = tempCourseTopicList;
    }

    protected List<Topic> updateCourseTopicList(List<Topic> updatedCourseTopicList) throws IOException {
        FileWriter csvWriter = new FileWriter(courseTopicDB);
        csvWriter.write("Topic Name,Course Name,Course ID\n");

        for (int i = 0; i < updatedCourseTopicList.size(); i++) {
            List<String> items = new ArrayList<>();
            items.add(updatedCourseTopicList.get(i).getCourseTopicName());
            items.add(updatedCourseTopicList.get(i).getCourseName());
            items.add(updatedCourseTopicList.get(i).getCourseID());

            String item = items.stream().collect(Collectors.joining(","));
            csvWriter.write(item + "\n");
        }

        csvWriter.close();
        initiateCourseTopicList();
        return courseTopicList;
    }

    protected List<Topic> getCourseTopicsList()
    {
        return courseTopicList;
    };

}