package io.wisoft.accounttutorial.service;

import io.wisoft.accounttutorial.dto.SignUpRequest;
import io.wisoft.accounttutorial.entity.Member;
import io.wisoft.accounttutorial.repository.MemberRepository;
import io.wisoft.accounttutorial.security.MemberContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class SignUpServiceTest {

  @Autowired
  SignUpService signUpService;
  @Autowired
  MemberRepository memberRepository;

  Member member;

  @BeforeEach
  public void init() {
    String username = "nalsm98";
    member = new Member(username, "1234", "이상민", "010-7188-9608",
        "nalsm0518@gmail.com");
  }

  @Test
  public void 회원가입() throws Exception {
    SignUpRequest signUpRequest = new SignUpRequest(member.getUsername(), member.getPassword(), member.getName(),
        member.getPhone(), member.getEmail());

    // when
    signUpService.signUp(signUpRequest);
    Member member = memberRepository.findByUsername(this.member.getUsername()).orElseThrow(NullPointerException::new);

    // then
    Assertions.assertEquals(this.member.getName(), member.getName());
  }

}
