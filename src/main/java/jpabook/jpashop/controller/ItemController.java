package jpabook.jpashop.controller;

import jakarta.validation.Valid;
import java.util.List;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.dto.item.BookCreateRequestDto;
import jpabook.jpashop.dto.item.BookUpdateRequestDto;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class ItemController {

  private final ItemService itemService;

  @GetMapping(value = "/items")
  public List<Item> getItems() {
    return itemService.findItems();
  }

  @PostMapping(value = "/items")
  public Item createBook(@Valid @RequestBody BookCreateRequestDto bookCreateRequestDto) {
    return itemService.createBook(bookCreateRequestDto);
  }

  @PutMapping(value = "/items/{itemId}")
  public Item updateBook(@PathVariable final Long itemId,@RequestBody BookUpdateRequestDto bookUpdateRequestDto) {
    return itemService.updateBook(itemId,bookUpdateRequestDto);
  }

}
