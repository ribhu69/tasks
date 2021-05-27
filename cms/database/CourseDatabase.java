package database;

import classes.Course;
import classes.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseDatabase {
    Scanner sc=new Scanner(System.in);
    private static List<Course> courseList = new ArrayList<>();

    public void initiateCourses()
    {
        courseList.add(new Course("E.Mathematics(M2)","me101","R.Madhavan",1000,3,5));
        courseList.add(new Course("E.Mathematics(M3)","me111","R.Madhavan",1000,3,6));

        courseList.add(new Course("Random Course","sc112","S.Raman",2000,2,3));

        courseList.add(new Course("Applied Science","ge101","Vivek Singla",2000,2,3));
        courseList.add(new Course("Applied Science","ge111","Vivek Singla",2000,2,4));
        courseList.add(new Course("Molecular Chemistry(MC1)","ge112","A.B.Bhake",3000,3,6));
        courseList.add(new Course("Molecular Chemistry(MC2)","ge102","A.B.Bhake",3000,3,5));
        courseList.add(new Course("E.Mechanics(EM1)","cv118","N.Wajgi",4000,3,5));
        courseList.add(new Course("E.Mechanics(EM2)","cv108","N.Wajgi",4000,3,6));
        courseList.add(new Course("E.Physics(P1)","ge103","G.Suri",5000,2,3));
        courseList.add(new Course("E.Physics(P2)","ge113","G.Suri",5000,2,4));
        courseList.add(new Course("x(Sem1)","sc102","S.Raman",6000,2,2));
        courseList.add(new Course("x(Sem2)","sc112","S.Raman",6000,2,1));


    }


    protected String addCourse()
    {
        System.out.println("Enter Course Name");
//        sc.nextLine();
        String courseName= sc.nextLine();
        for(Course i: courseList)
        {
            if(courseName.length()>1 && i.getCourseName().equals(courseName))
            {
                System.out.println("Course already exists");
                break;
            }
        }
            System.out.println("Enter Course ID");
            String courseID = sc.nextLine();
            System.out.println("Enter Course Teacher:");
            String courseTeacher = sc.nextLine();
            System.out.println("Enter Course Teacher ID");
            int coursTeacherID = sc.nextInt();
            System.out.println("Enter Year");
            int courseYear = Integer.parseInt(sc.nextLine());
//                sc.nextLine();
            System.out.println("Enter Semester");
            int courseSemester = sc.nextInt();

            courseList.add(new Course(courseName,courseID,courseTeacher,coursTeacherID,courseYear,courseSemester));
        return courseName;
    }

    protected String deleteCourse()
    {
        System.out.println("Enter Course ID");
        String courseID = sc.nextLine();
        int flag=1;
        for(Course i: courseList)
        {
            if(i.getCourseID().equals(courseID))
            {
                courseList.remove(i);
                flag=0;
                System.out.println("Course Removed.");
                break;
            }
        }
        if(flag==1)
            System.out.println("No such course exists");

        return courseID;
    }

    protected String getCourseID(String courseName)
    {
        String courseId = null;
        for(Course i:courseList)
        {
            if(i.getCourseName().equals(courseName))
                courseId = i.getCourseName();
        }
        return courseId;
    }

    protected void modifyCourseTeacher()
    {

        System.out.println("Enter Course ID");
        sc.nextLine();
        String courseId = sc.nextLine();
        for(Course i: courseList)
        {
            if(i.getCourseID().equals(courseId))
            {

                System.out.println("Current Teacher: "+ i.getCourseTeacher());
                System.out.println("Enter New Name");
                i.modifyCourseTeacher(sc.nextLine());
                System.out.println("Enter Teacher ID");
                i.setCourseTeacherID(sc.nextInt());
                System.out.println("Course Teacher Updated");
                break;
            }
        }
    }

    protected void changeCourseTitle()
    {
        System.out.println("Enter Existing Course ID");
        String courseID = sc.nextLine();
        for(Course i: courseList)
        {
            if(i.getCourseID().equals(courseID))
            {
                System.out.println("Course Exists.\nCurrent Course Name: "+i.getCourseName()+"\nEnter New Name of Course");
                String newCourseName = sc.nextLine();
                if(i.getCourseName().equals(newCourseName))
                {
                    System.out.println("Course Name is the same as previous name");
                }
                else
                {
                    i.setCourseName(sc.nextLine());
                    System.out.println("Course Name Updated.\nUpdated Course Name: "+i.getCourseName());
                    return;
                }
            }
        }
    }

    protected void changeCourseTopic()
    {
        System.out.println("Enter Course Name");

        String courseName = sc.nextLine();
        Course currentCourse = findCourse(courseName);
        if(currentCourse!=null)
        {
            currentCourse.iterateCourseTopics();
            currentCourse.modifyCourseTopic();
            currentCourse.iterateCourseTopics();
        }
        else
        {
            System.out.println("No Such Course Exists");
        }
    }

    protected void showCourses()
    {
        for(Course i:courseList)
        {
            System.out.println("Course Name: "+i.getCourseName()+
                    "\nCourse Year: "+i.getCourseYear()+
                    "\nCourse Code: "+i.getCourseID()+
                    "\nCourse Teacher Name: "+i.getCourseTeacher()+
                    "\nCourse Teacher ID: "+i.getCourseTeacherID());
            System.out.println();
        }
    }

    protected void deleteCourseTopic() {
        System.out.println("Enter Course Name");
        sc.nextLine();
        String courseName = sc.nextLine();
        Course currentCourse = findCourse(courseName);
        if(currentCourse!=null)
        {
            currentCourse.iterateCourseTopics();
            currentCourse.deleteCourseTopic();
            currentCourse.iterateCourseTopics();
        }

    }

    protected void addCourseTopic() {
        System.out.println("Enter Course Name");
        String courseName = sc.nextLine();
        Course currentCourse = findCourse(courseName);
        if(currentCourse!=null)
        {
            currentCourse.addCourseTopic();
        }
        else
        {
            System.out.println("Course Doesn't Exist");
        }
    }

    private Course findCourse(String courseName)
    {

        for(Course i: courseList)
        {
            if(i.getCourseName().equals(courseName))
                return i;
        }
        return null;
    }

    public String hasCourse(String id)
    {
        for(Course i:courseList)
        {
            if(i.getCourseID().equals(id))
                return i.getCourseID();
        }
        return null;
    }

    protected List<String> getTeacherCourseList(int teacherID)
    {
        List<String> teacherCourseList = new ArrayList<>();
        for(Course i:courseList)
        {
            if(i.getCourseTeacherID()==teacherID)
            {
                teacherCourseList.add(i.getCourseID());
            }
        }

        return teacherCourseList;
    }

}
