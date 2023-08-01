package jpabook.jpashop.controller;

import java.net.Authenticator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HomeController {
  @RequestMapping("/")
  public String home(Authentication authentication)  {
    log.info("{}님의 요청입니다",authentication.getName());
    return "home";
  }
}