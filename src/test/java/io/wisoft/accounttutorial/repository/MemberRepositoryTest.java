package io.wisoft.accounttutorial.repository;

import io.wisoft.accounttutorial.entity.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberRepositoryTest {

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
  public void 회원_생성() throws Exception {
    // given
    memberRepository.save(member);

    // when
    Member getMember = memberRepository.findById(member.getId())
        .orElseThrow(NullPointerException::new);

    // then
    Assertions.assertEquals(member.getId(), getMember.getId());
  }

  @Test
  public void 아이디로_검색() throws Exception {
    // given
    memberRepository.save(member);

    // when
    Member memberFoundByUsername = memberRepository.findByUsername(member.getUsername())
        .orElseThrow(NullPointerException::new);

    // then
    Assertions.assertEquals(member.getUsername(), memberFoundByUsername.getUsername());
    Assertions.assertEquals(member.getId(), memberFoundByUsername.getId());
  }

  @Test
  public void 이메일로_검색() throws Exception {
    // given
    memberRepository.save(member);

    // when
    Member memberFoundByEmail =
        memberRepository.findByEmail(member.getEmail()).orElseThrow(NullPointerException::new);

    // then
    Assertions.assertEquals(member.getUsername(), memberFoundByEmail.getUsername());
  }

}
