package user;

import admin.Admin;
import student.Student;
import teacher.Teacher;

import java.io.IOException;

public interface UserInterface {
    Student studentAccountValid() throws IOException;
    Teacher teacherAccountValid() throws IOException;
    Admin adminAccountValid() throws IOException;
}
