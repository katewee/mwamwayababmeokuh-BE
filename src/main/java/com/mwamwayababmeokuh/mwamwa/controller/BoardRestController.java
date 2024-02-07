package com.mwamwayababmeokuh.mwamwa.controller;

import com.mwamwayababmeokuh.mwamwa.domain.Post;
import com.mwamwayababmeokuh.mwamwa.domain.PostDTO;
import com.mwamwayababmeokuh.mwamwa.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BoardRestController {

    @Autowired
    BoardService boardService;

    @PostMapping("/boards/posts")
    @ResponseBody
    public PostDTO save(PostDTO postDTO) {
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
    public PostDTO update(PostDTO postDTO) {
        return boardService.update(postDTO);
    }

}
