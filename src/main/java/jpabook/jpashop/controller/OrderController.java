package jpabook.jpashop.controller;

import jakarta.validation.Valid;
import java.util.List;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.dto.order.OrderCreateRequestDto;
import jpabook.jpashop.dto.order.OrderCreateResponseDto;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    final private OrderService orderService;

    @PostMapping("/orders/{productId}")
    public OrderCreateResponseDto createOrder(@PathVariable(value="productId") Long productId,
        @Valid @RequestBody OrderCreateRequestDto orderCreateRequestDto ){
      return this.orderService.order(orderCreateRequestDto.getMemberId(),productId,orderCreateRequestDto.getCount());
    }

//    @GetMapping("/orders")
//    public List<Order> getOrder(){
//      return this.orderService.getOrderList();
//    }

    @PostMapping("/orders/cancel/{orderId}")
    public String cancelOrder(@PathVariable(value="orderId") Long orderId){
      this.orderService.cancelOrder(orderId);
      return "상품이 취소되었습니다.";
    }


}
