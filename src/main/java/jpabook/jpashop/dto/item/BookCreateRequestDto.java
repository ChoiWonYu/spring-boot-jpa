package jpabook.jpashop.dto.item;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookCreateRequestDto {

  @NotEmpty(message = "책 이름은 필수입니다.")
  private String name;

  @NotNull(message = "가격은 필수입니다.")
  private int price;

  @NotNull(message = "책 수량은 필수입니다.")
  private int stockQuantity;

  @NotEmpty(message = "저자는 필수입니다.")
  private String author;

  private String isbn;

}
