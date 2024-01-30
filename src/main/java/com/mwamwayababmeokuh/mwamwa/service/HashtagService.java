package com.mwamwayababmeokuh.mwamwa.service;

import com.mwamwayababmeokuh.mwamwa.domain.Hashtag;
import com.mwamwayababmeokuh.mwamwa.domain.HashtagDTO;
import com.mwamwayababmeokuh.mwamwa.repository.HashtagRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HashtagService {

    @Autowired
    HashtagRepository hashtagRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public HashtagDTO save(HashtagDTO hashtagDTO) {
        Hashtag entity = modelMapper.map(hashtagDTO, Hashtag.class);
        log.info("entity: " + entity.toString());
        Hashtag result = hashtagRepository.save(entity);
        return modelMapper.map(result, HashtagDTO.class);
    }

}
