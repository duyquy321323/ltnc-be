package com.ltnc.be.domain.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum StorageContentType implements DescriptiveEnum {
  PDF("application/pdf"),
  RAR("application/vnd.rar"),
  ZIP("application/zip"),
  COMPRESSED_ZIP("application/x-zip-compressed"),
  JPEG("image/jpeg"),
  PNG("image/png"),
  CSV("text/csv"),
  EXCEL("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
  WORD("application/msword"),
  PPT("application/vnd.ms-powerpoint");

  private final String contentType;

  @Override
  public String getName() {
    return this.name();
  }

  @Override
  public String getCaption() {
    return this.contentType;
  }
}
