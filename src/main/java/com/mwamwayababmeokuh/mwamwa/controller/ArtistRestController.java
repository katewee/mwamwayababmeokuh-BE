package com.mwamwayababmeokuh.mwamwa.controller;

import com.mwamwayababmeokuh.mwamwa.domain.Artist;
import com.mwamwayababmeokuh.mwamwa.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ArtistRestController {

    @Autowired
    ArtistService artistService;

    @PostMapping("/artists")
    @ResponseBody
    public Artist save(Artist artist) {
        return artistService.save(artist);
    }

    @GetMapping("/artists")
    @ResponseBody
    public List<Artist> findAll() {
        return artistService.findAll();
    }

}
