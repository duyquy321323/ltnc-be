package com.ltnc.be.dto;

import com.ltnc.be.domain.user.User;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
  private Long userId;
  private String userRole;
  private String userEmail;
  private Date userDob;
  private String userPhoneNumber;
  private String name;

  public static UserDTO fromDomain(User user) {
    return UserDTO.builder()
        .userId(user.getId())
        .userRole(user.getRole().name())
        .userEmail(user.getEmail())
        .userDob(user.getDob())
        .userPhoneNumber(user.getPhoneNumber())
            .name(user.getFullName())
        .build();
  }
}
