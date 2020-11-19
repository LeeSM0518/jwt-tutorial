package io.wisoft.accounttutorial.service;

import io.wisoft.accounttutorial.dto.LoginDto;
import io.wisoft.accounttutorial.entity.Member;
import io.wisoft.accounttutorial.repository.MemberRepository;
import io.wisoft.accounttutorial.security.MemberContext;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class SignInService {

  private final MemberRepository memberRepository;
  private ModelMapper modelMapper = new ModelMapper();

  public UserDetails findMemberByUsername(String username) throws UsernameNotFoundException {
    Member member = memberRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("UsernameNotFound"));

    LoginDto loginDto = modelMapper.map(member, LoginDto.class);

    return new MemberContext(loginDto, Collections.singletonList(new SimpleGrantedAuthority("USER")));
  }

}
