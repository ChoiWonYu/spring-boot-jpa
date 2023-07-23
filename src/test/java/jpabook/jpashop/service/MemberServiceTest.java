package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

  @Autowired MemberService memberService;
  @Autowired
  MemberRepository memberRepository;


  @Test
  @DisplayName("회원가입")
  public void signup() throws Exception {
    //given
    Member member=new Member();
    member.setName("kim");

    //when
    Long saveId = memberService.join(member);
    //persist 될 때는 영속성 컨텍스트에 올라가지 쿼리가 날아가지 않는다.
    //Test 코드에서 Transactional은 롤백을 해주는 기능이 있다.
    //영속성 컨텍스트에서 DB로 쿼리를 날리는 작업을 flush라고 한다.

    //then
    Assertions.assertEquals(member,memberRepository.findOne(saveId));
   }

  @Test(expected=IllegalStateException.class)
  @DisplayName("중복 회원 예외")
  public void duplciateException() throws Exception {
    //given
    Member member1=new Member();
    member1.setName("kim");

    Member member2=new Member();
    member2.setName("kim");

    //when
    memberService.join(member1);
    memberService.join(member2);
    //then
    Assertions.fail("예외가 발생해야 한다.");

   }

}