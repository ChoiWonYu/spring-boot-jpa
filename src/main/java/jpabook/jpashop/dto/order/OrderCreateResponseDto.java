package jpabook.jpashop.dto.order;

import java.time.LocalDateTime;
import java.util.List;
import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateResponseDto {
  private OrderStatus status;
}
