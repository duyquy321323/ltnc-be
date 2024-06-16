//package com.ltnc.be.adapter.service;
//
//import com.amazonaws.HttpMethod;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
//import com.amazonaws.services.s3.model.ObjectMetadata;
//import com.amazonaws.services.s3.model.PutObjectRequest;
//import com.ltnc.be.domain.common.StorageContentType;
//import com.ltnc.be.domain.common.StorageDomain;
//import com.ltnc.be.domain.common.StoragePresignedMethod;
//import com.ltnc.be.domain.exception.ExternalServiceException;
//import com.ltnc.be.port.service.StorageService;
//import java.io.ByteArrayInputStream;
//import java.net.URLConnection;
//import java.time.Instant;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.UUID;
//import lombok.RequiredArgsConstructor;
//import lombok.SneakyThrows;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.util.Pair;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class StorageServiceImpl implements StorageService {
//  @Value("${amazon.s3.presigned.url.expiration}")
//  private Long amazonS3PresignedUrlExpiration;
//
//  @Value("${amazon.s3.bucket.name}")
//  private String amazonS3BucketName;
//
//  @Value("${amazon.s3.public.url}")
//  private String amazonS3PublicUrl;
//
//  @Autowired
//  private final AmazonS3 amazonS3;
//
//  @Override
//  public Pair<String, String> generatePresignedUrl(
//      StorageDomain storageDomain, StorageContentType storageContentType) {
//    var key = buildPresignedUrlKey(storageDomain);
//    var url = buildPresignedUrl(key, storageContentType);
//    return Pair.of(key, url);
//  }
//
//  @Override
//  public Pair<String, String> generatePublicPresignedUrl(StorageContentType storageContentType) {
//    var key = buildPresignedUrlKey();
//    var url = buildPresignedUrl(key, storageContentType);
//    return Pair.of(key, url);
//  }
//
//  @Override
//  public String getPresignedUrl(String key) {
//    return buildPresignedUrl(key);
//  }
//
//  @Override
//  public String getPublicPresignedUrl(String key) {
//    return buildPresignedUrlKey(key);
//  }
//
//  @Override
//  @SneakyThrows
//  public String uploadFile(byte[] file, StorageDomain storageDomain) {
//    var key = buildPresignedUrlKey(storageDomain);
//    ByteArrayInputStream fileInputStream = new ByteArrayInputStream(file);
//    String contentType = URLConnection.guessContentTypeFromStream(fileInputStream);
//    if (contentType == null) contentType = MediaType.APPLICATION_PDF_VALUE;
//    try {
//      ObjectMetadata metadata = new ObjectMetadata();
//      metadata.setContentLength(file.length);
//      metadata.setContentType(contentType);
//
//      amazonS3.putObject(new PutObjectRequest(amazonS3BucketName, key, fileInputStream, metadata));
//    } catch (Exception e) {
//      fileInputStream.close();
//      throw new ExternalServiceException();
//    }
//    fileInputStream.close();
//    return key;
//  }
//
//  private String buildPresignedUrl(String key) {
//    return amazonS3
//        .generatePresignedUrl(
//            amazonS3BucketName,
//            key,
//            buildPresignedUrlExpiration(),
//            HttpMethod.valueOf(StoragePresignedMethod.GET.name()))
//        .toString();
//  }
//
//  private String buildPresignedUrl(String key, StorageContentType storageContentType) {
//    return amazonS3
//        .generatePresignedUrl(
//            new GeneratePresignedUrlRequest(amazonS3BucketName, key)
//                .withContentType(storageContentType.getContentType())
//                .withExpiration(buildPresignedUrlExpiration())
//                .withMethod(HttpMethod.valueOf(StoragePresignedMethod.PUT.name())))
//        .toString();
//  }
//
//  private Date buildPresignedUrlExpiration() {
//    var calendar = Calendar.getInstance();
//    calendar.setTimeInMillis(Instant.now().toEpochMilli() + amazonS3PresignedUrlExpiration);
//    return calendar.getTime();
//  }
//
//  private String buildPresignedUrlKey(StorageDomain storageDomain) {
//    return String.format("%s/%s", storageDomain.getCaption(), UUID.randomUUID());
//  }
//
//  private String buildPresignedUrlKey() {
//    return String.format("public/%s", UUID.randomUUID());
//  }
//
//  private String buildPresignedUrlKey(String key) {
//    return String.format("%s/%s", amazonS3PublicUrl, key);
//  }
//}
