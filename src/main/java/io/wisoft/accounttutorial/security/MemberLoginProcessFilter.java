package io.wisoft.accounttutorial.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.wisoft.accounttutorial.dto.RequestToLogin;
import io.wisoft.accounttutorial.exception.LoginContainWhitespace;
import io.wisoft.accounttutorial.exception.LoginEmailInvalidException;
import io.wisoft.accounttutorial.exception.LoginMethodNotEqualException;
import io.wisoft.accounttutorial.exception.LoginPasswordInvalidException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberLoginProcessFilter extends AbstractAuthenticationProcessingFilter {

  private final ObjectMapper objectMapper = new ObjectMapper();

  public MemberLoginProcessFilter() {
    super(new AntPathRequestMatcher("/api/signin"));
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException, IOException, ServletException {
    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

    if (!HttpMethod.POST.name().equals(request.getMethod())) {
      throwBadRequestException(response, "로그인은 반드시 POST로 요청해야 합니다.", new LoginMethodNotEqualException("error"));
    }

    RequestToLogin requestToLogin = objectMapper.readValue(request.getReader(), RequestToLogin.class);

    String username = requestToLogin.getUsername();
    String password = requestToLogin.getPassword();

    if (StringUtils.isEmpty(username)) {
      throwBadRequestException(response, "이메일을 반드시 입력해주세요.",
          new LoginEmailInvalidException("error"));
    }

    if (StringUtils.isEmpty(password)) {
      throwBadRequestException(response, "비밀번호를 반드시 입력해주세요.",
          new LoginPasswordInvalidException("error"));
    }

    if (StringUtils.containsWhitespace(username) || StringUtils.containsWhitespace(password)) {
      throwBadRequestException(response, "이메일이나 비밀번호에 공백이 존재하면 안됩니다.",
          new LoginContainWhitespace("error"));
    }

    MemberAuthenticationToken memberAuthenticationToken =
        new MemberAuthenticationToken(username, password);

    return getAuthenticationManager().authenticate(memberAuthenticationToken);
  }

  private void throwBadRequestException(HttpServletResponse response, String s, AuthenticationException exception)
      throws IOException {
    response.setStatus(HttpStatus.BAD_REQUEST.value());
    objectMapper.writeValue(response.getWriter(), s);
    throw exception;
  }

}
