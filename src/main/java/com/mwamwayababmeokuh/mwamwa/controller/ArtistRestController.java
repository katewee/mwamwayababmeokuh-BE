package com.mwamwayababmeokuh.mwamwa.controller;

import com.mwamwayababmeokuh.mwamwa.domain.Artist;
import com.mwamwayababmeokuh.mwamwa.domain.ArtistDTO;
import com.mwamwayababmeokuh.mwamwa.domain.FavoriteDTO;
import com.mwamwayababmeokuh.mwamwa.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/artists/favorites")
    @ResponseBody
    public List<FavoriteDTO> saveFavorites(@RequestBody List<FavoriteDTO> favoriteDTOS) {
        return artistService.saveFavorites(favoriteDTOS);
    }

    @DeleteMapping("/artists/favorites")
    @ResponseBody
    public Map<String, String> deleteFavorite(FavoriteDTO favoriteDTO) {
        Map<String, String> map = new HashMap<>();
        artistService.deleteFavorite(favoriteDTO);
        map.put("result", "OK");
        return map;
    }

    @GetMapping("/artists/favorites")
    @ResponseBody
    public List<ArtistDTO> searchFavorites(FavoriteDTO favoriteDTO) {
        return artistService.searchFavorites(favoriteDTO);
    }

}
