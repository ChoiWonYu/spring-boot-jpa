package jpabook.jpashop.service;

import java.util.List;
import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

  private final OrderRepository orderRepository;
  private final MemberRepository memberRepository;
  private final ItemRepository itemRepository;

  /**
   * 주문
   */
  @Transactional
  public Order order(Long memberId, Long itemId, int count) {
    Member member=memberRepository.findOne(memberId);
    Item item=itemRepository.findOne(itemId);

    Delivery delivery=new Delivery();
    delivery.setAddress(member.getAddress());

    OrderItem orderItem=OrderItem.createOrderItem(item,item.getPrice(),count);

    Order order=Order.createOrder(member,delivery,orderItem);

    orderRepository.save(order);

    return order;
  }

  @Transactional
  public void cancelOrder(Long orderId){
    Order order = orderRepository.findOne(orderId);
    order.cancel();
  }

  //public List<Order> findSearchOrders(OrderSearch orderSearch){
  //  return orderRepository.findAll(orderSearch);
  //}
}
