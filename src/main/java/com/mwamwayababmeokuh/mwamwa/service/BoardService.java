package com.mwamwayababmeokuh.mwamwa.service;

import com.mwamwayababmeokuh.mwamwa.domain.Post;
import com.mwamwayababmeokuh.mwamwa.domain.PostDTO;
import com.mwamwayababmeokuh.mwamwa.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class BoardService {

    @Autowired
    BoardRepository boardRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public PostDTO save(PostDTO postDTO) {
        log.info("save()" + postDTO.toString());
        Post post = modelMapper.map(postDTO, Post.class);
        Post result = boardRepository.save(post);
        return modelMapper.map(result, PostDTO.class);
    }

    public void deleteById(long pid) {
        log.info("deleteById()" + pid);
        boardRepository.deleteById(pid);
    }

    public PostDTO update(PostDTO postDTO) {
        log.info("update()" + postDTO.toString());
        Optional<Post> optionalPost = boardRepository.findById(postDTO.getPid());
        Post post = optionalPost.orElseThrow(NoSuchElementException::new);
        post.setAid(postDTO.getAid());
        post.setHashtag(postDTO.getHashtag());
        post.setContent(postDTO.getContent());
        post.setLng(postDTO.getLng());
        post.setLat(postDTO.getLat());
        return modelMapper.map(post, PostDTO.class);
    }
}
