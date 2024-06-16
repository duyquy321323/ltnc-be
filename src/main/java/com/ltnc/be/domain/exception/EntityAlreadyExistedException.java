package com.ltnc.be.domain.exception;

import com.ltnc.be.domain.BaseException;
import lombok.NonNull;

public class EntityAlreadyExistedException extends BaseException {
  private static final String MESSAGE = "Entity is already existed in the system";
  private static final String CODE = "ENTITY_ALREADY_EXISTED_EXCEPTION";

  public EntityAlreadyExistedException(@NonNull String message, @NonNull String code) {
    super(message, code);
  }

  public EntityAlreadyExistedException() {
    super(MESSAGE, CODE);
  }

  public EntityAlreadyExistedException(@NonNull String message) {
    super(message, CODE);
  }
}
