package com.Mind_Forge_SeatFlix.SeatFlix.Users;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

                                                                                                                        // Users class with private fields
                                                                                                                        //Map Users Class to Database

@Entity
@Table(name = "users")
public class Users implements UserDetails
 {
    @Id
    @SequenceGenerator(name = "Users_Sequence", sequenceName = "Users_Sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Users_Sequence")

    private Long id;
    private String username;
    private String password;
    private String email;
    private static int numOfUsers = 0;                                                                                     // Static int to track the number of Users our platform has

    @Version
    private Integer version;                                                                                               // Make sure entity has a @Version field for optimistic locking.

    public Users() {
        numOfUsers++;
    }                                                                                                                      // No args Constructor increases numOfUsers Each time an account is created
                                                                                                                           // Required by Spring JPA/Hibernate 

    public Users(Long id, String username, String password, String email) {                                                // Constructor including id field
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        numOfUsers++;
    }

    public Users(String username, String password, String email) {                                                          // Constructor excluding id field. Useful for creating new users DB auto generates ID
        this.username = username;
        this.password = password;
        this.email = email;
        numOfUsers++;
    }

                                                                                                                           // Generated Getters and Setters for the fields
    public Long getId() {return id;}

    public String getEmail() {return email;}

    public static int getNumOfUsers() {return numOfUsers;}

    public void setId(Long id) {this.id = id;}

    public void setUsername(String username) {this.username = username;}

    public void setPassword(String rawPassword) {this.password = new BCryptPasswordEncoder().encode(rawPassword); }

    public void setEmail(String email) {this.email = email;}


                                                                                                                        // fields required by User Details Interface for Authentification
     @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();                                                                                 // No roles for now  Ex: [ADMIN  USER  BANNED]
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
                                                                                                                        // Account configuration status
    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}

