package com.vincent.Item_Inventory_API.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;

@Entity
@Table(name="item")
public class Item {
  @Id
  private int itemId;
  private String itemName;
  private String itemEnteredByUser;

  @Temporal(TemporalType.TIMESTAMP)
  private Date itemEnteredDate;

  @Column(columnDefinition = "decimal(10,1)")
  private BigDecimal itemBuyingPrice;

  @Column(columnDefinition = "decimal(10,1)")
  private BigDecimal itemSellingPrice;

  @Temporal(TemporalType.TIMESTAMP)
  private Date itemLastModifiedDate;

  private String itemLastModifiedByUser;

  @Enumerated(EnumType.STRING)
  private Status itemStatus;

  public Item(){}

  public Item(int itemId, String itemName, String itemEnteredByUser, Date itemEnteredDate,
      BigDecimal itemBuyingPrice, BigDecimal itemSellingPrice, Date itemLastModifiedDate,
      String itemLastModifiedByUser, Status itemStatus) {
    this.itemId = itemId;
    this.itemName = itemName;
    this.itemEnteredByUser = itemEnteredByUser;
    this.itemEnteredDate = itemEnteredDate;
    this.itemBuyingPrice = itemBuyingPrice;
    this.itemSellingPrice = itemSellingPrice;
    this.itemLastModifiedDate = itemLastModifiedDate;
    this.itemLastModifiedByUser = itemLastModifiedByUser;
    this.itemStatus = itemStatus;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public int getItemId() {
    return itemId;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public String getItemEnteredByUser() {
    return itemEnteredByUser;
  }

  public void setItemEnteredByUser(String itemEnteredByUser) {
    this.itemEnteredByUser = itemEnteredByUser;
  }

  public Date getItemEnteredDate() {
    return itemEnteredDate;
  }

  public void setItemEnteredDate(Date itemEnteredDate) {
    this.itemEnteredDate = itemEnteredDate;
  }

  public BigDecimal getItemBuyingPrice() {
    return itemBuyingPrice;
  }

  public void setItemBuyingPrice(BigDecimal itemBuyingPrice) {
    this.itemBuyingPrice = itemBuyingPrice;
  }

  public BigDecimal getItemSellingPrice() {
    return itemSellingPrice;
  }

  public void setItemSellingPrice(BigDecimal itemSellingPrice) {
    this.itemSellingPrice = itemSellingPrice;
  }

  public Date getItemLastModifiedDate() {
    return itemLastModifiedDate;
  }

  public void setItemLastModifiedDate(Date itemLastModifiedDate) {
    this.itemLastModifiedDate = itemLastModifiedDate;
  }

  public String getItemLastModifiedByUser() {
    return itemLastModifiedByUser;
  }

  public void setItemLastModifiedByUser(String itemLastModifiedByUser) {
    this.itemLastModifiedByUser = itemLastModifiedByUser;
  }

  public Status getItemStatus() {
    return itemStatus;
  }

  public void setItemStatus(Status itemStatus) {
    this.itemStatus = itemStatus;
  }

  @Override
  public String toString() {
    return "Item{" +
        "itemId=" + itemId +
        ", itemName='" + itemName + '\'' +
        ", itemEnteredByUser='" + itemEnteredByUser + '\'' +
        ", itemEnteredDate=" + itemEnteredDate +
        ", itemBuyingPrice=" + itemBuyingPrice +
        ", itemSellingPrice=" + itemSellingPrice +
        ", itemLastModifiedDate=" + itemLastModifiedDate +
        ", itemLastModifiedByUser='" + itemLastModifiedByUser + '\'' +
        ", itemStatus=" + itemStatus +
        '}';
  }
}
