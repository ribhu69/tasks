package helper;

import databaseClass.CourseDatabase;

import java.util.List;

public class CourseHelper extends CourseDatabase {

    public void initiateCourseDatabase()
    {
        super.initiateCourseList();
    }

    public String addCourse() { return super.addCourse(); }

    public void getCourseList(){super.showCourses();}
    public String deleteCourse()
    {
        return super.deleteCourse();
    }

    public void updateDatabase()
    {
        super.updateCourseList();
    }

    public String changeCourseTitle() { return super.changeCourseTitle();}

    public List<String> getTeacherCourseList(int teacherID)
    {
        return super.getTeacherCourseList(teacherID);
    }

    public String getCourseID(String newCourseName) {return super.getCurrentCourseID(newCourseName);}

    public void modifyCourseTeacher() { super.modifyCourseTeacher();}
}
