package com.art.jeanyvesart.model;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class CustomerDataHelper {

    private long id;

    private MyProduct myProduct;
    private int quantity;
  private Date date = new Date();



  public CustomerDataHelper(MyProduct myProduct, int quantity) {
  this.myProduct = myProduct; this.quantity = quantity;
  }
}
