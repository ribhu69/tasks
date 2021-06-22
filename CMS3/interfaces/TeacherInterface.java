package interfaces;

import classes.Student;
import classes.Teacher;

import java.io.IOException;

public interface TeacherInterface {

    void welcome(Teacher teacher) throws IOException;
    Teacher isTeacherAccountValid() throws IOException;



}
