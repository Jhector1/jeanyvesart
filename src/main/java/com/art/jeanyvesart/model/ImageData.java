package com.art.jeanyvesart.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor


@AllArgsConstructor
public class ImageData {

    private Long id;
    private String name;
    private String type;

    private String base64Image;

}
