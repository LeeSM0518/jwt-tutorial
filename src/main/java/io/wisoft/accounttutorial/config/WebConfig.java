package io.wisoft.accounttutorial.config;

import io.wisoft.accounttutorial.jwt.JwtTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(jwtTokenInterceptor())
        .addPathPatterns("/api/**")
        .excludePathPatterns("/api/signup", "/api/signin", "/api/resetpassword");
  }

  @Bean
  public JwtTokenInterceptor jwtTokenInterceptor() {
    return new JwtTokenInterceptor();
  }

}
