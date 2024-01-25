package com.art.jeanyvesart.site_data.paintings;



import com.art.jeanyvesart.model.Artwork;
import com.art.jeanyvesart.model.MyProduct;
import com.art.jeanyvesart.site_data.description.ArtworkDescription;
import com.art.jeanyvesart.site_data.description.LittleBlackAngel_description;

import java.util.ArrayList;
import java.util.List;

public class LittleBlackAngel implements Serie {
    ArtworkDescription artworkDescription = new ArtworkDescription();
    public List<String> littleBlackAngelDescriptions =
            artworkDescription.getDescriptions(() ->
                    new LittleBlackAngel_description().getDescriptionList());
    private final List<MyProduct> littleBlackAngelSeries=new ArrayList<>();
    public LittleBlackAngel() {
        setLittleBlackAngelArtworkList();
    }
    public static void main(String[] args){

    }
    private void setLittleBlackAngelArtworkList() {
        String description = "description";
        littleBlackAngelSeries.add(
                new Artwork(14,"/images/paintings/little_black_angel/angel0.webp",
                        "Piman Zwazo", "350",
                        "Oil on canvas","20x20inch", description,1,new ArrayList<>()
                ));
        littleBlackAngelSeries.add(
                new Artwork(15,"/images/paintings/little_black_angel/angel1.webp",
                        "Queen Brigitte", "350",
                        "Oil on canvas","18x20inch", description,1,new ArrayList<>()
                ));
        littleBlackAngelSeries.add(
                new Artwork(16,"/images/paintings/little_black_angel/angel2.webp",
                        "Queen Freda", "350",
                        "Oil on canvas","20x20inch", description,1,new ArrayList<>()
                ));
        littleBlackAngelSeries.add(
                new Artwork(17,"/images/paintings/little_black_angel/angel3.webp",
                        "Oh dear", "350",
                        "Oil on canvas","20x20inch", description,1,new ArrayList<>()
                ));
        littleBlackAngelSeries.add(
                new Artwork(18,"/images/paintings/little_black_angel/angel4.webp",
                        "All the universe is with you", "450",
                        "Oil on canvas","20x20inch", littleBlackAngelDescriptions.get(0),1,new ArrayList<>()
                ));
        littleBlackAngelSeries.add(
                new Artwork(19,"/images/paintings/little_black_angel/angel5.webp",
                        "Piman Bouk", "380",
                        "Oil on canvas","20x20inch", description,1,new ArrayList<>()
                ));
    }


    @Override
    public List<MyProduct> getArtworkList() {
        return this.littleBlackAngelSeries;
    }
}
