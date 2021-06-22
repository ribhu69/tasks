package classes;

public class Assignment {

    private String assignmentTitle;
    private final String courseCode;
    private final int courseTopicNumber;
    private int assignmentNumber;

    public Assignment(String assignmentTitle, String courseCode, int courseTopic, int assignmentNumber)
    {
        this.assignmentNumber=assignmentNumber;
        this.courseCode=courseCode;
        this.courseTopicNumber=courseTopic;
        this.assignmentTitle=assignmentTitle;
        this.assignmentNumber=assignmentNumber;

    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public String getCourseId() {
        return courseCode;
    }



    public int getCourseTopicNumber() { return courseTopicNumber; }

    public int getAssignmentNumber() {
        return assignmentNumber;
    }





}
