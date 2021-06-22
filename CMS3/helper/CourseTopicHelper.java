package helper;

import classes.Admin;
import classes.Course;
import classes.Topic;
import databaseClass.CourseTopicDatabase;
import interfaces.utilities.CourseTopicServices;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CourseTopicHelper extends CourseTopicDatabase implements CourseTopicServices {

    private Admin admin;
    private List<Topic> courseTopicList;
    Scanner sc = new Scanner(System.in);
    public CourseTopicHelper(Admin admin)
    {
        this.admin = admin;
        courseTopicList = super.getCourseTopicsList();

    }

    //invoked after adding a new Course
    @Override
    public void addCourseTopics(String courseName, String courseID) throws IOException {
        System.out.println("Enter Topics For: "+courseID);
        for(int i=0;i<6;i++)
        {
            System.out.println("Enter Topic "+(i+1)+" Name");
            String topicName = sc.nextLine();
            courseTopicList.add(new Topic(topicName,courseName,courseID));
        }
        updateDatabase(courseTopicList);
    }

    public void addNewCourseTopic(String courseID) throws IOException {
        if(admin!=null)
        {
            int count = getTopicCountForTopic(courseID);
            System.out.println("The Count is "+count);
            if( count>0 && count <6 )
            {
                System.out.println("Enter New Topic Name");
                sc.nextLine();
                String newTopicName = sc.nextLine();
                courseTopicList.add(new Topic(newTopicName,getCourseName(courseID),courseID));
                updateDatabase(courseTopicList);
                System.out.println("Course Added Successfully");
            }

            else if(count==-1)
                System.out.println("Course ID not available in database.");

            else
                System.out.println("Maximum Number of Course Topics Reached.");

        }
        else
            System.out.println("Invalid Admin Operation.");
    }

    @Override
    public void changeCourseTitle(String courseID, String courseName) {
        for(Topic i:courseTopicList)
        {
            if(i.getCourseID().equals(courseID))
                i.setCourseName(courseName);
        }
    }

    @Override
    public List<Topic> getCourseTopicList(Admin admin) {
        if(admin!=null)
            return courseTopicList;
        return null;
    }

    @Override
    public void deleteSpecificCourseTopic(String courseID) throws IOException {
        if(admin!=null)
        {
            int flag=1, topicNumber=0, topicDeleted=0, topicIndex=-1;
            String topicName="";
            List<String> courseTopics = new ArrayList<>();
            for(Topic i: courseTopicList)
            {

                if(i.getCourseID().equals(courseID))
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
                if(i.getCourseTopicName().equals(topicName) && i.getCourseID().equals(courseID))
                {
                    topicIndex = courseTopicList.indexOf(i);
                    topicDeleted=1;
                    break;
                }
            }

            if(topicDeleted==1)
            {
                deleteIndexedTopic(topicIndex);
                updateDatabase(courseTopicList);
                System.out.println("Course Topic Deleted Successfully.");
            }
            else
                System.out.println("No Such Topic Exists/Altered");

        }
        else
        {
            System.out.println("Invalid Admin Operation.");
        }

    }

    @Override
    public void changeCourseTopic() throws IOException {
        if(admin!=null)
        {
            List<Topic> courseTopicsList = getCourseTopicList(admin);
            Set<String> topicsSet = getCourseTopicsId();
            List<String> topicsList = new ArrayList<>(topicsSet);
            int courseNumber=0, flag=1;
            if(topicsList.size()>0)
            {
                System.out.println("Choose Course ID");
                for(int i = 0; i< topicsList.size(); i++)
                {
//                    System.out.println((i+1)+") "+ topicsList.get(i).getCourseName()+
//                            "\nCourse ID: "+courseTopicsList.get(i).getCourseID().toUpperCase());
                    System.out.println((i+1)+") "+ topicsList.get(i).toUpperCase());
                }
                System.out.println("Select Course: ");

                do {
                    courseNumber = sc.nextInt();
                    if(courseNumber<=0 || courseNumber> topicsList.size())
                    {
                        System.out.println("Please Enter a valid Choice");
                    }
                    else flag=0;

                }while(flag!=0);
                flag=1;

                String courseID = topicsList.get(courseNumber-1);

                System.out.println("Selected Course ID: "+courseID);
                List<String> currentCourseTopicList = new ArrayList<>();
                for(Topic i:courseTopicsList)
                {
                    if(i.getCourseID().equals(courseID))
                        currentCourseTopicList.add(i.getCourseTopicName());
                }

                System.out.println("Choose Course Topic To be modified");
                for(int i=0;i<currentCourseTopicList.size();i++)
                {
                    System.out.println((i+1)+") "+ currentCourseTopicList.get(i));
                }

                int courseTopicNumber=0;
                do {
                    courseTopicNumber = sc.nextInt();
                    if(courseTopicNumber<=0 || courseTopicNumber> currentCourseTopicList.size())
                    {
                        System.out.println("Please Enter a valid Choice");
                    }
                    else flag=0;

                }while(flag!=0);

                String courseTopicName = currentCourseTopicList.get(courseTopicNumber-1);
                System.out.println("Enter New Course Topic Name");
                sc.nextLine();
                String newCourseTopicName = sc.nextLine();

                for(Topic i:courseTopicsList)
                {
                    if(i.getCourseID().equals(courseID) && i.getCourseTopicName().equals(courseTopicName)) {
                        i.setCourseTopicName(newCourseTopicName);
                        System.out.println("Course Topic Changed Successfully.");
                        break;
                    }
                }
                updateCourseTopicList(courseTopicsList);
            }
            else
                System.out.println("Error");

        }
        else
        {
            System.out.println("Invalid Admin Operation");
        }

    }

    //DCT (Delete Course Topic) invoked after deleting course
    @Override
    public void deleteCourseTopic(String courseID) throws IOException {

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
        updateDatabase(courseTopicList);
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

    private Set<String> getCourseTopicsId()
    {
        Set<String> setTopicList = new HashSet<>();
        for(Topic i:courseTopicList)
        {
            setTopicList.add(i.getCourseID());
        }
        return setTopicList;
    }

    private String getCourseName(String courseID)
    {
        for(Topic i:courseTopicList)
        {
            if(i.getCourseID().equals(courseID))
                return i.getCourseName();
        }
        return null;
    }

    private void deleteIndexedTopic(int index)
    {
        courseTopicList.remove(index);
    }


    private int getTopicCountForTopic(String courseId)
    {
        int count=0;
        int isTopicAvailable=0;
        for(Topic i:courseTopicList)
        {
            if(i.getCourseID().equals(courseId))
                isTopicAvailable=1;
                count+=1;
        }
        if(isTopicAvailable==0)
            return -1;
        return count;
    }
    private void updateDatabase(List<Topic> courseTopicList) throws IOException {
        this.courseTopicList = super.updateCourseTopicList(courseTopicList);
    }
}
