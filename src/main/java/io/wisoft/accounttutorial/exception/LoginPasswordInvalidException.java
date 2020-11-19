package io.wisoft.accounttutorial.exception;

import org.springframework.security.core.AuthenticationException;

public class LoginPasswordInvalidException extends AuthenticationException {
  public LoginPasswordInvalidException(String error) {
    super(error);
  }
}
