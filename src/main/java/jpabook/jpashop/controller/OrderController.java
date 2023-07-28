package jpabook.jpashop.controller;

import java.util.List;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.dto.order.OrderCreateRequestDto;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class OrderController {
    final private OrderService orderService;

    @PostMapping("/orders/{productId}")
    public Order createOrder(@RequestParam Long memberId,
        @PathVariable(value="productId") Long productId, @RequestBody OrderCreateRequestDto orderCreateRequestDto ){
      return this.orderService.order(memberId,productId,orderCreateRequestDto.getCount());
    }

    @GetMapping("/orders")
    public List<Order> getOrder(){
      return this.orderService.getOrderList();
    }

    @PostMapping("/orders/cancel/{orderId}")
    public String cancelOrder(@PathVariable(value="orderId") Long orderId){
      this.orderService.cancelOrder(orderId);
      return "상품이 취소되었습니다.";
    }


}
