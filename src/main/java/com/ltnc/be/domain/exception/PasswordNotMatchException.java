package com.ltnc.be.domain.exception;

import com.ltnc.be.domain.BaseException;
import lombok.NonNull;

public class PasswordNotMatchException extends BaseException {
  private static final String MESSAGE = "The password doesn't match";
  private static final String CODE = "PASSWORD_NOT_MATCH_EXCEPTION";

  public PasswordNotMatchException(@NonNull String message, @NonNull String code) {
    super(message, code);
  }

  public PasswordNotMatchException() {
    super(MESSAGE, CODE);
  }

  public PasswordNotMatchException(@NonNull String message) {
    super(message, CODE);
  }
}
