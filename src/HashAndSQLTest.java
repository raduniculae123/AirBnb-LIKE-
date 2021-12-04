package com2008project;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HashAndSQLTest {

    ///sql password check
    public static String passwordcheck(String password)
    {

        String regEx="^[A-Za-z0-9@_]+$";
        String okpassword = " ";
        Pattern p=Pattern.compile(regEx);
        Matcher m=p.matcher(password);
        if(!m.matches()) {
            System.out.println("password ' " + password + " ' is Illegal input ");
        }
        else{
            System.out.println(password + " OK!");
            okpassword = password;
        }
        return okpassword;
    }


     ///Hash the password
    public static byte[] salt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    public static byte[] hashGeneration(String password, byte[] salt) {

        byte[] hashpassword = null;
        try {
            MessageDigest MD = MessageDigest.getInstance("SHA-512");
            MD.update(salt);
            hashpassword = MD.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } finally {
            return hashpassword;
        }
    }

    public static void main(String[] args) {
        String password = "123sdjfsc@";
        String badpassword = "qwe张三%^^(";
        String email = "qwer@gmail.com";
        byte[] salt = HashAndSQLTest.salt();
        System.out.println(HashAndSQLTest.hashGeneration(password, salt));
        System.out.println(HashAndSQLTest.passwordcheck(password));
        System.out.println(HashAndSQLTest.passwordcheck(badpassword));

    }


}
