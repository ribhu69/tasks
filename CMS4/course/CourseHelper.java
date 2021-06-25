package course;

import admin.Admin;
import teacher.Teacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.String.valueOf;

public class CourseHelper  implements CourseServices
{
    private Teacher teacher;
    private Admin admin;
    List<Course> courseList;
    Scanner sc = new Scanner(System.in);

    public CourseHelper(List<Course> courseList)
    {
        this.courseList = courseList;
    }


    @Override
    public List<String> getTeacherCourseList(Teacher teacher) {
        List<String> teacherCourseList = new ArrayList<>();
        for(Course i:courseList)
        {
            if(i.getCourseTeacherID() == teacher.getTeacherId())
            {
                teacherCourseList.add(i.getCourseID());
            }
        }
        return teacherCourseList;
    }


    @Override
    public List<Course> getCourseList()
    {
            return courseList;

    }

    @Override
    public Course getRequestedCourse() {

            String courseID = getCourseAdmin();
            for(Course i: courseList)
            {
                if(i.getCourseID().equals(courseID))
                    return i;
            }
        return null;
    }

    @Override
    public void updateCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public String getCourseAdmin()
    {
            int courseNumber,flag=1;
            for(int i=0;i<courseList.size();i++)
            {
                System.out.println((i+1)+") Course ID: "+courseList.get(i).getCourseID().toUpperCase()+
                        "\nCourse Name: "+courseList.get(i).getCourseName());
                System.out.println();
            }
            System.out.println("Choose from above Registered Courses: ");

            do {
                courseNumber = sc.nextInt();
                if(courseNumber<=0 || courseNumber>courseList.size())
                {
                    System.out.println("Please Enter a valid Choice");
                }
                else flag=0;

            }while(flag!=0);

            String courseID = courseList.get(courseNumber-1).getCourseID();
            System.out.println("Chosen Course ID: "+courseID.toUpperCase());

            return courseID;
    }

    public String getCourseID(String courseName)
    {
        if(admin!=null)
        {
            for(Course i:courseList)
            {
                if(i.getCourseName().equals(courseName))
                    return i.getCourseID();
            }
        }
        return null;
    }

    @Override
    public String getCourseName(String courseID) {
        String courseName="";
            for(Course i:courseList)
            {
                if(i.getCourseID().equals(courseID))
                    courseName = i.getCourseName();
            }
            return courseName;
    }


    @Override
    public Course getExistingCourse()
    {
            int courseNumber,flag=1;
            for(int i=0;i<courseList.size();i++)
            {
                System.out.println((i+1)+") Course ID: "+courseList.get(i).getCourseID().toUpperCase()+
                        "\nCourse Name: "+courseList.get(i).getCourseName());
                System.out.println();
            }
            System.out.println("Choose from above Registered Courses: ");

            do {
                courseNumber = sc.nextInt();
                if(courseNumber<=0 || courseNumber>courseList.size())
                {
                    System.out.println("Please Enter a valid Choice");
                }
                else flag=0;

            }while(flag!=0);

            String courseID = courseList.get(courseNumber-1).getCourseID();
            System.out.println("Chosen Course ID: "+courseID.toUpperCase());

            for(Course i:courseList)
                if(i.getCourseID().equals(courseID))
                    return i;

            return null;

    }

    @Override
    public String getCourseID(Teacher teacher){

        int courseNumber,flag=1;
    System.out.println("** Your Assigned Courses **");
    List<Course> teacherCourseList = new ArrayList<>();
        for (Course course : courseList) {
            if (course.getCourseTeacherID() == teacher.getTeacherId()) {
                teacherCourseList.add(course);
            }
        }

    for(int i=0;i<teacherCourseList.size();i++)
    {
        System.out.println(i+1+") "+teacherCourseList.get(i).getCourseID().toUpperCase());
    }
    System.out.println("Select Course: ");

    do {
        courseNumber = sc.nextInt();
        if(courseNumber<=0 || courseNumber>teacherCourseList.size())
        {
            System.out.println("Please Enter a valid Choice");
        }
        else flag=0;

    }while(flag!=0);
    String courseID = teacherCourseList.get(courseNumber-1).getCourseID();
    System.out.println("Selected Course: "+courseID.toUpperCase());
        return courseID;
}

    @Override
    public void modifyCourseTeacher() throws IOException {
            //get courseID whose teacher details are to be modified
            String courseID = getCourseAdmin();
            for(Course i:courseList)
            {
                if(!i.getCourseID().equals(courseID))
                {
                    continue;
                }
                else
                {
                    System.out.println("Current Teacher Name: "+ i.getCourseTeacher());
                    System.out.println("Enter New Name");
                    sc.nextLine();
                    int flag=1;
                    String newName;
                    do {
                        newName = sc.nextLine();
                        char firstVariable = newName.charAt(0);
                        if((int)firstVariable>=48 && (int)firstVariable<=57)
                        {
                            System.out.println("Name cannot Start with an Integer Value");
                        }

                        else if(newName.length()<3)
                            System.out.println("Name should have a minimum length of 3.");

                        else flag=0;
                    }while (flag!=0);
                    i.modifyCourseTeacher(newName);

                    System.out.println("Course Teacher Updated");
                    //updateDatabase(courseList);
                    return;
                }
            }
    }

    @Override
    public void iterateCourseList()
    {
            for(Course i:courseList)
            {
                System.out.println("Course ID: "+i.getCourseID().toUpperCase()+
                        "\nCourse Name: "+i.getCourseName()+
                        "\nCourse Teacher: "+i.getCourseTeacher());
                System.out.println("-------------------------------------------------");
            }
    }

