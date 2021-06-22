package interfaces;

import classes.Student;

import java.io.IOException;

public interface StudentInterface {

    void welcome(Student student) throws IOException;
    Student isStudentAccountValid() throws IOException;
}
