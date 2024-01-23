package com.mwamwayababmeokuh.mwamwa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArtistController {

    @GetMapping("/artist_save")
    public String artist_save() {
        return "artist/artistForm";
    }

}
