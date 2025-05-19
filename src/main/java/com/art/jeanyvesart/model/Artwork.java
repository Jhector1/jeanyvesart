package com.art.jeanyvesart.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor


@AllArgsConstructor
public class Artwork extends MyProduct {
    private String medium;
    private String size;
    public Artwork(long id,
                   String imageUrl,
                   String title,
                   String price,
                   String medium,
                   String size,
                   String description,
                   int quantity) {
        super(id, imageUrl,
                title,
                price,
                description,
                quantity
        );
        this.medium = medium;
        this.size = size;
    }
    public Artwork(long id,
                   String imageUrl,
                   String title,
                   String price, String medium,String size,
                   String description,    int quantity,
                   List<MyReview> myReviews

                   ) {
        super(id, imageUrl,
                title,
                price,
                description,
                quantity,
                myReviews
        );
        this.medium = medium;
        this.size = size;

    }


}
