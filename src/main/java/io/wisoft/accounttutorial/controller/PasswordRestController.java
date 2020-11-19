package io.wisoft.accounttutorial.controller;

import io.wisoft.accounttutorial.dto.ResetPasswordRequest;
import io.wisoft.accounttutorial.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class PasswordRestController {

  private final PasswordResetService service;

  @PostMapping("/api/resetpassword")
  public void resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
    service.resetPassword(request.getEmail());
  }

}
