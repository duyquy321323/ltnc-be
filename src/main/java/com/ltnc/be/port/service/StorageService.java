package com.ltnc.be.port.service;

import com.ltnc.be.domain.common.StorageContentType;
import com.ltnc.be.domain.common.StorageDomain;
import org.springframework.data.util.Pair;

public interface StorageService {
  Pair<String, String> generatePresignedUrl(
      StorageDomain storageDomain, StorageContentType storageContentType);

  Pair<String, String> generatePublicPresignedUrl(StorageContentType storageContentType);

  String getPresignedUrl(String key);

  String getPublicPresignedUrl(String key);

  String uploadFile(byte[] file, StorageDomain storageDomain);
}
