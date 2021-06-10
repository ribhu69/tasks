package databaseClass;

import classes.Course;
import classes.Topic;
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

public class CourseTopicDatabase {
    private static List<Topic> courseTopicList = new ArrayList<>();
    private final String courseTopicDB = "D:\\Zoho\\Course Management System\\src\\database\\CourseTopicDatabase.csv";
    Scanner sc = new Scanner(System.in);

    protected void initiateCourseTopicList() {

        String[] courseTopicDetails;
        List<Topic> tempCourseTopicList = new ArrayList<>();
        try
        {
            CSVReader reader = new CSVReaderBuilder(new FileReader(courseTopicDB)).withSkipLines(1).build();
            while ((courseTopicDetails = reader.readNext())!=null)
            {

                Topic courseTopic = new Topic(
                        courseTopicDetails[0], //topicName
                        courseTopicDetails[1], //courseName
                        courseTopicDetails[2]); //teacherID
                tempCourseTopicList.add(courseTopic);
            }
            courseTopicList = tempCourseTopicList;
        }
        catch (FileNotFoundException e) {
            System.out.printf("Course DB Does not Exist.");

        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
    }


    protected void changeCourseTitle(String newCourseName,String courseID)
    {
        for(Topic i:courseTopicList)
        {
            if(i.getCourseID().equals(courseID))
            {
                i.setCourseTopicName(newCourseName);
            }
        }
    }

    protected void changeCourseTopic()
    {
        int flag=1, topicNumber;
        System.out.println("Enter Course ID");
        String courseId = sc.nextLine();
        List<String> courseTopics = new ArrayList<>();
        for(Topic i: courseTopicList)
        {

            if(i.getCourseID().equals(courseId))
            {
                courseTopics.add(i.getCourseTopicName());
            }
        }

        for(int i=0;i<courseTopics.size();i++)
        {
            System.out.println((i+1)+") "+courseTopics.get(i));
        }

        System.out.println("Select Topic Number: ");

        do {
            topicNumber = sc.nextInt();
            if(topicNumber<=0 || topicNumber>courseTopics.size())
            {
                System.out.println("Please Enter a valid Choice");
            }
            else flag=0;

        }while(flag!=0);


        String topicName = courseTopics.get(topicNumber-1);

        System.out.println("Enter New Topic Name");
        sc.nextLine();
        String newTopicName = sc.nextLine();

        for(Topic i: courseTopicList)
        {
            if(i.getCourseID().equals(courseId) && i.getCourseTopicName().equals(topicName))
            {
                i.setCourseTopicName(newTopicName);
//                return;
            }
        }
    }

    //invoked after Adding a New Course
    protected void addCourseTopic(String courseName,String courseID)
    {

        System.out.println("Course Name: "+courseName+" "+"Course ID: "+courseID);
        for(int i=0;i<6;i++)
        {
            System.out.println("Enter Topic "+(i+1)+" Name");
            String topicName = sc.nextLine();
            courseTopicList.add(new Topic(topicName,courseName,courseID));
        }

    }

    protected void addNewCourseTopic()
    {
        System.out.println("Enter Course ID");
        String courseID = sc.nextLine();
        if(isCourseTopicPresent(courseID))
        {
            String courseName = courseTopicName(courseID);
            if(courseName!=null)
            {
                System.out.println("Enter Number of New Topics to be Added");
                int count = sc.nextInt();
                while(count>0)
                {
                    System.out.println("Enter New Course Topic");
                    sc.nextLine();
                    String newCourseTopic = sc.nextLine();
                    courseTopicList.add(new Topic(newCourseTopic,courseName,courseID));
                    count--;
                }
            }
            else
            {
                System.out.println("Course Name Does not Exist");
            }

        }
        else {
            System.out.println("Course Not Present in Database.");
        }

    }

    private boolean isCourseTopicPresent(String courseID)
    {
        for(Topic i:courseTopicList)
        {
            if(i.getCourseID().equals(courseID))
            {
                return true;
            }
        }
        return false;
    }

    private String courseTopicName(String courseID)
    {
        for(Topic i:courseTopicList)
        {
            if(i.getCourseID().equals(courseID))
            {
                return i.getCourseTopicName();
            }
        }
        return null;
    }

    protected void deleteSpecificTopic()
    {
        int flag=1, topicNumber=0, topicDeleted=1, topicIndex=-1;
        String topicName="";
        System.out.println("Enter Course ID\n");
        String courseId = sc.nextLine();
        List<String> courseTopics = new ArrayList<>();
        for(Topic i: courseTopicList)
        {

            if(i.getCourseID().equals(courseId))
            {
                courseTopics.add(i.getCourseTopicName());
            }
        }

        for(int i=0;i<courseTopics.size();i++)
        {
            System.out.println((i+1)+") "+courseTopics.get(i));
        }

        System.out.println("Select Topic Number: ");

        do {
            topicNumber = sc.nextInt();
            if(topicNumber<=0 || topicNumber>courseTopics.size())
            {
                System.out.println("Please Enter a valid Choice");
            }
            else
            {
                topicName = courseTopics.get(topicNumber-1);
                flag=0;
            }

        }while(flag!=0);

        for(Topic i:courseTopicList)
        {
            if(i.getCourseTopicName().equals(topicName) && i.getCourseID().equals(courseId))
            {
                topicIndex = courseTopicList.indexOf(i);
                break;
            }
        }

        deleteIndexedTopic(topicIndex);

//        for(int i=0;i<courseTopicList.size();i++)
//        {
//            System.out.println((i+1)+") "+courseTopics.get(i));
//        }

        if(topicDeleted==1)
        {
            System.out.println("No Such Topic Exists/Altered");
        }
    }
    //DCT (Delete Course Topic) invoked after deleting course
    protected void deleteCourseTopic(String courseID)
    {

        int totalCount = getOccurrences(courseID);
        int courseTopicIndex;
        while (totalCount>0)
        {

            courseTopicIndex = courseIndex(courseID);
            if(courseTopicIndex!= -1)
            {
                courseTopicList.remove(courseTopicIndex);
            }

            totalCount--;
        }
    }

    //DCT helper 1
    private int getOccurrences(String courseID)
    {
        int count=0;
        for(Topic i:courseTopicList)
        {
            if(i.getCourseID().equals(courseID))
                count++;
        }
        return count;
    }

    //DCT helper 2
    private int courseIndex(String courseID)
    {
        for(Topic i:courseTopicList)
        {
            if(i.getCourseID().equals(courseID))
                return courseTopicList.indexOf(i);
        }
        return -1;
    }

    //FILE IO
    protected void updateCourseTopicList()
    {

        try {

            CSVWriter writer = new CSVWriter(new FileWriter(courseTopicDB));

            // adding header to csv
            String[] header = { "Topic Name","Course Name","Course ID" };
            writer.writeNext(header);

            for(int i=0;i<courseTopicList.size();i++)
            {
                String[] courseTopicItem = new String[3];
                courseTopicItem[0]= courseTopicList.get(i).getCourseTopicName();
                courseTopicItem[1]= courseTopicList.get(i).getCourseName();
                courseTopicItem[2]= courseTopicList.get(i).getCourseID();

                writer.writeNext(courseTopicItem);
            }
            writer.close();
            initiateCourseTopicList();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //delete a specific topic
    private void deleteIndexedTopic(int index)
    {
        courseTopicList.remove(index);
    }

}
