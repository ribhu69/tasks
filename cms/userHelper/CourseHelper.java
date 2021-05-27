package userHelper;

import database.CourseDatabase;

import java.util.List;
import java.util.Scanner;

public class CourseHelper extends CourseDatabase {

    Scanner sc = new Scanner(System.in);
    public String  addCourse() {return super.addCourse();}
    public String deleteCourse() {return super.deleteCourse();}

    public String getCourseID(String courseName) { return super.getCourseID(courseName);}

    public void modifyExistingCourse() {
        System.out.println("Choose from below options" +
                "\n1) Change course Name" +
                "\n2) Change Course Topic" +
                "\n3) Add New Course Topic" +
                "\n4) Delete Course Topic");

        int option = sc.nextInt();
        switch (option) {
            case 1:
                super.changeCourseTitle();
                break;

            case 2:
                super.changeCourseTopic();
                break;

            case 3:
                super.addCourseTopic();
                break;
            case 4:
                super.deleteCourseTopic();
        }
    }

        public void modifyCourseTeacher()
        {
            super.modifyCourseTeacher();
        }

        public void getCourseList()
        {
            super.showCourses();
        }

    public List<String> getTeacherCourseList(int teacherID)
    {
        return super.getTeacherCourseList(teacherID);
    }

}
