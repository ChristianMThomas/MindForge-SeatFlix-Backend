 package com.Mind_Forge_SeatFlix.SeatFlix.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface Users_Repository extends JpaRepository <Users, Long> {
    @Query("SELECT u FROM Users u WHERE u.email = ?1")                                                              //Retrieves a User enitity from DB based on email
    Optional<Users> findUserByEmail(String email); 
    Optional<Users> findUserByUsername(String username) ;
                                                                          //The method returns an Optional(may or maynot contain value)




 }

    

