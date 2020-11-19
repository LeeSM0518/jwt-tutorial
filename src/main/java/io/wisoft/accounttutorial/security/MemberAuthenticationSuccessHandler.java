package io.wisoft.accounttutorial.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.wisoft.accounttutorial.dto.LoginDto;
import io.wisoft.accounttutorial.jwt.TokenUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  private final ObjectMapper mapper = new ObjectMapper();

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                      Authentication authentication) throws IOException, ServletException {
    LoginDto loginDto = (LoginDto) authentication.getPrincipal();
    String token = TokenUtils.generateJwtToken(loginDto);
    response.addHeader(TokenUtils.AUTH_HEADER, token);
    response.setStatus(HttpStatus.OK.value());
  }

}
