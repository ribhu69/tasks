package database;

import classes.Topic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseTopicDatabase {
    Scanner sc = new Scanner(System.in);

    private static  List<Topic> courseTopicsList = new ArrayList<>();

    public void initializeCourseTopicsDatabase()
    {

        courseTopicsList.add(new Topic("Topic1","Applied Science","ge101"));
        courseTopicsList.add(new Topic("Topic2","Applied Science","ge101"));
        courseTopicsList.add(new Topic("Topic3","Applied Science","ge101"));
        courseTopicsList.add(new Topic("Topic4","Applied Science","ge101"));
        courseTopicsList.add(new Topic("Topic5","Applied Science","ge101"));
        courseTopicsList.add(new Topic("Topic6","Applied Science","ge101"));

        courseTopicsList.add(new Topic("Topic1","Engg Mathematics","me101"));
        courseTopicsList.add(new Topic("Topic2","Engg Mathematics","me101"));
        courseTopicsList.add(new Topic("Topic3","Engg Mathematics","me101"));
        courseTopicsList.add(new Topic("Topic4","Engg Mathematics","me101"));
        courseTopicsList.add(new Topic("Topic5","Engg Mathematics","me101"));
        courseTopicsList.add(new Topic("Topic6","Engg Mathematics","me101"));

        courseTopicsList.add(new Topic("Topic1","Molecular Chemistry","ge102"));
        courseTopicsList.add(new Topic("Topic2","Molecular Chemistry","ge102"));
        courseTopicsList.add(new Topic("Topic3","Molecular Chemistry","ge102"));
        courseTopicsList.add(new Topic("Topic4","Molecular Chemistry","ge102"));
        courseTopicsList.add(new Topic("Topic5","Molecular Chemistry","ge102"));
        courseTopicsList.add(new Topic("Topic6","Molecular Chemistry","ge102"));

        courseTopicsList.add(new Topic("Topic1","Engg Mechanics","cv108"));
        courseTopicsList.add(new Topic("Topic2","Engg Mechanics","cv108"));
        courseTopicsList.add(new Topic("Topic3","Engg Mechanics","cv108"));
        courseTopicsList.add(new Topic("Topic4","Engg Mechanics","cv108"));
        courseTopicsList.add(new Topic("Topic5","Engg Mechanics","cv108"));
        courseTopicsList.add(new Topic("Topic6","Engg Mechanics","cv108"));

        courseTopicsList.add(new Topic("Topic1","Engg Physics","ge103"));
        courseTopicsList.add(new Topic("Topic2","Engg Physics","ge103"));
        courseTopicsList.add(new Topic("Topic3","Engg Physics","ge103"));
        courseTopicsList.add(new Topic("Topic4","Engg Physics","ge103"));
        courseTopicsList.add(new Topic("Topic5","Engg Physics","ge103"));
        courseTopicsList.add(new Topic("Topic6","Engg Physics","ge103"));

        courseTopicsList.add(new Topic("Topic1","x","sc102"));
        courseTopicsList.add(new Topic("Topic2","x","sc102"));
        courseTopicsList.add(new Topic("Topic3","x","sc102"));
        courseTopicsList.add(new Topic("Topic4","x","sc102"));
        courseTopicsList.add(new Topic("Topic5","x","sc102"));
        courseTopicsList.add(new Topic("Topic6","x","sc102"));

    }

    protected void addCourseTopic(String courseName,String courseID)
    {

       for(int i=0;i<6;i++)
       {
           System.out.println("Enter Topic "+(i+1)+" Name");
           String topicName = sc.nextLine();
           addCourseTopicToList(topicName,courseName,courseID);
       }

    }

    private void addCourseTopicToList(String topicName,String courseName, String courseID)
    {
        courseTopicsList.add(new Topic(topicName,courseName,courseID));

    }
    protected void deleteCourseTopic(String courseID)
    {
//        System.out.println(courseTopicsList.size());
        int totalCount = getOccurrences(courseID);
        int courseTopicIndex;
        while (totalCount>0)
        {

            courseTopicIndex = courseIndex(courseID);
            if(courseTopicIndex!= -1)
            {
                courseTopicsList.remove(courseTopicIndex);
            }

            totalCount--;
        }

//        System.out.println(courseTopicsList.size());
    }

    private int getOccurrences(String courseID)
    {
        int count=0;
        for(Topic i:courseTopicsList)
        {
            if(i.getCourseID().equals(courseID))
                count++;
        }
        return count;
    }

    private int courseIndex(String courseID)
    {
        for(Topic i:courseTopicsList)
        {
            if(i.getCourseID().equals(courseID))
                return courseTopicsList.indexOf(i);
        }
        return -1;
    }
}
