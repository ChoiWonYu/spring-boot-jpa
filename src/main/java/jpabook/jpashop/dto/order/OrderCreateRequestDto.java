package jpabook.jpashop.dto.order;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderCreateRequestDto {

  @NotEmpty(message = "주문 수량은 필수입니다.")
  private int count;

}
