package com.ltnc.be.rest.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ltnc.be.domain.user.User;
import com.ltnc.be.domain.user.UserRole;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter // Tạo các Getter cho các thuộc tính
@AllArgsConstructor // tạo contructor của tất cả các thuộc tính
@NoArgsConstructor // tạo contructor rỗng
public class SecurityUser implements UserDetails { // phân quyền người dùng
  private Long id; // id của người dùng

  @JsonIgnore
  private String password; // password người dùng không được trả về json khi response cho client

  private Collection<? extends GrantedAuthority> authorities; // tập hợp các role của người dùng
  private User user; // người dùng

  private static final String ROLE_PREFIX = "ROLE_%s"; // tiền tố của role

  public static SecurityUser build(User user) { // hàm tạo 1 SecurityUser từ User
    final List<GrantedAuthority> authorities = new LinkedList<>(); // List role của người dùng
    if (user.isAdmin())
      authorities.add(buildRole(UserRole.ADMIN.name())); // nếu là admin thì thêm quyền admin vào
    else if (user.isMember())
      authorities.add(buildRole(UserRole.MEMBER.name())); // nếu là member thì thêm quyền member vào

    return new SecurityUser(
        user.getId(),
        user.getPassword(),
        authorities,
        user); // dùng hàm tạo full đối tượng tạo 1 SecurityUser
  }

  private static GrantedAuthority buildRole(String role) { // hàm tạo 1 quyền từ String role
    return new SimpleGrantedAuthority(
        String.format(ROLE_PREFIX, role)); // tạo mới 1 đơn quyền từ String
  }

  @Override
  public Collection<? extends GrantedAuthority>
      getAuthorities() { // lấy tập hợp các role của người dùng
    return this.authorities;
  }

  @Override
  public String getPassword() { // lấy mật khẩu
    return this.password;
  }

  @Override
  public String getUsername() {
    return String.valueOf(this.id); // lấy id
  }

  @Override
  public boolean isAccountNonExpired() { // kiểm tra tài khoản hết hạn chưa
    return true;
  }

  @Override
  public boolean isAccountNonLocked() { // kiểm tra xem tài khoản có bị khóa hay không
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() { // kiểm tra thông tin đăng nhập có hết hạn chưa
    return true;
  }

  @Override
  public boolean isEnabled() { // kiểm tra xem người dùng có được kích hoạt hay không
    return true;
  }
}
