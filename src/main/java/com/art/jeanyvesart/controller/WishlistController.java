package com.art.jeanyvesart.controller;

import com.art.jeanyvesart.component.EndPointPool;
import com.art.jeanyvesart.model.Artwork;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Slf4j

@Controller
@RequestMapping("/wishlists")
@SessionAttributes("artwork")

public class WishlistController {
    @GetMapping
    public String getWhishlist(Model model) {
        model.addAttribute("userFavoriteEndpoint", EndPointPool.getMap().get("userFavoriteEndpoint"));


        return "favorite_list_page";
    }

    @ModelAttribute(name = "artwork")
    public Artwork artwork() {
        return new Artwork();
    }
}