package interfaces;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;

public interface LoginInterface {
    void welcome() throws IOException, CsvValidationException;
    
    boolean isAccountValid(String id, String password) throws IOException, CsvValidationException;

}
