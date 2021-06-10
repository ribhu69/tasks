package helper;

import databaseClass.CourseTopicDatabase;

public class CourseTopicHelper extends CourseTopicDatabase {

    public void initiateCourseTopicDatabase()
    {
        super.initiateCourseTopicList();
    }

    public void updateDatabase()
    {
        super.updateCourseTopicList();
    }

    public void changeCourseTitle(String newCourseName,String courseID)
    {
        super.changeCourseTitle(newCourseName,courseID);
    }

    public void changeCourseTopic()
    {
        super.changeCourseTopic();
    }

    public void deleteSpecificCourseTopic() { super.deleteSpecificTopic();}
    public void removeCourseTopic(String courseID) { super.deleteCourseTopic(courseID);}

    public void addCourseTopic(String courseName, String courseID) {
        super.addCourseTopic(courseName,courseID);}

    public void addNewCourseTopic()
    {
        super.addNewCourseTopic();
    }


}
