package io.wisoft.accounttutorial.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

  @NotBlank(message = "아이디를 반드시 입력해주세요.")
  private String username;

  @NotBlank(message = "비밀번호를 반드시 입력해주세요.")
  private String password;

  @NotBlank(message = "이름을 반드시 입력해주세요.")
  private String name;

  @NotBlank(message = "전화번호를 반드시 입력해주세요.")
  private String phone;

  @NotBlank(message = "이메일을 반드시 입력해주세요.")
  private String email;

}
