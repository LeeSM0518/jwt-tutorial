package io.wisoft.accounttutorial.service;

import io.wisoft.accounttutorial.entity.Member;
import io.wisoft.accounttutorial.exception.EmailNotFoundException;
import io.wisoft.accounttutorial.repository.MemberRepository;
import io.wisoft.accounttutorial.utils.RandomValueUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

  private final MemberRepository memberRepository;
  private final MailService mailService;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public void resetPassword(String email) {
    Member member = memberRepository.findByEmail(email)
        .orElseThrow(EmailNotFoundException::new);
    String newPassword = RandomValueUtils.getRandom();
    member.changePassword(passwordEncoder.encode(newPassword));
    mailService.sendNewPassword(email, newPassword);
  }

}
