package com.art.jeanyvesart.controller;

import com.art.jeanyvesart.helper.api.Consumer;
import com.art.jeanyvesart.model.Inventory;
import com.art.jeanyvesart.model.MyProduct;
import com.art.jeanyvesart.site_data.paintings.*;
import com.art.jeanyvesart.site_data.print.GreenEnergy;
import com.luciad.imageio.webp.WebPImageReaderSpi;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@Controller
@Slf4j
@SessionAttributes({"enlightenment","green_energy", "jackets", "stripePublicKey", "renaissance", "little_black_angels", "healing_plants"})



@RequestMapping("/artworks")
public class ArtworkController {
    private final Paintings paintings = new Paintings();

    private final Consumer<Inventory> consumer;

    @Value(value = "${stripe.public.key}")
    private String stripePublicKey;

    public ArtworkController(Consumer<Inventory> consumer) {
        this.consumer = consumer;
    }


    @GetMapping("/crafts")
    String display_artworks_crafts(Model model) {
        model.addAttribute("allArtParent", "allArtParent");
        return "artworks/handcrafts_page";
    }

    @GetMapping
    String display_artworks(Model model) {
        model.addAttribute("allArtParent", "allArtParent");
        return "artworks/artworks_page";
    }

    @GetMapping("/drawings")
    String display_artworks_drawings(Model model) {
        model.addAttribute("allArtParent", "allArtParent");
        return "artworks/drawing_page";
    }

    @GetMapping("/clothes")
    String display_clothes_drawings(Model model) {
        model.addAttribute("allArtParent", "allArtParent");
        return "artworks/clothing_page";
    }

    @GetMapping("/paintings")
    String display_artworks_paintings(HttpServletResponse request, Model model) {
        model.addAttribute("allArtParent", "allArtParent");

        request.setHeader("Request-Id", "12345");
        return "artworks/painting_page";
    }
    @GetMapping("/print")
    String display_artworks_prints(Model model) {
        model.addAttribute("allArtParent", "allArtParent");
        return "artworks/print_page";
    }

    @ModelAttribute("healing_plants")
    public Iterable<MyProduct> displayHealing_plantsSeries() {
        return paintings.getSeries(() -> new HealingPlants().getArtworkList());
        //return allArtworkRepository.findAllBySeriesContainingIgnoreCase("healing_plants");
    }
    @ModelAttribute("green_energy")
    public Iterable<MyProduct> displayGreen_energySeries() {
        return paintings.getSeries(() -> new GreenEnergy().getArtworkList());
        //return allArtworkRepository.findAllBySeriesContainingIgnoreCase("healing_plants");
    }
    @ModelAttribute("enlightenment")
    public Iterable<MyProduct> displayEnlightenmentSeries() {
        return paintings.getSeries(() -> new Enlightenment().getArtworkList());
       // return allArtworkRepository.findAllBySeriesContainingIgnoreCase("enlightenment");

    }

    @ModelAttribute("little_black_angels")
    public Iterable<MyProduct> displayLittleBlackAngelSeries() {
        return paintings.getSeries(() -> new LittleBlackAngel().getArtworkList());
        //return allArtworkRepository.findAllBySeriesContainingIgnoreCase("little_black_angel");

    }

    @ModelAttribute("jackets")
    public Iterable<MyProduct> displayJacketSeries() {
        return paintings.getSeries(() -> new Clothe().getArtworkList());
        //return allArtworkRepository.findAllBySeriesContainingIgnoreCase("jacket");


    }

    @ModelAttribute("renaissance")
    public Iterable<MyProduct> displayRenaissanceSeries() {
        return paintings.getSeries(() -> new Renaissance().getArtworkList());
//        return allArtworkRepository.findAllBySeriesContainingIgnoreCase("renaissance");


    }

//    @PostMapping
//    public String processWishList(@Valid Artwork artwork, SessionStatus sesssionStatus) {
//        System.out.println(artwork.getImageUrl() + " Yeah it submit");
//        log.info("Processing taco: {}", artwork);
//
//        sesssionStatus.setComplete();
//        return "redirect:/artworks";
//    }

    @GetMapping("/{id}")
    public String displaySingleArtwork(@PathVariable String id, Model model) throws URISyntaxException, IOException {
        int artworkId = Integer.parseInt(id.substring(5));
        Optional<Inventory> productInventory = consumer.getResourceById("/data/artworks/{" + id + "}", id, Inventory.class);

        if(productInventory.isPresent()) {

            MyProduct artwork = productInventory.get().getMyProduct();
        //    log.info("object product, {}", artwork);
            String artworkImageUrl = artwork.getImageUrl().trim();
            ImageIO.scanForPlugins();
            ImageIO.getImageReadersByMIMEType("image/webp").next();

            WebPImageReaderSpi spi = new WebPImageReaderSpi();
            ImageReader reader = spi.createReaderInstance();
            reader.setInput(ImageIO.createImageInputStream(new URI("https://jeanyveshector.com"+artworkImageUrl).toURL().openStream()));
            //URI imageUrl = new URI(artworkImageUrl);
            BufferedImage artworkImj = reader.read(0);
            double width = artworkImj.getWidth();
            double height = artworkImj.getHeight();
            double aspectRatio = width / height;
            model.addAttribute("artwork_id", id);
            model.addAttribute("stripePublicKey", stripePublicKey);
            model.addAttribute("numberOfArtworks", 90);
            model.addAttribute("ogImage", artwork.getImageUrl());
            model.addAttribute("ogUrl", "https://jeanyveshector.com/artworks/" + id);
            model.addAttribute("ogDescription", artwork.getDescription());
            model.addAttribute("ogTitle", artwork.getTitle() + " - ");
            model.addAttribute("ogWidth", width);
            model.addAttribute("ogHeight", height);
            model.addAttribute("ogAspectRatio", aspectRatio);
            model.addAttribute("inventory", productInventory.get().getQuantity());
            return "artworks/artwork_link_display";
        }
        return "404";

    }
}
