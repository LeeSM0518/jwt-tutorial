package io.wisoft.accounttutorial.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "이메일이 존재하지 않습니다.")
public class EmailNotFoundException extends RuntimeException {
}
