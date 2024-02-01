package com.mwamwayababmeokuh.mwamwa.controller;

import com.mwamwayababmeokuh.mwamwa.domain.HashtagDTO;
import com.mwamwayababmeokuh.mwamwa.service.HashtagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class HashtagRestController {

    @Autowired
    HashtagService hashtagService;

    @PostMapping("/hashtags")
    @ResponseBody
    public HashtagDTO save(HashtagDTO hashtagDTO) {
        log.info("HashtagDTO: " + hashtagDTO.toString());
        return hashtagService.save(hashtagDTO);
    }

    @GetMapping("/hashtags/search")
    @ResponseBody
    public List<HashtagDTO> find(HashtagDTO hashtagDTO) {
        log.info("HashtagDTO: " + hashtagDTO.toString());
        return hashtagService.find(hashtagDTO);
    }

    @DeleteMapping("/hashtags")
    @ResponseBody
    public Map<String, String> remove(HashtagDTO hashtagDTO) {
        Map<String, String> result = new HashMap<>();

        result.put("result", "OK");
        return result;
    }

}
