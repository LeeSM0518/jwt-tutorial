package io.wisoft.accounttutorial.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.wisoft.accounttutorial.dto.SignUpRequest;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class SignUpControllerTest {

  @Autowired
  MockMvc mockMvc;
  @Autowired
  ObjectMapper objectMapper;
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

    mockMvc.perform(
        MockMvcRequestBuilders.post("/api/signup")
            .content(objectMapper.writeValueAsString(signUpRequest))
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isOk());

    Member member = memberRepository.findByUsername(this.member.getUsername()).orElseThrow(NullPointerException::new);
    Assertions.assertEquals(this.member.getEmail(), member.getEmail());
  }

}