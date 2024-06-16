package com.ltnc.be.rest.response;

import java.util.Collections;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
public class BaseResponse<T> {
  private T data; // request
  private String message; // tin nhắn trả về sau khi response
  private String stackTrace; //
  private String exceptionCode;

  protected static final String SUCCEED_REQUEST_MESSAGE = "Success";

  public static <T> BaseResponse<T> of(T data) {
    return (BaseResponse<T>)
        BaseResponse.builder()
            .data(data)
            .message(SUCCEED_REQUEST_MESSAGE)
            .exceptionCode(null)
            .stackTrace(null)
            .build(); // gọi các phương thức trên 1 dòng
  }

  public static <T> BaseResponse<T> ok() { // thành công
    return (BaseResponse<T>)
        BaseResponse.builder()
            .data(null)
            .message(SUCCEED_REQUEST_MESSAGE)
            .exceptionCode(null)
            .stackTrace(null)
            .build();
  }

  public static <T> BaseResponse<T> error( // lỗi
      String exceptionCode, String exceptionMessage, String stackTrace) {
    return (BaseResponse<T>)
        BaseResponse.builder()
            .data(null)
            .message(exceptionMessage)
            .exceptionCode(exceptionCode)
            .stackTrace(stackTrace)
            .build();
  }

  public static <T> BaseResponse<T> empty() {
    return (BaseResponse<T>)
        BaseResponse.builder()
            .data(Collections.emptyList()) // danh sách trống
            .message(SUCCEED_REQUEST_MESSAGE)
            .exceptionCode(null)
            .stackTrace(null)
            .build();
  }
}
