package com.art.jeanyvesart.site_data.description;

import java.util.ArrayList;
import java.util.List;

public class Healing_description implements Description {
    public Healing_description() {
        setHealingPlantDescriptionsData();
    }

    public List<String> healingPlantsDescriptions = new ArrayList<>();
    String assorossi_description = ""/*"The \"Assorrossi Dose tryptich\" is a captivating series of three paintings " +
            "that pay homage to the healing power of Assorrossi, a revered Haitian herb known for its " +
            "effectiveness in treating a variety of common ailments. Each painting within the trilogy explores " +
            "the theme of herbal medicine and invites viewers into a world of natural remedies and holistic healing."*/;



    public void setHealingPlantDescriptionsData() {
        healingPlantsDescriptions.add(assorossi_description);
        healingPlantsDescriptions.add(assorossi_description);
        healingPlantsDescriptions.add(assorossi_description);
    }

    @Override
    public List<String> getDescriptionList() {
        return healingPlantsDescriptions;
    }
}