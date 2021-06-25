package databaseClass;

import admin.Admin;
import assignment.Assignment;
import assignment.StudentAssignment;
import course.Course;
import course.Topic;
import enrolledCourse.EnrolledStudent;
import marks.CourseMarks;
import student.Student;
import teacher.Teacher;

import java.io.IOException;
import java.util.List;

public class DatabaseInitiator {


    EnrolledCourseDatabase enrolledCourseDatabase = new EnrolledCourseDatabase();
    AssignmentDatabase assignmentDatabase = new AssignmentDatabase();
    SubmittedAssignment submittedAssignment = new SubmittedAssignment();
    StudentMarksDatabase studentMarksDatabase = new StudentMarksDatabase();
    TeacherAccounts teacherAccounts = new TeacherAccounts();
    StudentAccounts studentAccounts = new StudentAccounts();
    AdminAccounts adminAccounts = new AdminAccounts();
    CourseDatabase courseDatabase = new CourseDatabase();
    CourseTopicDatabase courseTopicDatabase = new CourseTopicDatabase();

    public void initiateDatabase() throws IOException {
        courseDatabase.initiateCourseList();
        courseTopicDatabase.initiateCourseTopicList();
        teacherAccounts.initializeTeacherAccounts();
        enrolledCourseDatabase.initializeEnrolledCourseList();
        assignmentDatabase.initiateAssignmentList();
        submittedAssignment.initiateSubmittedAssignmentList();
        studentMarksDatabase.initializeMarksDatabase();
        adminAccounts.initiateAdminAccounts();
        studentAccounts.initializeStudentAccounts();

    }

    //admin accounts
    public List<Admin> getAdminAccounts() {return adminAccounts.getAdminList();}

    public void updateAdminAccounts(List<Admin> adminList) throws IOException {
        adminAccounts.updateAdminList(adminList);
    }

        //courses
    public List<Course> getCourseList()
    {
        return courseDatabase.getCourseList();
    }

    public void updateCourseList(List<Course> courseList) throws IOException {
        courseDatabase.updateCourseList(courseList);
    }

        //enrolled students
    public List<EnrolledStudent> getEnrolledStudents()
    {
        return enrolledCourseDatabase.getEnrolledStudentsList();
    }

    public void updateEnrolledCourse(List<EnrolledStudent> enrolledStudentList) throws IOException {
        enrolledCourseDatabase.updateEnrolledCourseList(enrolledStudentList);
    }

    //student accounts
    public List<Student> getStudentAccounts()
    {
        return studentAccounts.getStudentList();
    }

    public void updateStudentAccounts(List<Student> studentList) throws IOException {
        studentAccounts.updateStudentAccounts(studentList);
    }

//teacher accounts
    public List<Teacher> getTeacherAccounts(){return teacherAccounts.getTeacherList();}

    public void updateTeacherAccounts(List<Teacher> teacherList) throws IOException {
        teacherAccounts.updateTeacherAccounts(teacherList);
    }


//assignments
    public List<Assignment> getAssignments()
    {
        return assignmentDatabase.getAssignmentList();
    }

    public void updateAssignmentsList(List<Assignment> assignmentList) throws IOException {
        assignmentDatabase.updateAssignmentList(assignmentList);
    }


//submitted assignments
    public List<StudentAssignment> getSubmittedAssignments()
    {
        return  submittedAssignment.getSubmittedAssignmentList();
    }

    public void updateSubmittedAssignmentList(List<StudentAssignment> studentAssignmentList) throws IOException {
        submittedAssignment.updateSubmittedAssignment(studentAssignmentList);
    }


//coursemarks
    public List<CourseMarks> getStudentMarksList() {return studentMarksDatabase.getMarksDB();}

    public void updateStudentMarkList(List<CourseMarks> courseMarksList) throws IOException {
        studentMarksDatabase.updateMarksDatabase(courseMarksList);
    }

    //course topics
    public List<Topic> getCourseTopics() {
        return courseTopicDatabase.getCourseTopicsList();}

    public void updateCourseTopics(List<Topic> topicList) throws IOException {
        courseTopicDatabase.updateCourseTopicList(topicList);
    }
}
