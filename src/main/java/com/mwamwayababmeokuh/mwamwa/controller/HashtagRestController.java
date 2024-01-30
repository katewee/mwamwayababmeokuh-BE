package com.mwamwayababmeokuh.mwamwa.controller;

import com.mwamwayababmeokuh.mwamwa.domain.HashtagDTO;
import com.mwamwayababmeokuh.mwamwa.service.HashtagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
