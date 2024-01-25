package com.art.jeanyvesart.model;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



@Data
@NoArgsConstructor

@AllArgsConstructor
public  class MyProduct implements Serializable  {
    private static final long serialVersionUID = 123456789L;

    private long id;
    private String imageUrl;
    private String title;
    private String price;
    private String description;
    private int quantity;

    private List<MyReview> myReviews = new ArrayList<>();
    private String medium;
    private String size;
    public MyProduct(long id,
                     String imageUrl,
                     String title,
                     String price,
                     String description, int quantity

    ) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.price = price;
        this.description = description;
        this.quantity = quantity;

    }

}