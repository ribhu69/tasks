package classes;

public class StudentAssignment {

    private String courseCode;

    private String studentID;
    private int topicNumber;

    public StudentAssignment(String courseCode, String studentID,int topicNumber) {

        this.courseCode = courseCode;

        this.studentID = studentID;
        this.topicNumber=topicNumber;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getTopicNumber() {
        return topicNumber;
    }

    public String getStudentID() {
        return studentID;
    }
}
