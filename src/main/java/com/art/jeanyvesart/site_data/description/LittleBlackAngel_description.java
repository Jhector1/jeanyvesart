package com.art.jeanyvesart.site_data.description;

import java.util.ArrayList;
import java.util.List;



public class LittleBlackAngel_description implements Description {
    public LittleBlackAngel_description() {
        setLittleBlackAngelDescriptionsData();
    }

    public List<String> littleBlackAngelDescriptions = new ArrayList<>();
    String all_the_universe_is_with_you = ""/*"The painting titled \"All the Universe is with You\" " +
            "is a captivating work of art that combines the enchantment of cosmic elements with the innocence and" +
            " joy of a young soul. \n" +
            "At the forefront of the painting stands a little girl, her face adorned with a radiant and" +
            " happy expression. Her eyes sparkle with delight, reflecting her connection to the universe. Around " +
            "the girl's neck rests a magnificent, magical necklace. The necklace glimmers with ethereal light and " +
            "intricate details, symbolizing a cosmic connection.\n" +
            "The canvas is alive with the presence of planets and galaxies scattered across the background. " +
            "The planets exhibit a variety of shapes, sizes, and colors, creating a visually captivating scene. " +
            "Galaxies swirl and intertwine, filling the space with their breathtaking beauty. Stars twinkle and shine," +
            " casting a celestial glow upon the entire composition.\n" +
            "Through this enchanting artwork, viewers are encouraged to embrace their inner child, to believe in the " +
            "limitless possibilities that lie within, and to cherish the interconnectedness between themselves and the " +
            "universe. It serves as a reminder that we are all part of a grand cosmic tapestry, and that the universe " +
            "is always with us, guiding us on our journey of discovery and joy.\n"*/;


    public void setLittleBlackAngelDescriptionsData() {
        littleBlackAngelDescriptions.add(all_the_universe_is_with_you);

    }

    @Override
    public List<String> getDescriptionList() {
        return littleBlackAngelDescriptions;
    }

}
