package jpabook.jpashop.service;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.exception.NotEnoughStockException;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class OrderServiceTest {

  @PersistenceContext
  EntityManager em;

  @Autowired
  OrderService orderService;
  @Autowired
  OrderRepository orderRepository;

  @Test
  @DisplayName("상품 주문")
  public void 상품주문() throws Exception {
    //given
    Member member = createMember();

    Book book = createBook("하이", 10000, 10);

    int orderCount = 2;
    //when
    Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
    //then
    Order getOrder = orderRepository.findOne(orderId);

    assertEquals(OrderStatus.ORDER, getOrder.getStatus());
    assertEquals(1, getOrder.getOrderItems().size());
    assertEquals(10000 * orderCount, getOrder.getTotalPrice());
    assertEquals(8, book.getStockQuantity());
  }

  @Test
  @DisplayName("주문 취소")
  public void 주문취소() throws Exception {
    //given
    Member member = createMember();
    Book book = createBook("하이", 10000, 10);

    int orderCount = 2;
    Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

    //when
    orderService.cancelOrder(orderId);

    //then
    Order getOrder = orderRepository.findOne(orderId);

    assertEquals(OrderStatus.CANCEL, getOrder.getStatus());
    assertEquals(10, book.getStockQuantity());
  }

  @Test
  @DisplayName("상품 주문 재고수량 초과")
  public void 상품주문_재고수량초과() throws Exception {
    //given
    Member member = createMember();
    Item book = createBook("하이", 10000, 10);

    int orderCount = 11;

    //then&when
    assertThrows(NotEnoughStockException.class,
        () -> orderService.order(member.getId(), book.getId(), orderCount));

  }

  private Book createBook(String bookName, int price, int stockQuantity) {
    Book book = new Book();
    book.setName(bookName);
    book.setPrice(price);
    book.setStockQuantity(stockQuantity);
    em.persist(book);
    return book;
  }

  private Member createMember() {
    Member member = new Member();
    member.setName("kim");
    member.setAddress(new Address("서울", "강가", "123-123"));
    em.persist(member);
    return member;
  }
}

