package com.mwamwayababmeokuh.mwamwa.controller;

import com.mwamwayababmeokuh.mwamwa.domain.Board;
import com.mwamwayababmeokuh.mwamwa.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BoardRestController {

    @Autowired
    BoardService boardService;

    @PostMapping("/boards/posts")
    @ResponseBody
    public Board save(Board board) {
        return boardService.save(board);
    }

    @DeleteMapping("/boards/posts/{id}")
    @ResponseBody
    public Map<String, String> remove(@PathVariable String id) {
        Board board = new Board();
        board.setId(Long.parseLong(id));
        boardService.deleteById(board);

        Map<String, String> map = new HashMap<>();
        map.put("result", "OK");
        return map;
    }

}
