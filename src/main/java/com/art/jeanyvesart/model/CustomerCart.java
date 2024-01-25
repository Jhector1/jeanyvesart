package com.art.jeanyvesart.model;

import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
//@DiscriminatorValue("customerCart")
//@Data
public class CustomerCart extends CustomerData<CustomerCartHelper> {
  //  @ElementCollection
  //  private List<Integer> quantities;
    public CustomerCart(MyCustomer myCustomer, List<CustomerCartHelper> customerDataHelpers){
       super(myCustomer, customerDataHelpers);
      // this.quantities = quantities;
    }

}


