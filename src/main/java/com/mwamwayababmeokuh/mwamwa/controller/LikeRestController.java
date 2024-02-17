package com.mwamwayababmeokuh.mwamwa.controller;

import com.mwamwayababmeokuh.mwamwa.domain.LikeDTO;
import com.mwamwayababmeokuh.mwamwa.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Controller
public class LikeRestController {

    @Autowired
    LikeService likeService;

    @PostMapping("/boards/posts/like")
    @ResponseBody
    public LikeDTO saveLike(@RequestBody LikeDTO likeDTO) {
        return likeService.save(likeDTO);
    }

    @DeleteMapping("/boards/posts/like")
    @ResponseBody
    public Map<String, String> deleteLike(@RequestBody LikeDTO likeDTO) {
        likeService.delete(likeDTO);
        Map<String, String> map = new HashMap<>();
        map.put("result", "OK");
        return map;
    }

    @GetMapping("/boards/posts/like")
    @ResponseBody
    public Map<String, Boolean> findLike(LikeDTO likeDTO) {
        Map<String, Boolean> map = new HashMap<>();
        try {
            LikeDTO likeDTO1 = likeService.findByUidAndPid(likeDTO);
        }catch (NoSuchElementException e) {
            map.put("isLiked", false);
            return map;
        }

        map.put("isLiked", true);
        return map;
    }

}
