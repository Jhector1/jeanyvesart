package com.art.jeanyvesart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public  class MyProduct implements Serializable {
    private static final long serialVersionUID = 123456789L;

//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "my_sequence_generator")
//    @SequenceGenerator(name = "my_sequence_generator", sequenceName = "my_sequence", initialValue = 0, allocationSize = 1)

    private long id;
    private String imageUrl;
    private String title;
    private String price;

    private String description;
    private int quantity;

    private ImageData imageData;

    private List<MyReview> myReviews = new ArrayList<>();

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

    public MyProduct(long id, String imageUrl, String title, String price, String description, int quantity, List<MyReview> myReviews) {
        this(id, imageUrl, title, price, description, quantity);
        this.myReviews = myReviews;
    }

}