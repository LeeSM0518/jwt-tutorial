package io.wisoft.accounttutorial.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

  @Test
  public void 회원_생성() throws Exception {
    // given
    String username = "nalsm98";
    Member member = new Member(username, "1234", "이상민", "010-7188-9608",
        "nalsm0518@gmail.com");

    // when
    // then
    assertNotNull(member);
    assertEquals(username, member.getUsername());
  }

}