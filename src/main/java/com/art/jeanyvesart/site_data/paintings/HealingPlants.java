package com.art.jeanyvesart.site_data.paintings;


import com.art.jeanyvesart.model.Artwork;
import com.art.jeanyvesart.model.MyProduct;
import com.art.jeanyvesart.site_data.description.ArtworkDescription;
import com.art.jeanyvesart.site_data.description.Healing_description;

import java.util.ArrayList;
import java.util.List;

public class HealingPlants implements Serie {
    String description = "description";


    private final List<MyProduct> healingPlantsSeries = new ArrayList<>();
    ArtworkDescription artworkDescription = new ArtworkDescription();

    public List<String> healingPlantDescriptions =
            artworkDescription.getDescriptions(() ->
                    new Healing_description().getDescriptionList());
    public HealingPlants() {
        setEnlightenmentArtworkList();

    }

    private void setEnlightenmentArtworkList() {
        healingPlantsSeries.add(
                new Artwork(6, "/images/paintings/healing_plants/healing0.webp",
                        "Assorrossi Dose I", "360",
                        "Oil and Acrylic on canvas", "12x12inch (The price cover all three paintings)", healingPlantDescriptions.get(0),1,new ArrayList<>()
                ));

        healingPlantsSeries.add(
                new Artwork(7, "/images/paintings/healing_plants/healing1.webp",
                        "Assorrossi Dose II", "360",
                        "Oil and Acrylic on canvas", "12x12inch (The price cover all three paintings)", healingPlantDescriptions.get(0),1,new ArrayList<>()
                ));

        healingPlantsSeries.add(
                new Artwork(8, "/images/paintings/healing_plants/healing2.webp",
                        "Assorrossi Dose III", "360",
                        "Oil and Acrylic on canvas", "12x12inch (The price cover all three paintings)", healingPlantDescriptions.get(0),1,new ArrayList<>()
                ));
        healingPlantsSeries.add(
                new Artwork(9, "/images/paintings/healing_plants/healing3.webp",
                        "Blossom Season", "unavailable",
                        "Oil on canvas", "20x20inch", description,1,new ArrayList<>()
                ));
        healingPlantsSeries.add(
                new Artwork(10, "/images/paintings/healing_plants/healing4.webp",
                        "Blessed Thistle", "350",
                        "Oil and Acrylic on canvas", "24x24inch", description,1,new ArrayList<>()
                ));
        healingPlantsSeries.add(
                new Artwork(11, "/images/paintings/healing_plants/healing5.webp",
                        "Garden of the red Angel", "320",
                        "Oil on canvas", "24x24inch", description,1,new ArrayList<>()
                ));
        healingPlantsSeries.add(
                new Artwork(12, "/images/paintings/healing_plants/healing6.webp",
                        "Moon Trees", "unavailable",
                        "Oil and Acrylic on canvas", "12x12inch", description,1,new ArrayList<>()
                ));
        healingPlantsSeries.add(
                new Artwork(13, "/images/paintings/healing_plants/healing7.webp",
                        "The Forest", "250",
                        "Oil on canvas", "12x12inch", description,1,new ArrayList<>()
                ));


    }


    @Override
    public List<MyProduct> getArtworkList() {
        return this.healingPlantsSeries;
    }
}
