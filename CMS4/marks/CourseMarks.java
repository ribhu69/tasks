package marks;

public class CourseMarks {
    private String courseName;
    private String courseTeacherID;
    private final String courseID;
    private int scoredMarks;
    private int scoredExamMarks;
    private final int examTotalMarks = 40;
    private final String enrolledStudentID;

    public CourseMarks(String courseName, String courseID, String courseTeacherID, String enrolledStudentID, int scoredExamMarks)
    {
        this.courseName=courseName;
        this.courseID = courseID;
        this.courseTeacherID=courseTeacherID;
        this.enrolledStudentID = enrolledStudentID;
        this.scoredExamMarks = scoredExamMarks;
    }


    public int getExamTotalMarks() { return this.examTotalMarks;}

    public int getExamScoredMarks() { return this.scoredExamMarks;}

    public void setScoredExamMarks(int scoredExamMarks) {
        this.scoredExamMarks = scoredExamMarks;
    }

    public void setCourseTeacherID(String teacherID) {this.courseTeacherID = teacherID;}


    public String getCourseID() {return this.courseID;}

    public String getCourseName() {return this.courseName;}

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStudentID() {return this.enrolledStudentID;}

    public String getCourseTeacherID() {
        return courseTeacherID;
    }


}
