package com.ltnc.be.rest.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpsertUserRequest extends BaseRequest {
  @Schema(accessMode = Schema.AccessMode.READ_ONLY)
  private Long userId;

  @NotBlank private String username;
  @NotBlank private String fullName;
  @NotBlank private String email;
  @NotBlank private String password;
}
