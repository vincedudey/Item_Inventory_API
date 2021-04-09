package com.vincent.Item_Inventory_API.service;

import com.vincent.Item_Inventory_API.domain.Item;
import com.vincent.Item_Inventory_API.domain.Status;
import java.util.List;
import java.util.Optional;

public interface ItemService {
  public Optional<Item> findItemById(int itemId);
  public Item saveItem(Item item);
  public Item updateItem(Item item);
  public void deleteItem(int itemId);
  public void deleteAllItems();
  public List<Item> findAllItems();
  public List<Item> findItemsByStatusAndUser(Status itemStatus, String user);
  public List<Item> findItemsByPagesizeAndSortBy(int pageSize, int page, String sortBy);
}
