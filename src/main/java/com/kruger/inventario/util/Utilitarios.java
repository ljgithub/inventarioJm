package com.kruger.inventario.util;

public class Utilitarios {

    //Generate method to obtain random username
    public static String generateUsername() {
        String username = "";
        int randomNumber = (int) (Math.random() * 1000000);
        username = "user" + randomNumber;
        return username;
    }

    public static String generatePassword() {
        String password = "";
        for (int i = 0; i < 8; i++) {
            password += (char) (Math.random() * 26 + 'a');
        }
        return password;
    }
}
