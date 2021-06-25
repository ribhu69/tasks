package teacher;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private final String teacherName;
    private int teacherId;
    private String password;
    private final List<String> allotedSubjects = new ArrayList<>();

    public Teacher(String teacherName, int teacherId, String password)
    {
        this.teacherName = teacherName;
        this.teacherId = teacherId;
        this.password = password;
    }

    public int getTeacherId()
    {
        return this.teacherId;
    }

    public String getTeacherPin()
    {
        return this.password;
    }

    public String getTeacherName() { return this.teacherName;}

    public void setTeacherId(int teacherID) {this.teacherId=teacherID;}

    public void setTeacherPin(String password)
    {
        this.password = password;
    }


}