    @Override
public Course addCourse() throws IOException {
        String courseID;
        String courseName;
        int courseTeacherID;
        int courseYear;
        int courseSemester;
        String firstInitial = "0", secondInitial = "0", semId, year= "0",semester;

        System.out.println("Enter Course Name");
        int courseNameFlag=1;

        do {
            courseName = sc.nextLine();
            if(courseName.length()<8 || courseName.length()>25)
                System.out.println("Course Name should be of minimum 8 and maximum 20 characters");
            else
            {
                String[] initials = courseName.split(" ");
                firstInitial = String.valueOf(initials[0].charAt(0));
                secondInitial = String.valueOf(initials[1].charAt(0));
                courseNameFlag=0;

            }
        }while (courseNameFlag!=0);
        courseNameFlag=1;

        do {
            System.out.println("Enter Course Teacher ID");
            courseTeacherID = sc.nextInt();
            if(valueOf(courseTeacherID).length() != 4)
                System.out.println("Teacher ID is Invalid. Please Try Again.");
            else
               courseNameFlag=0;

        }while (courseNameFlag!=0);
        courseNameFlag=1;

        do {

            System.out.println("Enter Course Year");
            courseYear = sc.nextInt();
            if(courseYear<0 || valueOf(courseYear).length()!=1)
                System.out.println("Invalid Course Year. Please Try Again.");
            else
            {
                year = String.valueOf(courseYear);
                courseNameFlag=0;
            }

        }while (courseNameFlag!=0);

        courseNameFlag=1;

        do {

            System.out.println("Enter Course Semester");
            courseSemester = sc.nextInt();
            if(courseSemester%2==0)

            {
                semId="0";
                semester = String.valueOf(courseSemester);
            }

            else
            {
                semId="1";
                semester = String.valueOf(courseSemester);
                courseNameFlag=0;
            }

        }while (courseNameFlag!=0);

        courseID = firstInitial + secondInitial +semId+ year +semester;
        courseID = courseID.toLowerCase();
        String courseTeacher = getCourseTeacherName(courseTeacherID);

        if(isEntryInvalid(courseID,courseTeacherID))
            System.out.println("Course Exists Already.");

        else
        {
            System.out.println("Course Information:" +
                    "\nCourse Name: "+courseName+
                    "\nCourse ID: "+courseID.toUpperCase()+
                    "\nCourse Teacher ID: "+courseTeacherID+
                    "\nCourse Year: "+courseYear+
                    "\nCourse Semester: "+courseSemester);
            Course newCourse = new Course(courseName,courseID,courseTeacher,courseTeacherID,courseYear,courseSemester);
            courseList.add(newCourse);
            //updateDatabase(courseList);
            return newCourse;
        }
    return null;
}

    @Override
    public void allotCourseToTeacher(String courseID, Teacher teacher)  {
           for(Course i: courseList)
           {
               if(i.getCourseID().equals(courseID))
               {
                   i.setCourseTeacher(teacher.getTeacherName());
                   i.setCourseTeacherID(teacher.getTeacherId());
               }

           }
           //updateDatabase(courseList);

            System.out.println("Course Teacher Updated.");
    }


    @Override
    public String changeCourseTitle(List<Course> allCourseList) throws IOException {
        int courseNumber, flag=1;
        System.out.println("Choose Course ID");
        for(int i = 0; i< allCourseList.size(); i++)
        {
            System.out.println((i+1)+") "+ allCourseList.get(i).getCourseName()+
                    "\nCourse ID: "+allCourseList.get(i).getCourseID().toUpperCase());
            System.out.println();
        }
        System.out.println("Select Course: ");

        do {
            courseNumber = sc.nextInt();
            if(courseNumber<=0 || courseNumber> allCourseList.size())
            {
                System.out.println("Please Enter a valid Choice");
            }
            else flag=0;

        }while(flag!=0);

        flag=1;

        String courseID = allCourseList.get(courseNumber-1).getCourseID();
        String courseName = allCourseList.get(courseNumber-1).getCourseName();
        System.out.println("Selected Course: "+courseID.toUpperCase());
        String newCourseName;

        do {
            System.out.println("Enter new Course Name");
            sc.nextLine();
            newCourseName = sc.nextLine();
            if(newCourseName.equals(courseName))
                System.out.println("Course Name is the same as previous name");
            else
                flag=0;

        }while(flag!=0);

        for(Course i:courseList)
        {
            if(i.getCourseName().equals(courseName) && i.getCourseID().equals(courseID))
            {
                i.setCourseName(newCourseName);
                //updateDatabase(courseList);
                return newCourseName;
            }
        }
        return null;
    }

    @Override
    public String deleteCourse() throws IOException {
           String courseID = getCourseAdmin();
           for(Course i: courseList)
           {
               if(i.getCourseID().equals(courseID))
               {
                   courseList.remove(i);
                   System.out.println("Course Removed.");
                   //updateDatabase(courseList);
                   return courseID;
               }
           }
           return null;
    }


    private boolean isEntryInvalid(String courseID, int teacherID)
{
    for(Course i:courseList)
    {
        if(i.getCourseTeacherID() == teacherID && i.getCourseID().equals(courseID))
            return true;
    }
    return false;
}
private String getCourseTeacherName(int teacherID)
{
    for(Course i:courseList)
    {
        if(i.getCourseTeacherID() == teacherID)
            return i.getCourseTeacher();
    }
    return null;
}

//private void updateDatabase(List<Course> updatedCourseList) throws IOException {
//    this.courseList = super.updateCourseList(updatedCourseList);
//}

}