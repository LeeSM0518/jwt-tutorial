package io.wisoft.accounttutorial.security;

import io.wisoft.accounttutorial.dto.LoginDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class MemberContext extends User {

  private LoginDto loginDto;

  public MemberContext(LoginDto loginDto, List<SimpleGrantedAuthority> roles) {
    super(loginDto.getUsername(), loginDto.getPassword(), roles);
    this.loginDto = loginDto;
  }
}
