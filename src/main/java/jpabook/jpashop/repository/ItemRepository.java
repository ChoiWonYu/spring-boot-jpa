package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

  private final EntityManager em;

  public void saveItem(Item item) {
    if (item.getId() == null) {
      em.persist(item);
    } else {
      em.merge(item);
      //여기서 merge는 갱신되지 않은 item 정보를 논리적으로는 업데이트하는 개념
    }
  }

  public Item findOne(Long id) {
    return em.find(Item.class, id);
  }

  public List<Item> findAll() {
    return em
        .createQuery("select i from Item i", Item.class)
        .getResultList();
  }
}

