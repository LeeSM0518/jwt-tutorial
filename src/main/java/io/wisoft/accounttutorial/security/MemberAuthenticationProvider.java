package io.wisoft.accounttutorial.security;

import io.wisoft.accounttutorial.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  private SignInService signInService;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = (String) authentication.getCredentials();

    MemberContext memberContext = (MemberContext) signInService.findMemberByUsername(username);

    if (!passwordEncoder.matches(password, memberContext.getPassword())) {
      throw new BadCredentialsException("잘못된 비밀번호 입니다.");
    }

    return new MemberAuthenticationToken(memberContext.getLoginDto(), null, memberContext.getAuthorities());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(MemberAuthenticationToken.class);
  }
}
