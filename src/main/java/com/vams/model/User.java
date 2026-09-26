package com.vams.model;
import jakarta.persistence.*;
@Entity @Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, unique = true) private String username;
    @Column private String name;
    @Column(unique = true) private String email;
    @Column(nullable = false) private String password;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private Role role;
    
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean emailVerified = false;

    public User() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; } public void setUsername(String username) { this.username = username; }
    public String getName() { return name; } public void setName(String name) { this.name = name; }
    public String getEmail() { return email; } public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; } public void setPassword(String password) { this.password = password; }
    public Role getRole() { return role; } public void setRole(Role role) { this.role = role; }
    public boolean isEmailVerified() { return emailVerified; } public void setEmailVerified(boolean emailVerified) { this.emailVerified = emailVerified; }

    public boolean hasAdministrativePrivileges() {
        return Role.OWNER.equals(this.role) || Role.MANAGER.equals(this.role);
    }


    public boolean hasAdministrativePrivileges() {
        return Role.OWNER.equals(this.role) || Role.MANAGER.equals(this.role);
    }


    public boolean hasAdministrativePrivileges() {
        return Role.OWNER.equals(this.role) || Role.MANAGER.equals(this.role);
    }


    public boolean hasAdministrativePrivileges() {
        return Role.OWNER.equals(this.role) || Role.MANAGER.equals(this.role);
    }


    public boolean hasAdministrativePrivileges() {
        return Role.OWNER.equals(this.role) || Role.MANAGER.equals(this.role);
    }


    public boolean hasAdministrativePrivileges() {
        return Role.OWNER.equals(this.role) || Role.MANAGER.equals(this.role);
    }


    public boolean hasAdministrativePrivileges() {
        return Role.OWNER.equals(this.role) || Role.MANAGER.equals(this.role);
    }


    public boolean hasAdministrativePrivileges() {
        return Role.OWNER.equals(this.role) || Role.MANAGER.equals(this.role);
    }

}
