package com.mwamwayababmeokuh.mwamwa.service;

import com.mwamwayababmeokuh.mwamwa.domain.*;
import com.mwamwayababmeokuh.mwamwa.repository.ArtistRepository;
import com.mwamwayababmeokuh.mwamwa.repository.FavoriteRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ArtistService {

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    FavoriteRepository favoriteRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public ArtistDTO save(ArtistDTO artistDTO) {
        log.info("save()" + artistDTO.toString());
        Artist artist = modelMapper.map(artistDTO, Artist.class);
        Artist result = artistRepository.save(artist);
        return modelMapper.map(result, ArtistDTO.class);
    }

    public List<ArtistDTO> findAll() {
        log.info("findAll()");
        List<ArtistDTO> list = artistRepository.findAll().stream()
                .map(m -> modelMapper.map(m, ArtistDTO.class)).collect(Collectors.toList());
        return list;
    }

    public List<ArtistDTO> findByNameContaining(ArtistDTO artistDTO) {
        log.info("findByNameContaining()" + artistDTO.toString());
        List<ArtistDTO> list = artistRepository.findByNameContaining(artistDTO.getName()).stream()
                .map(m -> modelMapper.map(m, ArtistDTO.class)).collect(Collectors.toList());
        return list;
    }

    public List<FavoriteDTO> saveFavorites(List<FavoriteDTO> favoriteDTOS) {
        log.info("saveFavorites()" + favoriteDTOS.toString());
        List<Favorite> favorites = favoriteDTOS.stream()
                .map(m -> modelMapper.map(m, Favorite.class)).collect(Collectors.toList());
        List<FavoriteDTO> list = favoriteRepository.saveAll(favorites).stream()
                .map(m -> modelMapper.map(m, FavoriteDTO.class)).collect(Collectors.toList());
        return list;
    }

    public void deleteFavorite(FavoriteDTO favoriteDTO) {
        log.info("deleteFavorite()" + favoriteDTO.toString());
        favoriteRepository.deleteByUidAndAid(favoriteDTO.getUid(), favoriteDTO.getAid());
    }

    public List<ArtistDTO> searchFavorites(FavoriteDTO favoriteDTO) {
        log.info("searchFavorites()" + favoriteDTO.toString());
        List<ArtistDTO> list = artistRepository.selectSQLByUid(favoriteDTO.getUid()).stream()
                .map(m -> modelMapper.map(m, ArtistDTO.class)).collect(Collectors.toList());
        return list;
    }

    public List<ArtistDTO> updateFavorites(FavoriteInsertDTO favoriteInsertDTO) {
        log.info("updateFavorites()" + favoriteInsertDTO.toString());
        long uid = favoriteInsertDTO.getUid();
        List<Long> newList = favoriteInsertDTO.getAid();
        List<Long> oldList = new ArrayList<>();

        FavoriteDTO favoriteDTO = new FavoriteDTO();
        favoriteDTO.setUid(uid);
        List<ArtistDTO> artistDTOS = searchFavorites(favoriteDTO);
        for(ArtistDTO artistDTO : artistDTOS) {
            oldList.add(artistDTO.getAid());
        }

        List<Long> oldNoneMatchList = oldList.stream().filter(o -> newList.stream()
                .noneMatch(Predicate.isEqual(o))).collect(Collectors.toList());
        List<Long> newNoneMatchList = newList.stream().filter(n -> oldList.stream()
                .noneMatch(Predicate.isEqual(n))).collect(Collectors.toList());

        List<FavoriteDTO> favoriteDTOS = new ArrayList<>();
        for(Long aid : newNoneMatchList) {
            favoriteDTOS.add(new FavoriteDTO(uid, aid));
        }

        favoriteRepository.deleteAllByUidAndAid(uid, oldNoneMatchList);
        saveFavorites(favoriteDTOS);
        return searchFavorites(favoriteDTO);
    }
}
