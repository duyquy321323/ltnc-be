package com.ltnc.be.domain.exception;

import com.ltnc.be.domain.BaseException;
import lombok.NonNull;

public class PermissionDeniedException extends BaseException {
  private static final String MESSAGE =
      "The current user credential does not have the permission to access resources";
  private static final String CODE = "PERMISSION_DENIED_EXCEPTION";

  public PermissionDeniedException(@NonNull String message, @NonNull String code) {
    super(message, code);
  }

  public PermissionDeniedException() {
    super(MESSAGE, CODE);
  }

  public PermissionDeniedException(@NonNull String message) {
    super(message, CODE);
  }
}
