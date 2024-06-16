package com.ltnc.be.rest.security;

import com.ltnc.be.domain.exception.PermissionDeniedException;
import com.ltnc.be.domain.user.User;
import com.ltnc.be.port.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // Bean Service
@RequiredArgsConstructor // hàm contructor cho các trường bắt buộc chưa những từ khóa như là final
// hoặc có annotation @NonNull
public class SecurityUserService implements UserDetailsService {
  private final UserRepository userRepository; // lấy bean có thể thao tác với csdl

  private static final String ANONYMOUS_USER = "anonymousUser"; // biến người ẩn danh

  @SneakyThrows // từ động bắt ngoại lệ và xử lý ngoại lệ
  public static User getCurrentUser() {
    var principal =
        SecurityContextHolder.getContext()
            .getAuthentication()
            .getPrincipal(); // trả về người dùng hiện tại dạng UserDetails
    if (ANONYMOUS_USER.equals(principal))
      throw new PermissionDeniedException(); // nếu người dùng hiện tại là ẩn danh thì ném ngoại lệ
    var securityUser = (SecurityUser) principal; // chuyển object về SecurityUser

    return securityUser.getUser(); // lấy người dùng (User)
  }

  @Override
  public UserDetails loadUserByUsername(String id)
      throws UsernameNotFoundException { // lấy userdetail ra từ csdl bằng id
    var user =
        this.userRepository
            .findById(Long.parseLong(id))
            .orElseThrow(() -> new UsernameNotFoundException(id + " is not found"));
    return SecurityUser.build(user);
  }
}
