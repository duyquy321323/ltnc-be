package com.ltnc.be.domain.user;

import com.ltnc.be.domain.BaseEntity;
import jakarta.persistence.*;
import java.util.Date;

import lombok.*;

@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User extends BaseEntity {
  @Enumerated(EnumType.STRING)
  @Column(name = "role", nullable = false)
  private UserRole role;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "email")
  private String email;

  @Column(name = "gender")
  private String gender;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "dob")
  @Temporal(TemporalType.DATE)
  private Date dob;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "address")
  private String address;

  public boolean isMember() {
    return UserRole.MEMBER.equals(this.role);
  }

  public boolean isAdmin() {
    return UserRole.ADMIN.equals(this.role);
  }

  private User(String email, String password, UserRole role) {
    this.email = email;
    this.password = password;
    this.role = role;
  }

  public static User createMember(String username, String password) {
    return new User(username, password, UserRole.MEMBER);
  }

  public static User createAdmin(String username, String password) {
    return new User(username, password, UserRole.ADMIN);
  }
}
