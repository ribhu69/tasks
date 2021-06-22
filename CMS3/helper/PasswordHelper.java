package helper;

public class PasswordHelper {
    private static int cipherKey = 95127534;
    public  String encryptPassword(String password)
    {
        char[] cipher = password.toCharArray();
        String cipherValue="";
        for(int i=0;i<password.length();i++)
        {
            cipherValue = cipherValue + (char)((int)cipher[i] ^ cipherKey);
        }
        return cipherValue;
    }

    public String decryptPassword(String password)
    {
        char[] cipher = password.toCharArray();
        String cipherValue="";
        for(int i=0;i<password.length();i++)
        {
            cipherValue = cipherValue + (char)((int)cipher[i] ^ cipherKey);
        }
        return cipherValue;
    }
}
