package com.mwamwayababmeokuh.mwamwa.controller;

import com.mwamwayababmeokuh.mwamwa.domain.Artist;
import com.mwamwayababmeokuh.mwamwa.domain.ArtistDTO;
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
    public ArtistDTO save(ArtistDTO artistDTO) {
        return artistService.save(artistDTO);
    }

    @GetMapping("/artists")
    @ResponseBody
    public List<ArtistDTO> findAll() {
        return artistService.findAll();
    }

    @GetMapping("/artists/search")
    @ResponseBody
    public List<ArtistDTO> findByName(ArtistDTO artistDTO) {
        return artistService.findByNameContaining(artistDTO);
    }

}
