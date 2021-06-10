package classes;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private String studentName;
    private String studentID;
    private String studentDept;
    private String password;
    private int studentYear;
    private int studentAge;
    private int semester;

    private List<String> enrolledCourses = new ArrayList<>();


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
//    public void setStudentName(String studentName) {this.studentName = studentName ;}
//    public void setStudentDept(String studentDept) {this.studentDept=studentDept; }
//    public void setStudentYear(int studentYear) { this.studentYear=studentYear; }
//    public void setSemester(int semester) { this.semester=semester; }

    public void setPassword(String password) {this.password = password;}

//    public void iterateEnrolledCourses()
//    {
//        for(int i=0;i<enrolledCourses.size();i++)
//            System.out.println((i+1)+") "+enrolledCourses.get(i));
//    }
}
