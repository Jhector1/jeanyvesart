package com.art.jeanyvesart.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//@DiscriminatorValue("customerFavorite")
@Data
@NoArgsConstructor
public class CustomerFavorite extends CustomerData<CustomerFavoriteHelper>{

    public CustomerFavorite(MyCustomer myCustomer, List<CustomerFavoriteHelper> customerDataHelpers){
       super(myCustomer, customerDataHelpers);
    }
}
