package com.art.jeanyvesart.model;


import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@NoArgsConstructor
//@DiscriminatorColumn(name="customerData",
        //discriminatorType = DiscriminatorType.STRING)
@AllArgsConstructor
public abstract class CustomerData<H> implements Serializable {

    private long id;

    private List<H> customerDataHelpers = new ArrayList<>();


   // @JsonManagedReference

    private MyCustomer myCustomer;
    private Date date = new Date();
    public CustomerData(MyCustomer myCustomer, List<H> customerDataHelpers){
        this.myCustomer = myCustomer;
        this.customerDataHelpers = customerDataHelpers;
    }


}
