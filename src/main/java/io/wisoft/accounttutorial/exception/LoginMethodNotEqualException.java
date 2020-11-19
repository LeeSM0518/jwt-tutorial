package io.wisoft.accounttutorial.exception;

import org.springframework.security.core.AuthenticationException;

public class LoginMethodNotEqualException extends AuthenticationException {
  public LoginMethodNotEqualException(String error) {
    super(error);
  }
}
