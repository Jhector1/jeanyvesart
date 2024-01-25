package com.art.jeanyvesart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence_generator")
//    @SequenceGenerator(name = "my_sequence_generator", sequenceName = "my_sequence", initialValue = 0, allocationSize = 1)
    private String id;
    private MyProduct myProduct;
    private String category;
    private int quantity;
    List<String>  metadata = new ArrayList<>();
}
