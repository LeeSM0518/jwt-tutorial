package io.wisoft.accounttutorial.service;

import io.wisoft.accounttutorial.dto.SignUpRequest;
import io.wisoft.accounttutorial.entity.Member;
import io.wisoft.accounttutorial.exception.UsernameDuplicateException;
import io.wisoft.accounttutorial.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SignUpService {

  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public void signUp(SignUpRequest request) {
    if (memberRepository.findByUsername(request.getUsername()).isPresent())
      throw new UsernameDuplicateException();

    Member member = new Member(
        request.getUsername(),
        passwordEncoder.encode(request.getPassword()),
        request.getName(),
        request.getPhone(),
        request.getEmail());
    memberRepository.save(member);
  }

}
