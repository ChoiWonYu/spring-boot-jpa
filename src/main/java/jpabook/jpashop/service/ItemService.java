package jpabook.jpashop.service;

import java.util.List;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.dto.item.BookCreateRequestDto;
import jpabook.jpashop.dto.item.BookUpdateRequestDto;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

  private final ItemRepository itemRepository;

  public void saveItem(Item item) {
    itemRepository.saveItem(item);
  }

  public List<Item> findItems() {
    return itemRepository.findAll();
  }

  public Item findOne(Long itemId) {
    return itemRepository.findOne(itemId);
  }

  public Item createBook(BookCreateRequestDto bookCreateRequestDto) {
    Book createdBook = new Book();
    createdBook.setName(bookCreateRequestDto.getName());
    createdBook.setPrice(bookCreateRequestDto.getPrice());
    createdBook.setStockQuantity(bookCreateRequestDto.getStockQuantity());
    createdBook.setAuthor(bookCreateRequestDto.getAuthor());
    createdBook.setIsbn(bookCreateRequestDto.getIsbn());

    itemRepository.saveItem(createdBook);

    return createdBook;
  }

  public Item updateBook(Long itemId,BookUpdateRequestDto bookUpdateRequestDto) {
    Item targetBook = itemRepository.findOne(itemId);
    targetBook.setName(bookUpdateRequestDto.getName());
    targetBook.setPrice(bookUpdateRequestDto.getPrice());
    targetBook.setStockQuantity(bookUpdateRequestDto.getStockQuantity());

    return targetBook;
  }
}
