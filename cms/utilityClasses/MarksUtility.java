package utilityClasses;

import classes.CourseMarks;
import database.MarksDatabase;

public class MarksUtility extends MarksDatabase {

    public void getStudentReport()
    {
        super.getStudentMarksReport();
    }
    public void modifyCourseMarks()
    {
        super.modifyCourseMarks();
    }

    public void allotAssignmentMarks(String courseID, String studentID) {super.allotAssignmentMarks(courseID, studentID);}
}
