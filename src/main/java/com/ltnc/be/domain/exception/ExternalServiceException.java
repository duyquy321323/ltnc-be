package com.ltnc.be.domain.exception;

import com.ltnc.be.domain.BaseException;
import lombok.NonNull;

public class ExternalServiceException extends BaseException {
  private static final String MESSAGE = "The exception occurs when trying to use external service";
  private static final String CODE = "EXTERNAL_SERVICE_EXCEPTION";

  public ExternalServiceException(@NonNull String message, @NonNull String code) {
    super(message, code);
  }

  public ExternalServiceException() {
    super(MESSAGE, CODE);
  }

  public ExternalServiceException(@NonNull String message) {
    super(message, CODE);
  }
}
