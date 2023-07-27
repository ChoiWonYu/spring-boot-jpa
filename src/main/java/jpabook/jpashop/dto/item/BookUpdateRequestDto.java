package jpabook.jpashop.dto.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BookUpdateRequestDto {

  private String name;
  private int price;
  private int stockQuantity;
  private String author;
  private String isbn;

}
