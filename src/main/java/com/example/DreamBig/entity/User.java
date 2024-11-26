package com.example.DreamBig.entity;

import com.example.DreamBig.config.RolePrivilegeConfig;
import com.example.DreamBig.service.implementations.ValidationServiceImpl;
import com.example.DreamBig.service.interfaces.ValidationService;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorColumn(name = "user_type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String phoneNumber;

    private String email;

    private String password;

    private String role;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_privileges", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "privilege")
    private Set<String> privileges;

    private boolean isPhoneNumberValid;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Subscription> subscriptions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Payment> payments;

    @ManyToMany(mappedBy = "participants")
    private List<Session> sessions;

    @Transient
    private final ValidationService validationService = new ValidationServiceImpl();

    public User() {}

    public User(Long id, String fullName, String phoneNumber, String email, String password, String role) {
        if (!validationService.isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
        if (!validationService.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }

        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.role = role;
        this.privileges = new HashSet<>(getPrivilegesByRole(role));
    }

    private Set<String> getPrivilegesByRole(String role) {
        return switch (role.toUpperCase()) {
            case "USER" -> RolePrivilegeConfig.getUserPrivileges();
            case "TRAINER" -> RolePrivilegeConfig.getTrainerPrivileges();
            case "ADMIN" -> RolePrivilegeConfig.getAdminPrivileges();
            default -> throw new IllegalArgumentException("Unknown role: " + role);
        };
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (fullName == null || fullName.isEmpty()) {
            throw new IllegalArgumentException("Full name cannot be empty");
        }
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (!validationService.isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!validationService.isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        if (role == null || role.isEmpty()) {
            throw new IllegalArgumentException("Role cannot be empty");
        }
        this.role = role;
    }

    public Set<String> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<String> privileges) {
        this.privileges = privileges;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public boolean isPhoneNumberValid() {
        return isPhoneNumberValid;
    }

    public void setPhoneNumberValid(boolean isPhoneNumberValid) {
        this.isPhoneNumberValid = isPhoneNumberValid;
    }
}
