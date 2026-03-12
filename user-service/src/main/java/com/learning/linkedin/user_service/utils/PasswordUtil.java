package com.learning.linkedin.user_service.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordUtil {
    
    public static String hashPassword(String plainTestPass){
        return BCrypt.hashpw(plainTestPass, BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainTestPass, String hashedPassword){
        return BCrypt.checkpw(plainTestPass,hashedPassword);
    }

    
}
