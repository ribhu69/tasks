package databaseClass;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;

public class AdminAccounts {

    private final String adminAccountDB = "D:\\Zoho\\Course Management System\\src\\database\\AdminAccounts.csv";
    private String[] userDetails;
    protected boolean isAccountValid(String userID, String password) throws IOException, CsvValidationException {
        CSVReader reader = new CSVReaderBuilder(new FileReader(adminAccountDB)).withSkipLines(1).build();

        while((userDetails = reader.readNext())!=null)
        {
            if(userDetails[1].equals(userID) && userDetails[2].equals(password))
            {
                System.out.println("YES");
                reader.close();
                return true;

            }
        }
        reader.close();
        return false;
    }

    protected String[] getAdmin()
    {
        String[] adminDetails= new String[2];
        adminDetails[0]=userDetails[0];
        adminDetails[1]=userDetails[1];
        return adminDetails;
    }
}
