package jpabook.jpashop.service;

import java.util.List;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
  public Long join(Member member) {
    validateDuplicateMember(member);
    memberRepository.save(member);
    //이 순간에 영속성 컨텍스트에 올라가는데 키값이 PK가 됨
    return member.getId();
  }

  private void validateDuplicateMember(Member member) {
    List<Member> findMembers = memberRepository.findByName(member.getName());
    if(!findMembers.isEmpty()){
      throw new IllegalStateException("이미 존재하는 회원입니다.");
    }
  }

  public List<Member> findMember(){
    return memberRepository.findAll();
  }

  public Member findOne(Long id){
    return memberRepository.findOne(id);
  }

  //회원 전체 조회
}
