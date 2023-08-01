package jpabook.jpashop.dto.member;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberSigninRequestDto {

  @NotEmpty
  private String name;

  @NotEmpty
  private String password;
}
