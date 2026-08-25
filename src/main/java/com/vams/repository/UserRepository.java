package com.vams.repository; 
import com.vams.model.User; 
import org.springframework.data.jpa.repository.JpaRepository; 
import java.util.Optional; 
public interface UserRepository extends JpaRepository<User, Long> { 
    Optional<User> findByUsername(String username); 
    Optional<User> findByEmail(String email);

    @org.springframework.data.jpa.repository.Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username) OR LOWER(u.email) = LOWER(:email)")
    java.util.Optional<User> findByUsernameOrEmailIgnoreCase(String username, String email);


    @org.springframework.data.jpa.repository.Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username) OR LOWER(u.email) = LOWER(:email)")
    java.util.Optional<User> findByUsernameOrEmailIgnoreCase(String username, String email);


    @org.springframework.data.jpa.repository.Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username) OR LOWER(u.email) = LOWER(:email)")
    java.util.Optional<User> findByUsernameOrEmailIgnoreCase(String username, String email);


    @org.springframework.data.jpa.repository.Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username) OR LOWER(u.email) = LOWER(:email)")
    java.util.Optional<User> findByUsernameOrEmailIgnoreCase(String username, String email);


    @org.springframework.data.jpa.repository.Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username) OR LOWER(u.email) = LOWER(:email)")
    java.util.Optional<User> findByUsernameOrEmailIgnoreCase(String username, String email);


    @org.springframework.data.jpa.repository.Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username) OR LOWER(u.email) = LOWER(:email)")
    java.util.Optional<User> findByUsernameOrEmailIgnoreCase(String username, String email);


    @org.springframework.data.jpa.repository.Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username) OR LOWER(u.email) = LOWER(:email)")
    java.util.Optional<User> findByUsernameOrEmailIgnoreCase(String username, String email);


    @org.springframework.data.jpa.repository.Query("SELECT u FROM User u WHERE LOWER(u.username) = LOWER(:username) OR LOWER(u.email) = LOWER(:email)")
    java.util.Optional<User> findByUsernameOrEmailIgnoreCase(String username, String email);

}
