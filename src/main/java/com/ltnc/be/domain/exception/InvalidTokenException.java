package com.ltnc.be.domain.exception;

import com.ltnc.be.domain.BaseException;
import lombok.NonNull;

public class InvalidTokenException extends BaseException {
  private static final String MESSAGE = "The token is invalid";
  private static final String CODE = "INVALID_JWT_TOKEN_EXCEPTION";

  public InvalidTokenException(@NonNull String message, @NonNull String code) {
    super(message, code);
  }

  public InvalidTokenException() {
    super(MESSAGE, CODE);
  }

  public InvalidTokenException(@NonNull String message) {
    super(message, CODE);
  }
}
