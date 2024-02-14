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
    public Map<String, String> findLike(LikeDTO likeDTO) {
        Map<String, String> map = new HashMap<>();
        try {
            LikeDTO likeDTO1 = likeService.findByUidAndPid(likeDTO);
        }catch (NoSuchElementException e) {
            map.put("result", "not exist");
            return map;
        }

        map.put("result", "exist");
        return map;
    }

}
