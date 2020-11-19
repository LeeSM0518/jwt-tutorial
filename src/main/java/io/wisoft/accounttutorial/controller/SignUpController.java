package io.wisoft.accounttutorial.controller;

import io.wisoft.accounttutorial.dto.SignUpRequest;
import io.wisoft.accounttutorial.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class SignUpController {

  private final SignUpService signUpService;

  @PostMapping("/api/signup")
  public void signUp(@Valid @RequestBody SignUpRequest dto) {
    signUpService.signUp(dto);
  }

}
