package com.art.jeanyvesart.site_data.paintings;


import com.art.jeanyvesart.model.Artwork;
import com.art.jeanyvesart.model.MyProduct;

import java.util.ArrayList;
import java.util.List;

public class Clothe implements Serie {
    private final List<MyProduct> jacketSeries=new ArrayList<>();
    public Clothe() {
        setJacketList();
    }
    public static void main(String[] args){

    }
    private void setJacketList() {
        String description = "description";
        String extension = "webp";
        jacketSeries.add(
                new Artwork(30,"/images/clothes/jacket/jacket0.webp",
                        "Kostim Mèt Kalfou", "90",
                        "","Medium", description,1,new ArrayList<>()
                ));
        jacketSeries.add(
                new Artwork(31,"/images/clothes/jacket/jacket1.webp",
                        "Jacket Freda", "90",
                        "","Medium", description,1,new ArrayList<>()
                ));
        jacketSeries.add(
                new Artwork(32,"/images/clothes/jacket/jacket2.webp",
                        "Jacket Beni", "Unavailable",
                        "","Medium", description,1,new ArrayList<>()
                ));
        jacketSeries.add(
                new Artwork(33,"/images/clothes/jacket/jacket3.webp",
                        "Jacket Danmbala", "90",
                        "","Medium", description,1,new ArrayList<>()
                ));
        jacketSeries.add(
                new Artwork(34,"/images/clothes/jacket/jacket4.webp",
                        "Jacket Beni", "Unavailable",
                        "","Medium", description,1,new ArrayList<>()
                ));
    }


    @Override
    public List<MyProduct> getArtworkList() {
        return this.jacketSeries;
    }
}
