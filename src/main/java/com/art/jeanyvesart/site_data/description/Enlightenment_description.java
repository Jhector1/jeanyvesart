package com.art.jeanyvesart.site_data.description;

import java.util.ArrayList;
import java.util.List;

public class Enlightenment_description implements Description {
    public Enlightenment_description() {
        setEnlightenmentDescriptionsData();
    }

    public List<String> enlightenmentDescriptions = new ArrayList<>();
    String dear_time_description = "The artwork titled \"Dear Time\" depicts a young woman with her skin " +
            "colored in green" +
            ". The woman appears to be looking" +
            " off to the side, with a pensive and contemplative expression on her face. \n" +
            "\n" +
            "The use of green skin suggests a connection to nature and" +
            " perhaps a sense of being \"in tune\" with the environment. The trees that form the woman's hair " +
            "further reinforce this theme, symbolizing growth, change, and the passage of time. \n" +
            "\n" +
            "The title of the artwork, \"Dear Time,\" adds another layer of meaning to the piece, suggesting " +
            "a sense of nostalgia or longing for the past, or perhaps a fear of the future and the unknown. " +
            "The woman's expression and pose seem to convey a sense of introspection and reflection, as if she" +
            " is pondering the passage of time and its impact on her life. \n" +
            "\n" +
            "Overall, \"Dear Time\" is a thought-provoking and evocative artwork that uses striking " +
            "imagery and symbolism to explore themes of nature, growth, and the passage of time.";


    String freedom_to_choose_your_life = "This piece depicts a young " +
            "woman with a happy and carefree expression on her face, with a colorful " +
            "green bouquet flowers that represent her hair. The woman is shown with a bright and vibrant skin color " +
            "in shades of green, which further adds to the cheerful and optimistic tone of the artwork.\n" +
            "\n" +
            "The overall composition of the artwork suggests a sense of freedom and empowerment, with the woman " +
            "standing confidently and boldly, as if asserting her right to choose her own path in life. The use of " +
            "vibrant colors and bold lines reinforces this theme, evoking a sense of energy and vitality.\n" +
            "\n" +
            "The title of the artwork, \"Freedom to Choose the Life You Want,\"  suggesting a celebration of " +
            "individuality, self-expression, and the pursuit of one's own " +
            "dreams and aspirations. The green bouquet flowers that form the woman's hair further reinforce this idea," +
            " symbolizing growth, renewal, and the possibility of infinite potential.\n" +
            "\n" +
            "In a nutshell, \"Freedom to Choose the Life You Want\" is an uplifting and inspiring artwork that " +
            "celebrates the power of choice and individuality, and encourages viewers to embrace their own unique " +
            "path in life.";
    String my_garden_my_life = "The artwork titled \"My Garden, My Life, My Peace\" depicts a young woman " +
            " standing in front of a serene blue background. The woman's eyes are closed and she wears a pensive yet " +
            "peaceful expression on her face, suggesting a deep connection with the universe.\n" +
            "\n" +
            "The overall composition of the artwork suggests a sense of tranquility and introspection, with the woman " +
            "appearing to be lost in thought and contemplation. The use of soft colors and gentle lines reinforces this " +
            "theme, creating a calming and peaceful atmosphere.\n" +
            "\n" +
            "The title of the artwork, \"My Garden, My Life, My Peace,\"" +
            " suggesting a personal and intimate connection with nature and the natural world. " +
            "\"My Garden, My Life, My Peace\" is a reflective and introspective artwork that celebrates the beauty" +
            " and serenity of nature, and encourages viewers to find their own sense of peace and connection with the" +
            " natural world.";
    String prince_of_pic_macaya = "Pic Macaya is a mountain peak located in the southern region of Haiti." +
            " It is part of the larger Massif de la Hotte mountain range, which is known for its biodiversity " +
            "and unique plant and animal species. This is a representation of a young man who is connected to nature " +
            "and possesses a deep knowledge of the forest. The use of the color green likely creates a sense of " +
            "tranquility and harmony with nature, while also symbolizing growth, renewal, and life.\n" +
            "\n" +
            "The young man being depicted as a prince suggests that he has a sense of authority and wisdom, further " +
            "emphasized by his knowledge of the forest's herbs. This indicate that he is a caretaker or protector " +
            "of the forest, and is in tune with its natural rhythms and cycles.\n" +
            "\n" +
            "The piece conveys a sense of reverence for nature and the wisdom that can be gained " +
            "from observing and learning from it. The use of the color green and the imagery of the young " +
            "man as a prince create a sense of beauty and wonder that invites the viewer to connect with nature " +
            "in a deeper way.\n";

    public void setEnlightenmentDescriptionsData() {
        enlightenmentDescriptions.add(dear_time_description);
        enlightenmentDescriptions.add(freedom_to_choose_your_life);
        enlightenmentDescriptions.add(my_garden_my_life);
        enlightenmentDescriptions.add(prince_of_pic_macaya);
    }

    @Override
    public List<String> getDescriptionList() {
        return enlightenmentDescriptions;
    }
}