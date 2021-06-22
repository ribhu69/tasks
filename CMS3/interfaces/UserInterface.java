package interfaces;

import classes.Admin;
import classes.Student;
import classes.Teacher;

import java.io.IOException;

public interface UserInterface {
    Student studentAccountValid() throws IOException;
    Teacher teacherAccountValid() throws IOException;
    Admin adminAccountValid() throws IOException;
}
