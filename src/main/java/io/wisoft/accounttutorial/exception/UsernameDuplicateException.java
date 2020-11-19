package io.wisoft.accounttutorial.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "아이디가 이미 존재합니다.")
public class UsernameDuplicateException extends RuntimeException {
}
