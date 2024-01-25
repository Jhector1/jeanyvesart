package com.art.jeanyvesart.site_data.metadata;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class InventoryMetadata {
    private List<String> images;
    private int quantity;
public InventoryMetadata(){
    this.images = new ArrayList<>();
    this.quantity = 1;
}
    public InventoryMetadata(List<String> images){
        this.images = images;
        this.quantity = 1;
    }
    public InventoryMetadata(int quantity){
        this.images = new ArrayList<>();
        this.quantity = quantity;
    }



}

