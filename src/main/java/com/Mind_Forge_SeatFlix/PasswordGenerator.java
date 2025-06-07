package com.Mind_Forge_SeatFlix;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("Password"); // Replace with your desired password
        System.out.println(hashedPassword);
    }
}

