package classes;

public class Topic {

    private String topicName;
    private String courseName;
    private String courseID;

    public Topic(String topicName, String courseName, String courseID)
    {
        this.topicName = topicName;
        this.courseName = courseName;
        this.courseID = courseID;

    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseTopicName() {
        return topicName;
    }

    public void setCourseTopicName(String newTopicName) {
        this.topicName = newTopicName;
    }
}
