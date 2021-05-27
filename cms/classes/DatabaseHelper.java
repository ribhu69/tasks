package classes;

import database.*;

public class DatabaseHelper {

    StudentAccounts studentAccounts = new StudentAccounts();
    TeacherAccounts teacherAccounts = new TeacherAccounts();
    AdminAccounts adminAccounts = new AdminAccounts();
    CourseDatabase courseDatabase = new CourseDatabase();
    EnrolledCourseDatabase enrolledCourseDatabase = new EnrolledCourseDatabase();
    MarksDatabase marksDatabase = new MarksDatabase();
    AssignmentDatabase assignmentDatabase = new AssignmentDatabase();
    CourseTopicDatabase courseTopicDatabase = new CourseTopicDatabase();
    SubmittedAssignment submittedAssignment = new SubmittedAssignment();

    public void initiateDatabase()
    {
        studentAccounts.initiateStudentAccounts();
        teacherAccounts.initiateTeacherAccounts();
        adminAccounts.initiateAdminAccounts();
        courseDatabase.initiateCourses();
        marksDatabase.initializeMarksDatabase();
        enrolledCourseDatabase.initializeEnrolledCourseDatabase();
        assignmentDatabase.initiateAssignmentList();
        courseTopicDatabase.initializeCourseTopicsDatabase();
        submittedAssignment.initializeSubmittedAssignment();

    }
}
