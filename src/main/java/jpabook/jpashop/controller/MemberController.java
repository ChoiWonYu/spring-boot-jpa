package jpabook.jpashop.controller;

import jakarta.validation.Valid;
import java.util.List;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.dto.member.MemberSigninRequestDto;
import jpabook.jpashop.dto.member.MemberSigninResponseDto;
import jpabook.jpashop.dto.member.MemberJoinRequestDto;
import jpabook.jpashop.dto.member.MemberJoinResponseDto;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/members")
  public List<Member> getMemberList() {
    return this.memberService.findMember();
  }


  @PostMapping("/members/signup")
  public MemberJoinResponseDto signup(@Valid @RequestBody MemberJoinRequestDto memberForm) {
    return memberService.join(memberForm);
  }

  @PostMapping("/members/signin")
  public ResponseEntity<String> signin(@Valid @RequestBody MemberSigninRequestDto memberInfo) {
    return ResponseEntity.ok().body(memberService.signin(memberInfo));
  }
}
