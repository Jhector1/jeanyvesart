package com.art.jeanyvesart.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MyReview implements Serializable {

    private long id;

    private MyCustomer myCustomer;
    private String headline;

    private String reviewText;
    private MyProduct product;
    private int rating;
    private Date date = new Date();

    private byte[] imageData;
    private String imageName;
}
