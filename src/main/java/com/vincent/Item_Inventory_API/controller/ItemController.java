package com.vincent.Item_Inventory_API.controller;

import com.vincent.Item_Inventory_API.domain.Item;
import com.vincent.Item_Inventory_API.domain.Status;
import com.vincent.Item_Inventory_API.service.ItemServiceImpl;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
  @Autowired
  ItemServiceImpl itemService;

  @PostMapping("/app/item")
  public ResponseEntity<?> addItem(@RequestBody Item item){
    if(itemService.findItemById(item.getItemId()).isPresent())
      return new ResponseEntity<>("Item id already exists.", HttpStatus.BAD_REQUEST);

    System.out.println(item);

    return new ResponseEntity<>(itemService.saveItem(item), HttpStatus.CREATED);
  }

  @PutMapping("/app/item/{itemId}")
  public ResponseEntity<?> updateItem(@RequestBody Item item, @PathVariable("itemId")int itemId){
    if(itemId != item.getItemId())
      return new ResponseEntity<>("Item id mismatch.", HttpStatus.BAD_REQUEST);
    else if(itemService.findItemById(itemId).isEmpty())
      return new ResponseEntity<>("Item id does not exist.", HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(itemService.updateItem(item), HttpStatus.OK);
  }

  @DeleteMapping("/app/item/{itemId}")
  public ResponseEntity<?> deleteItemById(@PathVariable("itemId")int itemId){
    if(itemService.findItemById(itemId).isEmpty())
      return new ResponseEntity<>("Item id does not exist.", HttpStatus.BAD_REQUEST);
    itemService.deleteItem(itemId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/app/item")
  public ResponseEntity<?> deleteAllItems(){
    itemService.deleteAllItems();
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/app/item/{itemId}")
  public ResponseEntity<?> findItemById(@PathVariable("itemId")int itemId){
    Optional<Item> item = itemService.findItemById(itemId);
    if(item.isEmpty())
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    return new ResponseEntity<>(item.get(), HttpStatus.OK);
  }

  @GetMapping("/app/item")
  public ResponseEntity<?> findItemsByQuery(@RequestParam(value = "itemStatus", required = false) Status itemStatus,
      @RequestParam(value = "itemEnteredByUser", required = false)String itemEnteredByUser,
      @RequestParam(value = "pageSize", required = false)Integer pageSize,
      @RequestParam(value = "page", required = false)Integer page,
      @RequestParam(value = "sortBy", required = false)String sortBy){

      if(itemStatus != null && itemEnteredByUser != null && pageSize == null && page == null && sortBy == null){
        return new ResponseEntity<>(itemService.findItemsByStatusAndUser(itemStatus, itemEnteredByUser), HttpStatus.OK);
      }
      else if(itemStatus == null && itemEnteredByUser == null && pageSize != null && page != null && sortBy != null){
        return new ResponseEntity<>(itemService.findItemsByPagesizeAndSortBy(pageSize, page, sortBy), HttpStatus.OK);
      }
      return new ResponseEntity<>(itemService.findAllItems(), HttpStatus.OK);
  }



}
