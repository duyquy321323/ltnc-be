package com.ltnc.be.rest.filter;

import com.ltnc.be.port.service.JsonService;
import com.ltnc.be.rest.response.BaseResponse;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {
  private final JsonService jsonService;

  private static final String UN_AUTHORIZED_MESSAGE =
      "Can not authorize the user due to invalid token";

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    try {
      filterChain.doFilter(request, response);
    } catch (Exception exception) {
      if (exception instanceof JwtException) handleJwtException(response, exception);
      else handleUnknownException(response, exception);
    }
  }

  private void byPassCors(@NonNull HttpServletResponse response) {
    // TODO: Restrict when releasing to the production
    response.setHeader("Access-Control-Allow-Origin", "*");
  }

  @SneakyThrows
  private void handleJwtException(@NonNull HttpServletResponse response, Exception exception) {
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response
        .getWriter()
        .write(
            jsonService.toJson(
                BaseResponse.error(
                    UN_AUTHORIZED_MESSAGE,
                    ExceptionUtils.getRootCauseMessage(exception),
                    Arrays.toString(exception.getStackTrace()))));
  }

  @SneakyThrows
  private void handleUnknownException(@NonNull HttpServletResponse response, Exception exception) {
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    response
        .getWriter()
        .write(
            jsonService.toJson(
                BaseResponse.error(
                    ExceptionUtils.getRootCauseMessage(exception),
                    ExceptionUtils.getRootCauseMessage(exception),
                    Arrays.toString(exception.getStackTrace()))));
  }
}
