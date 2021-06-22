package databaseClass;

import java.io.IOException;

public class DatabaseInitiator {
    EnrolledCourseDatabase enrolledCourseDatabase = new EnrolledCourseDatabase();
    AssignmentDatabase assignmentDatabase = new AssignmentDatabase();
    SubmittedAssignment submittedAssignment = new SubmittedAssignment();
    StudentMarksDatabase studentMarksDatabase = new StudentMarksDatabase();
    CourseDatabase courseDatabase = new CourseDatabase();
    CourseTopicDatabase courseTopicDatabase = new CourseTopicDatabase();

    public void initiateDatabase() throws IOException {
        courseDatabase.initiateCourseList();
        courseTopicDatabase.initiateCourseTopicList();
        enrolledCourseDatabase.initializeEnrolledCourseList();
        assignmentDatabase.initiateAssignmentList();
        submittedAssignment.initiateSubmittedAssignmentList();
        studentMarksDatabase.initializeMarksDatabase();
    }
}
