package jpabook.jpashop.controller;

import jakarta.validation.Valid;
import java.util.List;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/members")
  public List<Member> getMemberList(){
    return this.memberService.findMember();
  }


  @PostMapping("/members/register")
  public Member register(@Valid @RequestBody MemberForm memberForm) {

    Address address = new Address(memberForm.getCity(), memberForm.getStreet(),
        memberForm.getZipcode());
    Member member = new Member();
    member.setName(memberForm.getName());
    member.setAddress(address);

    memberService.join(member);
    return member;
  }

}
