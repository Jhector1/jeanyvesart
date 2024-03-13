package com.art.jeanyvesart.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class MyOrder implements Serializable {

    private long id;

    private Date date = new Date();
//    @JoinTable(
//            name = "my_order_my_customer ",
//            joinColumns = @JoinColumn(name = "my_order_id"),
//            inverseJoinColumns = @JoinColumn(name = "my_customer_id")
//    )
   // @JoinColumn(name = "my_customer_id")

    private MyCustomer myCustomer;
    private List<MyProduct> myProducts = new ArrayList<>();

    public MyOrder(List<MyProduct> myProducts){
        this.myProducts = myProducts;
        this.myCustomer = new MyCustomer();
    }

    public MyOrder(MyCustomer myCustomer, List<MyProduct> myProducts) {
        this.myCustomer = myCustomer; this.myProducts = myProducts;
    }

}
