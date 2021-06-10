package classes;

import databaseClass.CourseDatabase;
import databaseClass.StudentAccounts;
import databaseClass.SubmittedAssignment;
import helper.*;

public class DatabaseHelper {

    CourseHelper courseHelper = new CourseHelper();
    CourseTopicHelper courseTopicHelper = new CourseTopicHelper();
    StudentHelper studentHelper = new StudentHelper();
    EnrolledCourseHelper enrolledCourseHelper = new EnrolledCourseHelper();
    MarksUtility marksUtility = new MarksUtility();
    SubmittedAssignmentHelper submittedAssignmentHelper = new SubmittedAssignmentHelper();
    AssignmentHelper assignmentHelper = new AssignmentHelper();

    public void initiateDatabase()
    {
        courseHelper.initiateCourseDatabase();
        courseTopicHelper.initiateCourseTopicDatabase();
        studentHelper.initiateStudentDatabase();
        enrolledCourseHelper.initiateEnrolledCourseDatabase();
        marksUtility.initiateCourseMarksList();
        submittedAssignmentHelper.initiateSubmittedAssignmentList();
        assignmentHelper.initiateAssignmentDatabase();
    }
    public void updateDatabase() {
        courseHelper.updateDatabase();
        courseTopicHelper.updateDatabase();
        studentHelper.updateDatabase();
        enrolledCourseHelper.updateDatabase();
        marksUtility.updateDatabase();
        submittedAssignmentHelper.updateDatabase();
        assignmentHelper.updateDatabase();
    }
}
