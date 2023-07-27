package jpabook.jpashop.service;

import java.util.List;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.dto.member.MemberJoinRequestDto;
import jpabook.jpashop.dto.member.MemberJoinResponseDto;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
//스프링에서 사용한다면 스프링에서 제공하는 Transactional 어노테이션 사용하는 게 나음
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  //회원 가입
  @Transactional
  public MemberJoinResponseDto join(MemberJoinRequestDto memberForm) {
    validateDuplicateMember(memberForm.getName());

    Member createdMember=new Member();

    createdMember.setAddress(memberForm.getAddress());
    createdMember.setName(memberForm.getName());

    memberRepository.save(createdMember);
    //이 순간에 영속성 컨텍스트에 올라가는데 키값이 PK가 됨

    return MemberJoinResponseDto.toDto(createdMember);
  }

  private void validateDuplicateMember(String name) {
    List<Member> findMembers = memberRepository.findByName(name);
    if (!findMembers.isEmpty()) {
      throw new IllegalStateException("이미 존재하는 회원입니다.");
    }
  }

  public List<Member> findMember() {
    return memberRepository.findAll();
  }

  public Member findOne(Long id) {
    return memberRepository.findOne(id);
  }

  //회원 전체 조회
}
