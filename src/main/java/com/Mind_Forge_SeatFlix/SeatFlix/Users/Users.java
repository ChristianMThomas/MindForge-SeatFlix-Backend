package com.Mind_Forge_SeatFlix.SeatFlix.Users;


// Users class with private fields
public class Users {
    private Long id;
    private String username;
    private String password;
    private String email;

    private static int numOfUsers = 0; // Static int to track the number of Users our platform has

    public Users(){ numOfUsers++;}   // No args Constructor

    public Users (Long id, String username, String password, String email){
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        numOfUsers++;
    }

     public Users (String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
        numOfUsers++;
    }

    
/*
 * Generated Getters and Setters for the fields
 */
     public Long getId() {
         return id;
     }

     public String getUsername() {
         return username;
     }

     public String getPassword() {
         return password;
     }

     public String getEmail() {
         return email;
     }

     public static int getNumOfUsers() {
         return numOfUsers;
     }
    
    public void setId(Long id) {
        this.id = id;
    }

     public void setUsername(String username) {
         this.username = username;
     }

     public void setPassword(String password) {
         this.password = password;
     }

     public void setEmail(String email) {
         this.email = email;
     }

    
}
