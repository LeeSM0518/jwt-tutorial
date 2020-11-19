package io.wisoft.accounttutorial.jwt;

import io.jsonwebtoken.*;
import io.wisoft.accounttutorial.dto.LoginDto;
import lombok.extern.log4j.Log4j2;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class TokenUtils {

  private static final String SECRET_KEY = "WiSoft";
  public static final String AUTH_HEADER = "X-AUTH-TOKEN";

  public static String generateJwtToken(LoginDto loginDto) {
    return Jwts.builder()
        .setSubject(loginDto.getUsername())
        .setHeader(createHeader())
        .setClaims(createClaims(loginDto))
        .setExpiration(createExpireDateForOneYear())
        .signWith(SignatureAlgorithm.HS256, createSigningKey())
        .compact();
  }

  public static boolean isValidToken(String token) {
    try {
      Claims claims = getClaimsFormToken(token);
      log.info("expireTime: " + claims.getExpiration());
      log.info("username: " + claims.get("username"));
      return true;
    } catch (ExpiredJwtException e) {
      log.error("Token Expired");
      return false;
    } catch (JwtException e) {
      log.error("Token Tampered");
      return false;
    } catch (NullPointerException e) {
      log.error("Token is null");
      return false;
    }
  }

  private static Claims getClaimsFormToken(String token) {
    return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
        .parseClaimsJws(token).getBody();
  }

  private static Key createSigningKey() {
    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
    return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
  }

  private static Date createExpireDateForOneYear() {
    // 토큰 만료시간 30일
    Calendar c = Calendar.getInstance();
    c.add(Calendar.DATE, 30);
    return c.getTime();
  }

  private static Map<String, Object> createHeader() {
    Map<String, Object> header = new HashMap<>();

    header.put("typ", "JWT");
    header.put("alg", "HS256");
    header.put("regDate", System.currentTimeMillis());

    return header;
  }

  private static Map<String, Object> createClaims(LoginDto loginDto) {
    Map<String, Object> claims = new HashMap<>();

    claims.put("username", loginDto.getUsername());

    return claims;
  }



}
