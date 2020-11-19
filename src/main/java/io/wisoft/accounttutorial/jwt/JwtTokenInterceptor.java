package io.wisoft.accounttutorial.jwt;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtTokenInterceptor extends HandlerInterceptorAdapter {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String token = request.getHeader(TokenUtils.AUTH_HEADER);

    if (token != null) {
      return TokenUtils.isValidToken(token);
    }
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    return false;
  }

}
