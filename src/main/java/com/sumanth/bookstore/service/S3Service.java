package com.sumanth.bookstore.service;

import java.io.IOException;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3Service {
  private final S3Client s3Client;

  public S3Service() {
    this.s3Client = S3Client.builder().region(Region.YOUR_REGION).build();
  }

  public String uploadFile(MultipartFile file) throws IOException {
    String key = UUID.randomUUID() + "-" + file.getOriginalFilename();
    s3Client.putObject(
        PutObjectRequest.builder().bucket("your-bucket").key(key).build(),
        RequestBody.fromBytes(file.getBytes())
    );
    return key; // or construct the S3 URL
  }
}
