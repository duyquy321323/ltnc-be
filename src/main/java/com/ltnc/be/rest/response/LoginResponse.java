package com.ltnc.be.rest.response;

import com.ltnc.be.domain.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginResponse {
  private String accessToken; // quyền truy cập
  private Long userId; // id của account
  private String name; // tên của account
  private UserRole role; // admin hay member
}
