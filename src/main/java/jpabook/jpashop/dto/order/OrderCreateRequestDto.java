package jpabook.jpashop.dto.order;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateRequestDto {

  @NotNull()
  private Long memberId;

  @NotNull(message = "주문 수량은 필수입니다.")
  private int count;

}
