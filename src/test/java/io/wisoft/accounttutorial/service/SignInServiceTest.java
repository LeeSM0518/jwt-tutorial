package io.wisoft.accounttutorial.service;

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
class SignInServiceTest {

  @Autowired
  SignInService signInService;
  @Autowired
  MemberRepository memberRepository;

  Member member;

  @BeforeEach
  public void init() {
    String username = "nalsm98";
    member = new Member(username, "1234", "이상민", "010-7188-9608",
        "nalsm0518@gmail.com");
    memberRepository.save(member);

    Member getMember = memberRepository.findById(member.getId())
        .orElseThrow(NullPointerException::new);

    Assertions.assertEquals(member.getId(), getMember.getId());
  }

  @Test
  public void 로그인() throws Exception {
    // when
    MemberContext context = (MemberContext) signInService.findMemberByUsername(member.getUsername());

    // then
    Assertions.assertEquals(member.getUsername(), context.getLoginDto().getUsername());
  }

}