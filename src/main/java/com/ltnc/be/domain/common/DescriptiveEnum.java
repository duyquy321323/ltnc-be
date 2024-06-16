package com.ltnc.be.domain.common;

public interface DescriptiveEnum {
  String getName();

  String getCaption();

  default Boolean isDeprecated() {
    return false;
  }
}
