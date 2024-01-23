package com.mwamwayababmeokuh.mwamwa.service;

import com.mwamwayababmeokuh.mwamwa.domain.Board;
import com.mwamwayababmeokuh.mwamwa.domain.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    public Board save(Board board) {
        boardRepository.save(board);
        return board;
    }

    public List<Board> findAllById(Board board) {
        List<Long> ids = new ArrayList<>();
        ids.add(board.getId());
        return boardRepository.findAllById(ids);
    }

    public void deleteById(Board board) {
        boardRepository.deleteById(board.getId());
    }
}
