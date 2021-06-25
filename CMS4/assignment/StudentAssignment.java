package assignment;

public class StudentAssignment {

    private final String courseCode;

    private final String studentID;
    private final int topicNumber;
    private int assignmentMarks=-1;

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

    public void setAssignmentMarks(int assignmentMarks) {
        this.assignmentMarks = assignmentMarks;
    }

    public int getAssignmentMarks() {
        return assignmentMarks;
    }
}
