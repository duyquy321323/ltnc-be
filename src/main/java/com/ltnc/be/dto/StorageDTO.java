package com.ltnc.be.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StorageDTO {
  private String key;
  private String url;

  public static StorageDTO from(String key, String url) {
    return StorageDTO.builder().key(key).url(url).build();
  }
}
