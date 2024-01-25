package com.art.jeanyvesart.site_data.paintings;




import com.art.jeanyvesart.model.Artwork;
import com.art.jeanyvesart.model.MyProduct;
import com.art.jeanyvesart.site_data.description.ArtworkDescription;
import com.art.jeanyvesart.site_data.description.Enlightenment_description;

import java.util.ArrayList;
import java.util.List;

public class Enlightenment implements Serie {
    boolean unique = true;
    ArtworkDescription artworkDescription = new ArtworkDescription();
    private final List<MyProduct> enlightenmentSeries = new ArrayList<>();
public List<String> enlightenmentDescriptions =
        artworkDescription.getDescriptions(() ->
                new Enlightenment_description().getDescriptionList());
    public Enlightenment() {
        setEnlightenmentArtworkList();
    }

    public static void main(String[] args) {

    }

    private void setEnlightenmentArtworkList() {
        String description = "description";
        enlightenmentSeries.add(
                new Artwork(0, "/images/paintings/enlightenment/enlightenment0.webp",
                        "Dear Time", "1800",
                        "Oil and Acrylic on canvas", "36x48inch", enlightenmentDescriptions.get(0),1,new ArrayList<>()
                ));
        enlightenmentSeries.add(
                new Artwork(1, "/images/paintings/enlightenment/enlightenment1.webp",
                        "Freedom to choose the life you want", "1800",
                        "Oil and Acrylic on canvas", "36x48inch", enlightenmentDescriptions.get(1),1,new ArrayList<>()
                ));


        enlightenmentSeries.add(
                new Artwork(2, "/images/paintings/enlightenment/enlightenment2.webp",
                        "My garden, my life, my peace", "Unavailable",
                        "Oil and Acrylic on canvas", "36x36inch", enlightenmentDescriptions.get(2),1,new ArrayList<>()
                ));

        enlightenmentSeries.add(
                new Artwork(3, "/images/paintings/enlightenment/enlightenment3.webp",
                        "The prince of Pic Makaya", "700",
                        "Oil and Acrylic on canvas", "20x20inch", enlightenmentDescriptions.get(3),1,new ArrayList<>()
                ));

        enlightenmentSeries.add(
                new Artwork(4, "/images/paintings/enlightenment/enlightenment4.webp",
                        "Her story was the most courageous I have heard", "1780",
                        "Oil and Acrylic on canvas", "36x48inch", description,1,new ArrayList<>()
                ));

        enlightenmentSeries.add(
                new Artwork(5, "/images/paintings/enlightenment/enlightenment5.webp",
                        "Enlighten", "unavailable",
                        "Oil and Acrylic on canvas", "24x24inch", description,1,new ArrayList<>()
                ));


    }


    @Override
    public List<MyProduct> getArtworkList() {
        return this.enlightenmentSeries;
    }
}
