package io.wisoft.accounttutorial.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.wisoft.accounttutorial.dto.RequestToLogin;
import io.wisoft.accounttutorial.entity.Member;
import io.wisoft.accounttutorial.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class SignInTest {

  @Autowired
  MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;
  @Autowired
  MemberRepository memberRepository;
  @Autowired
  PasswordEncoder passwordEncoder;

  Member member;
  String username = "nalsm98";
  String password = "1234";

  @BeforeEach
  public void init() {
    member = new Member(username, passwordEncoder.encode(password), "이상민", "010-7188-9608",
        "nalsm0518@gmail.com");
    memberRepository.save(member);

    Member getMember = memberRepository.findById(member.getId())
        .orElseThrow(NullPointerException::new);

    Assertions.assertEquals(member.getId(), getMember.getId());
  }

  @Test
  public void 로그인() throws Exception {
    // when
    RequestToLogin requestToLogin = new RequestToLogin(username, password);

    // then
    mockMvc.perform(
        MockMvcRequestBuilders.post("/api/signin")
            .content(objectMapper.writeValueAsString(requestToLogin))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

}
