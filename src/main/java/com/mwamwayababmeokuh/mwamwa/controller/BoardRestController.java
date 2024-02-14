package com.mwamwayababmeokuh.mwamwa.controller;

import com.mwamwayababmeokuh.mwamwa.domain.*;
import com.mwamwayababmeokuh.mwamwa.service.BoardService;
import com.mwamwayababmeokuh.mwamwa.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BoardRestController {

    @Autowired
    BoardService boardService;

    @PostMapping("/boards/posts")
    @ResponseBody
    public PostDTO save(@RequestBody PostDTO postDTO) {
        return boardService.save(postDTO);
    }

    @DeleteMapping("/boards/posts/{pid}")
    @ResponseBody
    public Map<String, String> remove(@PathVariable String pid) {
        boardService.deleteById(Long.parseLong(pid));

        Map<String, String> map = new HashMap<>();
        map.put("result", "OK");
        return map;
    }

    @PutMapping("/boards/posts")
    @ResponseBody
    public PostDTO update(@RequestBody PostDTO postDTO) {
        return boardService.update(postDTO);
    }

    @GetMapping("/boards/posts/{uid}")
    @ResponseBody
    public List<PostDTO> findAllByUid(@PathVariable String uid) {
        return boardService.findAllByWriter(Long.parseLong(uid));
    }

    @GetMapping("/boards/onepost/{pid}")
    @ResponseBody
    public PostDTO findById(@PathVariable String pid) {
        return boardService.findById(Long.parseLong(pid));
    }

    @GetMapping("/boards/posts/liked-posts")
    @ResponseBody
    public List<PostDTO> findAllLikeByUid(LikeDTO likeDTO) {
        return boardService.findAllSqlByUid(likeDTO);
    }

    @GetMapping("/boards/rankings")
    @ResponseBody
    public List<String> rankHashtag() {
        return boardService.selectHashtagWithRankSql();
    }

    @GetMapping("/boards/posts")
    @ResponseBody
    public List<PostDTO> findByAid(@RequestBody List<ArtistDTO> artistDTOList) {
        return boardService.findByAid(artistDTOList);
    }

    @GetMapping("/boards/search-results")
    @ResponseBody
    public PostSearchDTO findPosts(String searchKeyword) {
        return boardService.findPosts(searchKeyword);
    }

}
