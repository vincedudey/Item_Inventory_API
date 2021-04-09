package com.vincent.Item_Inventory_API.service;

import com.vincent.Item_Inventory_API.domain.Item;
import com.vincent.Item_Inventory_API.domain.Status;
import java.util.List;
import java.util.Optional;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
  @Autowired
  SessionFactory sessionFactory;
  Session session;

  public Optional<Item> findItemById(int itemId){
    session = sessionFactory.openSession();
    Query query = session.createQuery("from Item where id =: itemId");
    query.setParameter("itemId", itemId);

    try{
      Item item = (Item)query.getSingleResult();
      session.close();
      return Optional.of(item);
    }
    catch (NoResultException nre){
      return Optional.empty();
    }
    finally{
      session.close();
    }
  }

  @Override
  public Item saveItem(Item item) {
    session = sessionFactory.openSession();
    session.beginTransaction();
    session.save(item);
    session.getTransaction().commit();
    Item savedItem = session.load(Item.class, item.getItemId());
    session.close();
    return savedItem;
  }

  @Override
  public Item updateItem(Item item) {
    session = sessionFactory.openSession();
    session.beginTransaction();
    session.update(item);
    session.getTransaction().commit();
    Item updatedItem = session.load(Item.class, item.getItemId());
    session.close();
    return updatedItem;
  }

  @Override
  public void deleteItem(int itemId) {
    session = sessionFactory.openSession();
    session.beginTransaction();
    Item existingItem = session.load(Item.class, itemId);
    if(existingItem != null){
      session.delete(existingItem);
      session.getTransaction().commit();
    }
    session.close();
  }

  @Override
  public void deleteAllItems() {
    session = sessionFactory.openSession();
    session.beginTransaction();
    Query query = session.createQuery("delete from Item");
    query.executeUpdate();
    session.getTransaction().commit();
    session.close();
  }

  @Override
  public List<Item> findAllItems() {
    session = sessionFactory.openSession();
    List<Item> allItems = session.createQuery("select i from Item i", Item.class).getResultList();
    session.close();
    return allItems;
  }

  @Override
  public List<Item> findItemsByStatusAndUser(Status itemStatus, String user) {
    session = sessionFactory.openSession();
    System.out.println(itemStatus.name());
    System.out.println(user);
    Query query = session.createQuery("from Item where itemStatus =: itemStatus and itemEnteredByUser =: user", Item.class);
    query.setParameter("itemStatus", itemStatus);
    query.setParameter("user", user);
    List<Item> result = query.list();
    session.close();
    return result;
  }

  @Override
  public List<Item> findItemsByPagesizeAndSortBy(int pageSize, int page, String sortBy) {
    session = sessionFactory.openSession();
    Query query = null;

    switch(sortBy){
      case "itemId":
        query = session.createQuery("select i from Item i order by itemId", Item.class);
        break;
      case "itemName":
        query = session.createQuery("select i from Item i order by itemName", Item.class);
        break;
      case "itemEnteredByUser":
        query = session.createQuery("select i from Item i order by itemEnteredByUser", Item.class);
        break;
      case "itemEnteredDate":
        query = session.createQuery("select i from Item i order by itemEnteredDate", Item.class);
        break;
      case "itemBuyingPrice":
        query = session.createQuery("select i from Item i order by itemBuyingPrice", Item.class);
        break;
      case "itemSellingPrice":
        query = session.createQuery("select i from Item i order by itemSellingPrice", Item.class);
        break;
      case "itemLastModifiedDate":
        query = session.createQuery("select i from Item i order by itemLastModifiedDate", Item.class);
        break;
      case "itemLastModifiedByUser":
        query = session.createQuery("select i from Item i order by itemLastModifiedByUser", Item.class);
        break;
      case "itemStatus":
        query = session.createQuery("select i from Item i order by itemStatus", Item.class);
        break;
      default:
        break;
    }

    query.setFirstResult(pageSize * page);
    query.setMaxResults(pageSize);
    List<Item> result = query.list();
    session.close();
    return result;
  }

}
