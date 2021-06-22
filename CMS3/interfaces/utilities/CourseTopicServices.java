package interfaces.utilities;

import classes.Admin;
import classes.Course;
import classes.Topic;

import java.io.IOException;
import java.util.List;

public interface CourseTopicServices {
    void addCourseTopics(String courseName,String courseID) throws IOException;
    void addNewCourseTopic(String courseID) throws IOException;
    void changeCourseTitle(String courseID, String courseName);
    void changeCourseTopic() throws IOException;
    List<Topic> getCourseTopicList(Admin admin);
    void deleteSpecificCourseTopic(String courseID) throws IOException;
    void deleteCourseTopic(String courseID) throws IOException;
}
