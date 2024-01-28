package com.art.jeanyvesart.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String country;
    private String fullname;
    //@NotBlank(message = "Delivery street is required")

    private String street;
    //@NotBlank(message = "Delivery city is required")

    private String city;
    //@NotBlank(message = "Delivery state is required")
    private String state;
    //@NotBlank(message = "Zip code is required")
    private String apartment;
    private String zip;
}