package jpabook.jpashop.dto.member;

import jakarta.validation.constraints.NotEmpty;
import jpabook.jpashop.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberJoinRequestDto {
  @NotEmpty(message = "회원 이름은 필수 입니다")
  private String name;
  private Address address;
}
