package com.mwamwayababmeokuh.mwamwa.service;

import com.mwamwayababmeokuh.mwamwa.domain.Like;
import com.mwamwayababmeokuh.mwamwa.domain.LikeDTO;
import com.mwamwayababmeokuh.mwamwa.repository.LikeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class LikeService {

    @Autowired
    LikeRepository likeRepository;

    private final ModelMapper modelMapper = new ModelMapper();


    public LikeDTO save(LikeDTO likeDTO) {
        log.info("save()" + likeDTO.toString());
        Like like = modelMapper.map(likeDTO, Like.class);
        Like result = likeRepository.save(like);
        return modelMapper.map(result, LikeDTO.class);
    }

    public void delete(LikeDTO likeDTO) {
        log.info("delete()" + likeDTO.toString());
        likeRepository.deleteByUidAndPid(likeDTO.getUid(), likeDTO.getPid());
    }

    public LikeDTO findByUidAndPid(LikeDTO likeDTO) {
        log.info("findByUidAndPid()" + likeDTO.toString());
        Optional<Like> optionalLike = likeRepository.findByUidAndPid(likeDTO.getUid(), likeDTO.getPid());
        return modelMapper.map(optionalLike.orElseThrow(NoSuchElementException::new), LikeDTO.class);
    }
}
