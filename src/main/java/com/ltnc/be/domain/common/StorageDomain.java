package com.ltnc.be.domain.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum StorageDomain implements DescriptiveEnum {
  USER("user");

  private final String caption;

  @Override
  public String getName() {
    return this.name();
  }

  @Override
  public String getCaption() {
    return this.caption;
  }
}
