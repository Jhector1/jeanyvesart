package com.art.jeanyvesart.site_data.paintings;


import com.art.jeanyvesart.model.MyProduct;

import java.util.ArrayList;
import java.util.List;

public class Paintings {
    List<MyProduct> allMyProducts = new ArrayList<>();
  public List<MyProduct> getSeries(Serie serie) {

      return serie.getArtworkList();
    }

}
