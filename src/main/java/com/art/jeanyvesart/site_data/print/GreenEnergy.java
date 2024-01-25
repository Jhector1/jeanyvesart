package com.art.jeanyvesart.site_data.print;



import com.art.jeanyvesart.model.Artwork;
import com.art.jeanyvesart.model.MyProduct;
import com.art.jeanyvesart.site_data.paintings.Serie;

import java.util.ArrayList;
import java.util.List;

public class GreenEnergy implements Serie {
    private final List<MyProduct> greenEnergySeries=new ArrayList<>();
    public GreenEnergy() {
        setGreenEnergyList();
    }

    private void setGreenEnergyList() {
        String description = "description";
        String extension = "webp";
        greenEnergySeries.add(
                new Artwork(35,"/images/prints/green_energy/green_energy0.webp",
                        "Protector", "120",
                        "print on arches 88 paper","12x16inch", description,1,new ArrayList<>()
                ));

        greenEnergySeries.add(
                new Artwork(36,"/images/prints/green_energy/green_energy1.webp",
                        "Protector", "120",
                        "print on arches 88 paper","12x16inch",description,1,new ArrayList<>()
                ));
        greenEnergySeries.add(
                new Artwork(37,"/images/prints/green_energy/green_energy2.webp",
                        "Protector", "120",
                        "print on arches 88 paper","12x16inch",description,1,new ArrayList<>()
                ));
        greenEnergySeries.add(
                new Artwork(38,"/images/prints/green_energy/green_energy3.webp",
                        "Lonesome Green Halo", "120",
                        "print on arches 88 paper","12x12inch",description,1,new ArrayList<>()
                ));
        greenEnergySeries.add(
                new Artwork(39,"/images/prints/green_energy/green_energy4.webp",
                        "Lonesome Green Halo", "120",
                        "print on arches 88 paper","12x12inch",description,1,new ArrayList<>()
                ));
        greenEnergySeries.add(
                new Artwork(40,"/images/prints/green_energy/green_energy5.webp",
                        "Lonesome Lover", "120",
                        "print on arches 88 paper","12x12inch",description,1,new ArrayList<>()
                ));
        greenEnergySeries.add(
                new Artwork(41,"/images/prints/green_energy/green_energy6.webp",
                        "Snapshot of a beautiful burnt-out era", "120",
                        "print on arches 88 paper","12x12inch", description,1,new ArrayList<>()
                ));    greenEnergySeries.add(
                new Artwork(42,"/images/prints/green_energy/green_energy7.webp",
                        "Snapshot of a time when the Gods use to party", "120",
                        "print on arches 88 paper","12x12inch", description,1,new ArrayList<>()
                ));    greenEnergySeries.add(
                new Artwork(43,"/images/prints/green_energy/green_energy8.webp",
                        "Snapshot of the rising sun", "120",
                        "print on arches 88 paper","12x12inch", description,1,new ArrayList<>()
                ));
        greenEnergySeries.add(
                new Artwork(44,"/images/prints/green_energy/green_energy9.webp",
                        "Snapshot of a time without bubble", "120",
                        "print on arches 88 paper","12x12inch", description,1,new ArrayList<>()
                ));
    }


    @Override
    public List<MyProduct> getArtworkList() {
        return this.greenEnergySeries;
    }
}
