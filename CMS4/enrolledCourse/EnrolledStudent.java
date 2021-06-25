package enrolledCourse;

public class EnrolledStudent {

    private final String studentID;
    private int attendance;
    private final String courseName;
    private final int totalClasses;
    private final String courseId;


    public EnrolledStudent(String courseName,String courseId, int totalClasses, String studentID, int attendance)
    {
        this.courseId = courseId;
        this.courseName = courseName;
        this.studentID=studentID;
        this.totalClasses=totalClasses;
        this.attendance=attendance;
    }

    public int getAttendance() { return this.attendance;}

    public int getTotalClasses() { return this.totalClasses;}
    public String getCourseName() {return this.courseName;}

    public String getStudentID() {return this.studentID;}
    public String getCourseId() {return this.courseId;}

    public void setAttendance(int updatedAttendance) {this.attendance =+ updatedAttendance;}
    public void incrementAttendance()
    {
        this.attendance += 1;
    }
    public void incrementAttendance(int value)
    {
        this.attendance += value;
    }

}
