package jpabook.jpashop.dto.member;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberJoinResponseDto {

  private Long memberId;
  private String name;
  private Address address;

  public static MemberJoinResponseDto toDto(Member member) {
    return new MemberJoinResponseDto(member.getId(), member.getName(), member.getAddress());
  }
}
