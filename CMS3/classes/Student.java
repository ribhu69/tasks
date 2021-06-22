package classes;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private final String studentName;
    private String studentID;
    private final String studentDept;
    private String password;
    private final int studentYear;
    private final int studentAge;
    private final int semester;

    private final List<String> enrolledCourses = new ArrayList<>();


    public Student(String studentName, int age, String studentID, String password, String studentDept, int year, int semester)
    {
        this.studentDept=studentDept;
        this.studentName=studentName;
        this.studentAge=age;
        this.studentYear = year;
        this.studentID=studentID;
        this.password = password;
        this.semester=semester;
    }

    public String getPin()
    {
        return this.password;
    }

    public String getStudentID()
    {
        return this.studentID;
    }

    public int getStudentAge() { return studentAge; }

    public String getStudentName() {return this.studentName; }

    public int getStudentYear() {return this.studentYear;}

    public int getSemester() {return this.semester;}

    public String getStudentDept() { return this.studentDept;}


    public void setStudentID(String studentID) {this.studentID = studentID ;}


    public void setPassword(String password) {this.password = password;}

}
