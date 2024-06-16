package com.ltnc.be.port.facade;

import com.ltnc.be.rest.request.UpsertUserRequest;
import com.ltnc.be.rest.response.UserResponse;
import java.util.List;

public interface UserFacade {
  List<UserResponse> getUsers();

  UserResponse getUser(Long userId);

  void createUser(UpsertUserRequest request);

  UserResponse getContextUser();
}
