package com.art.jeanyvesart.site_data.paintings;

import com.art.jeanyvesart.model.Artwork;
import com.art.jeanyvesart.model.MyProduct;

import java.util.ArrayList;
import java.util.List;

public class Renaissance implements Serie {
    private final List<MyProduct> renaissanceSeries=new ArrayList<>();
    public Renaissance() {
        setRenaissanceArtworkList();
    }
    public static void main(String[] args){

    }
    private void setRenaissanceArtworkList() {
        String description = "description";
        renaissanceSeries.add(
                new Artwork(20,"/images/paintings/renaissance/renaissance0.webp",
                        "Greener in the dark", "90",
                        "Acrylic on canvas","8x8inch", description,1,new ArrayList<>()
                ));
        renaissanceSeries.add(
                new Artwork(21,"/images/paintings/renaissance/renaissance1.webp",
                        "Greener in love", "90",
                        "Acrylic on canvas","8x8inch", description,1,new ArrayList<>()
                ));
        renaissanceSeries.add(
                new Artwork(22,"/images/paintings/renaissance/renaissance2.webp",
                        "Greener in sorrow", "90",
                        "Acrylic on canvas","8x8inch", description,1,new ArrayList<>()
                ));
        renaissanceSeries.add(
                new Artwork(23,"/images/paintings/renaissance/renaissance3.webp",
                        "Greener in the green", "90",
                        "Acrylic on canvas","8x8inch", description,1,new ArrayList<>()
                ));
        renaissanceSeries.add(
                new Artwork(24,"/images/paintings/renaissance/renaissance4.webp",
                        "Freedom to choose the life you want", "90",
                        "Oil on canvas","8x8inch", description,1,new ArrayList<>()
                ));
        renaissanceSeries.add(
                new Artwork(25,"/images/paintings/renaissance/renaissance5.webp",
                        "Freedom to choose the life you want", "90",
                        "Oil on canvas","8x8inch", description,1,new ArrayList<>()
                ));
        renaissanceSeries.add(
                new Artwork(26,"/images/paintings/renaissance/renaissance6.webp",
                        "Freedom to choose the life you want", "90",
                        "Oil on canvas","8x8inch", description,1,new ArrayList<>()
                ));
        renaissanceSeries.add(
                new Artwork(27,"/images/paintings/renaissance/renaissance7.webp",
                        "Freedom to choose the life you want", "90",
                        "Oil on canvas","8x8inch", description,1,new ArrayList<>()
                ));
        renaissanceSeries.add(
                new Artwork(28,"/images/paintings/renaissance/renaissance8.webp",
                        "Rafael and Michael", "unavailable",
                        "Oil on canvas","126x80inch", description,1,new ArrayList<>()
                ));
        renaissanceSeries.add(
                new Artwork(29,"/images/paintings/renaissance/renaissance9.webp",
                        "The Fruit of Resilience", "unavailable",
                        "Oil on canvas","8x8inch", description,1,new ArrayList<>()
                ));
    }


    @Override
    public List<MyProduct> getArtworkList() {
        return this.renaissanceSeries;
    }
}
