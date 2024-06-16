package com.ltnc.be.adapter.facade;

import com.ltnc.be.annotation.IsAuthorizedAsMember;
import com.ltnc.be.domain.exception.EntityAlreadyExistedException;
import com.ltnc.be.domain.exception.EntityNotFoundException;
import com.ltnc.be.domain.user.User;
import com.ltnc.be.dto.UserDTO;
import com.ltnc.be.port.facade.UserFacade;
import com.ltnc.be.port.repository.UserRepository;
import com.ltnc.be.rest.request.UpsertUserRequest;
import com.ltnc.be.rest.response.UserResponse;
import com.ltnc.be.rest.security.SecurityUserService;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@RequiredArgsConstructor
@Service
public class UserFacadeImpl implements UserFacade {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  // TODO: These are example methods, you should implement them by yourself
  // ******************************************
  @Override
  public List<UserResponse> getUsers() {
    var users = this.userRepository.findAll();
    if (CollectionUtils.isEmpty(users)) return Collections.emptyList();
    var userDTOs = users.stream().map(UserDTO::fromDomain).toList();

    return userDTOs.stream().map(UserResponse::toUserResponse).toList();
  }

  @SneakyThrows
  @Override
  public UserResponse getUser(Long userId) {
    var user = this.userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    var userDTO = UserDTO.fromDomain(user);

    return UserResponse.toUserResponse(userDTO);
  }

  @SneakyThrows
  @Transactional
  @Override
  public void createUser(UpsertUserRequest request) {
    var userOpt = this.userRepository.findByUsername(request.getUsername());
    if (userOpt.isPresent()) throw new EntityAlreadyExistedException();

    this.userRepository.save(
        User.createMember(request.getUsername(), passwordEncoder.encode(request.getPassword())));
  }

  @SneakyThrows
  @Override
  @IsAuthorizedAsMember
  public UserResponse getContextUser() {
    User user = SecurityUserService.getCurrentUser();
    var userDTO = UserDTO.fromDomain(user);

    return UserResponse.toUserResponse(userDTO);
  }
  // ******************************************
}
