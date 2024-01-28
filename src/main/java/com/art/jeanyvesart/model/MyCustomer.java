package com.art.jeanyvesart.model;

import com.art.jeanyvesart.model.CustomerCart;
import com.art.jeanyvesart.model.CustomerFavorite;
import com.art.jeanyvesart.model.MyOrder;
import com.art.jeanyvesart.model.MyReview;
import com.art.jeanyvesart.security.user.MyUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@JsonIgnoreProperties({"customerFavorite", "customerCart", "myOrders", "myReviews"})
public class MyCustomer implements Serializable, MyUser {
    //    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence_customer")
//    @SequenceGenerator(name = "my_sequence_customer", sequenceName = "my_customer", initialValue = 0, allocationSize = 1)
    @Setter
    @Getter
    private String id;
    @Setter
    @Getter
    @NotBlank(message = "Full Name is required")

    private String fullName;

    @Setter
    @Getter
    @NotBlank(message = "Email is required")

    private String email;
    @Setter
    @Getter
    private String telephone;
    @Setter
    @Getter
    private String authority;
    @Setter
    @Getter
    @NotBlank(message = "Password is required")

    private String password;
    @Setter
    @Getter
    private String resetToken;
    @Setter
    @Getter
    private Date resetTokenDate;
    @Setter
    @Getter
    private boolean resetTokenUsed;
    @Setter
    @Getter
    private Date expirationDate = new Date();
    @Setter
    @Getter
    boolean collector;
    //@ToString.Exclude
    // @JsonBackReference(value="customerFavoriteReference")
    @Setter
    @Getter
    private CustomerFavorite customerFavorite;
    //@ToString.Exclude
    //  @JsonBackReference(value="customerCartReference")

    @Setter
    @Getter
    private CustomerCart customerCart;
    @Setter
    @Getter
    private Set<Address> addressList;
    @Setter
    @Getter
    private List<MyOrder> myOrders;

    @Setter
    @Getter
    private List<MyReview> myReviews;
    public MyCustomer() {
        // this("randomId");
    }


    public MyCustomer(String id) {
        this.fullName = "anonymous";
        this.email = "example@email.com";
        this.id = id;
        this.telephone = "00000000";
        this.collector = false;
        this.password= "Password@StrongerThanYou1794";
    }


    @Override
    public String getUsername() {
        return this.email;
    }


}