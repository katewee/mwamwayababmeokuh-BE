package com.mwamwayababmeokuh.mwamwa.service;

import com.mwamwayababmeokuh.mwamwa.domain.Hashtag;
import com.mwamwayababmeokuh.mwamwa.domain.HashtagDTO;
import com.mwamwayababmeokuh.mwamwa.repository.HashtagRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<HashtagDTO> find(HashtagDTO hashtagDTO) {
        log.info("find()");
        log.info(hashtagDTO.toString());
        String searchKeyword = hashtagDTO.getHashtag();

        List<HashtagDTO> list = hashtagRepository.findByHashtagContaining(searchKeyword).stream().map(m -> new HashtagDTO(m.getHid(), m.getHashtag())).collect(Collectors.toList());
        log.info(list.toString());
        return list;
    }

    public void deleteById(HashtagDTO hashtagDTO) {
        log.info("deleteById()" + hashtagDTO.toString());
        hashtagRepository.deleteById(hashtagDTO.getHid());
    }

}
