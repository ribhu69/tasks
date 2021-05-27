package utilityClasses;

import database.CourseTopicDatabase;

public class CourseTopicUtility extends CourseTopicDatabase {

    protected void deleteCourseTopic(String courseID)
    {
        super.deleteCourseTopic(courseID);
    }


    public void addCourseTopic(String courseName,String courseID)
    {
        super.addCourseTopic(courseName,courseID);
    }


}
