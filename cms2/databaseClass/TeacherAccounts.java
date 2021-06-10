package databaseClass;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;

public class TeacherAccounts {

    private final String teacherAccountDB = "D:\\Zoho\\Course Management System\\src\\database\\TeacherAccounts.csv";
    private String[] userDetails;
    protected boolean isAccountValid(String userID, String password) throws IOException, CsvValidationException {
        CSVReader reader = new CSVReaderBuilder(new FileReader(teacherAccountDB)).withSkipLines(1).build();
//        CSVReader reader = new CSVReader(new FileReader(adminAccountDB));

        while((userDetails = reader.readNext())!=null)
        {
            if(userDetails[1].equals(userID) && userDetails[2].equals(password))
            {
                reader.close();
                return true;
//                break;
            }
        }
        reader.close();
        return false;
    }

    protected String[] getTeacher()
    {
        String[] teacherDetails= new String[2];
        teacherDetails[0]=userDetails[0];
        teacherDetails[1]=userDetails[1];
        return teacherDetails;
    }
}
