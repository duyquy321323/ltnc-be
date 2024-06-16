//package com.ltnc.be.config;
//
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class StorageConfig {
//  @Value("${amazon.s3.access.key.id}")
//  private String s3AccessKeyId;
//
//  @Value("${amazon.s3.access.key.secret}")
//  private String s3AccessKeySecret;
//
//  @Value("${amazon.s3.region.name}")
//  private String s3RegionName;
//
//  @Bean
//  public AmazonS3 amazonS3() {
//    return AmazonS3ClientBuilder.standard()
//        .withCredentials(
//            new AWSStaticCredentialsProvider(
//                new BasicAWSCredentials(s3AccessKeyId, s3AccessKeySecret)))
//        .withRegion(s3RegionName)
//        .build();
//  }
//}
