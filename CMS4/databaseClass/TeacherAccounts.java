package databaseClass;
import teacher.Teacher;
import helper.PasswordHelper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TeacherAccounts {
    private final String teacherAccountDB = "src\\database\\TeacherAccounts.csv";
    private static List<Teacher> teacherAccounts = new ArrayList<>();
    private boolean isDatabaseInitiated = false;

    private PasswordHelper passwordHelper = new PasswordHelper();

    public void initializeTeacherAccounts() throws IOException {
        File inFile = new File(teacherAccountDB);
        Scanner inputFile = new Scanner(inFile);
        inputFile.nextLine();
        List<Teacher> tempTeacherList = new ArrayList<>();
        String str;
        String[] tokens;
        while (inputFile.hasNext()) {
            str = passwordHelper.decryptPassword(inputFile.nextLine());         // read a line of text from the file
            tokens = str.split(",");            // split the line using commas as delimiter
            tokens[2] = passwordHelper.encryptPassword(tokens[2]);
            Teacher teacher = new Teacher(tokens[0], Integer.parseInt(tokens[1]), tokens[2]);
            tempTeacherList.add(teacher);
        }
        teacherAccounts = tempTeacherList;
    }

    public List<Teacher> updateTeacherAccounts(List<Teacher> updatedTeacherList) throws IOException {
        FileWriter csvWriter = new FileWriter(teacherAccountDB);
        csvWriter.write("TeacherName,TeacherID,Password\n");

        for (int i = 0; i < updatedTeacherList.size(); i++) {
            List<String> items = new ArrayList<>();
            items.add(updatedTeacherList.get(i).getTeacherName());
            items.add(String.valueOf(updatedTeacherList.get(i).getTeacherId()));
            items.add(passwordHelper.decryptPassword(updatedTeacherList.get(i).getTeacherPin()));
            String item = items.stream().collect(Collectors.joining(","));
            item = passwordHelper.encryptPassword(item);
            csvWriter.write(item + "\n");
        }

        csvWriter.close();
        initializeTeacherAccounts();
        return teacherAccounts;

    }
        protected boolean isTeacherValid(Teacher teacher)
        {
            for(Teacher i:teacherAccounts)
            {
                if(i.getTeacherId() == teacher.getTeacherId())
                    return true;
            }
            return false;
        }

    protected List<Teacher> getTeacherList()
    {
        return teacherAccounts;
    }
}
