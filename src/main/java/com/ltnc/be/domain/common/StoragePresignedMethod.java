package com.ltnc.be.domain.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum StoragePresignedMethod {
  GET("getObject"),
  PUT("putObject");

  private final String method;
}
