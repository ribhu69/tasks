package databaseClass;

import java.io.IOException;

public class DatabaseHelper {
StudentAccounts studentAccounts = new StudentAccounts();
TeacherAccounts teacherAccounts = new TeacherAccounts();
AdminAccounts adminAccounts = new AdminAccounts();


    public void initiateDatabase() throws IOException {
        studentAccounts.initializeStudentAccounts();
        teacherAccounts.initializeTeacherAccounts();
        adminAccounts.initiateAdminAccounts();


    }
}
