package com.mwamwayababmeokuh.mwamwa.service;

import com.mwamwayababmeokuh.mwamwa.domain.Artist;
import com.mwamwayababmeokuh.mwamwa.domain.ArtistDTO;
import com.mwamwayababmeokuh.mwamwa.repository.ArtistRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ArtistService {

    @Autowired
    ArtistRepository artistRepository;

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
}
