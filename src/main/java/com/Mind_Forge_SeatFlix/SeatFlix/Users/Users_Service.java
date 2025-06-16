package com.Mind_Forge_SeatFlix.SeatFlix.Users;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

                                                                                            // Service that acts between Controller and Repository that handles Buisness Logic
@Service
public class Users_Service {

    private final Users_Repository users_Repository; 
    private final BCryptPasswordEncoder passwordEncoder;
                                                                                          // Instance of Repository for usage
    public Users_Service(Users_Repository users_Repository, BCryptPasswordEncoder passwordEncoder){                                // Constructor that has Repo param
        this.users_Repository = users_Repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List <Users> getUsers(){                                                         // Method used to call FindAll() from Repo to fetch user records from DB
        return users_Repository.findAll();
    }

                                                                                            // Method to adding new Users to DB  
                                                                                            /* First checks if there is already a user with the email that exist
                                                                                                    If (user with email exist ) then
                                                                                                    Throws IllegalStateException with Message
                                                                                                else 
                                                                                                    hash users password and  save user to DB
                                                                                           */ 
    public void addNewUsers(Users users) {
        Optional<Users> usersOptional = users_Repository
                .findUserByEmail(users.getEmail());
        if (usersOptional.isPresent()) {
            throw new IllegalStateException("Email has been taken");
        }
         String hashedPassword = passwordEncoder.encode(users.getPassword());
         users.setPassword(hashedPassword);                                                  // Hash password before saving
         users_Repository.save(users);
    }
                                                                                            // Method to deleting Users from DB  
                                                                                            /* First checks if there is a user with the id that exist
                                                                                                    If (user with id does not exist ) then
                                                                                                    Throws IllegalStateException with Message
                                                                                                else 
                                                                                                    deletes user from DB
                                                                                           */ 
    public void deleteUser(Long id){                                                        
        boolean exist = users_Repository.existsById(id);
        if(!exist){
            throw new IllegalStateException("User with id " + id + " does not exist!");
        }
        users_Repository.deleteById(id);
    }

    @Transactional
    public void updateUser(Long id, String name, String email){
        Users user = users_Repository
        .findById(id)
        .orElseThrow(() -> new IllegalStateException(
        ("user with id" + id + " does not exist")));

       if (name != null && name.length() > 0 && !Objects.equals(user.getUsername(), name)) {
        user.setUsername(name);
       }
       if (email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) {
        Optional<Users> employeeOptional = users_Repository.findUserByEmail(email);
            if (employeeOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
        user.setEmail(email);
       }
    }

    public void updateService(Long id, String name, String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateService'");
    } 
    
    
    public Users findUserByUsername(String username) {
        return users_Repository.findUserByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Users getUserById(Long id) {
        return users_Repository.findById(id).orElse(null);
}


} 