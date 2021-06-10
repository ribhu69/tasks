package helper;

import databaseClass.MarksDatabase;

public class MarksUtility extends MarksDatabase {


    public void initiateCourseMarksList() {super.initiateCourseMarksList();}

    public void updateDatabase() {super.updateCourseMarksList();}
    public void modifyCourseMarks()
    {
        super.modifyCourseMarks();
    }

    public void getStudentReport() {
        super.getStudentMarksReport();
    }

    public void allotAssignmentMarks(String courseID,String studentId){super.allotAssignmentMarks(courseID,studentId);};
}
