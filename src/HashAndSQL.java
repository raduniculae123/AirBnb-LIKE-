package com2008project;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashAndSQL {

    /**
     * SQL Generation
     */

    private static char characterChecker(char characters){

        int i = (int)characters;

        if((i>63 && i<91) || (i>47 && i<58) || (i>96 && i<123) || i == 46 || i == 32) {
            return characters;
        }else{
            System.out.println("* Warning: The password can only contain numbers and English letters. *");
            return ' ';
        }

    }

    public static String generalValidation(String inputPassword){

        String outputPassword = "";

        for(int i = 0; i < inputPassword.length(); i++){
            outputPassword += characterChecker(inputPassword.charAt(i));
        }

        return outputPassword;
    }


    /**
     * Hash the password
     */

    public static byte[] saltGeneration() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }

    public static byte[] hashGeneration(String password, byte[] salt) {

        byte[] hashed = null;
        try {
            MessageDigest MD = MessageDigest.getInstance("SHA-512");
            MD.update(salt);
            hashed = MD.digest(password.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } finally {
            return hashed;
        }
    }

    public static void main(String[] args) {
        String inputpassword = "qwer112233@";
        String wrongpassword = "q!!张();;";
        String email = "qwer@gmail.com";
        email = HashAndSQL.generalValidation(email);
        byte[] salt = HashAndSQL.saltGeneration();
        System.out.println(HashAndSQL.hashGeneration(inputpassword, salt));
        System.out.println(HashAndSQL.generalValidation(inputpassword));
        System.out.println(HashAndSQL.generalValidation(wrongpassword));

    }


}
