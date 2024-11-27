package com.example.DreamBig.entity;

import com.example.DreamBig.config.RolePrivilegeConfig;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorColumn(name = "user_type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

    public Set<String> getPrivilegesByRole(String role) {
        return switch (role.toUpperCase()) {
            case "USER" -> RolePrivilegeConfig.getUserPrivileges();
            case "TRAINER" -> RolePrivilegeConfig.getTrainerPrivileges();
            case "ADMIN" -> RolePrivilegeConfig.getAdminPrivileges();
            default -> throw new IllegalArgumentException("Unknown role: " + role);
        };
    }
}
