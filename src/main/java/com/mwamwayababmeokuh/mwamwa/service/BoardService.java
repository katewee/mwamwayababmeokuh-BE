package com.mwamwayababmeokuh.mwamwa.service;

import com.mwamwayababmeokuh.mwamwa.domain.*;
import com.mwamwayababmeokuh.mwamwa.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

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
        return boardRepository.selectOneSQLByPid(result.getPid());
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

    public List<PostDTO> findAllByWriter(long uid) {
        log.info("findAllByWriter()" + uid);
        List<PostDTO> list = boardRepository.findAllByWriterOrderByCreatedAtDesc(uid);
        return list;
    }

    public PostDTO findById(long pid) {
        log.info("findById()" + pid);
        PostDTO postDTO = boardRepository.selectOneSQLByPid(pid);
        return postDTO;
    }

    public List<PostDTO> findAllSqlByUid(LikeDTO likeDTO) {
        log.info("findAllLikeSqlByUid()" + likeDTO.toString());
        List<PostDTO> list = boardRepository.selectSQLByUid(likeDTO.getUid());
        return list;
    }

    public List<HashtagDTO> selectHashtagWithRankSql() {
        log.info("selectHashtagWithRankSql()");
        LocalDate yesterday = LocalDate.now().minusDays(1);
        log.info("date: " + yesterday.toString());
        List<HashtagDTO> list = boardRepository.selectHashtagSQL(yesterday.toString());
        return list;
    }

    public List<PostDTO> findByAid(List<Long> aidList) {
        log.info("findAllByAid()" + aidList.toString());
        List<PostDTO> list = boardRepository.selectSQLByAidInOrderByCreatedAtDesc(aidList);
        return list;
    }

    public PostSearchDTO findPosts(String searchKeyword) {
        log.info("findPosts()" + searchKeyword);
        List<PostDTO> byHashtag = boardRepository.findByHashtagContaining(searchKeyword);
        List<PostDTO> byArtist = boardRepository.selectSQLByArtist(searchKeyword);
        PostSearchDTO postSearchDTO = new PostSearchDTO(byHashtag, byArtist);

        return postSearchDTO;
    }
}
