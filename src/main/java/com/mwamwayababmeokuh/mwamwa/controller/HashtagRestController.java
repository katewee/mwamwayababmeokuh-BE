package com.mwamwayababmeokuh.mwamwa.controller;

import com.mwamwayababmeokuh.mwamwa.domain.HashtagDTO;
import com.mwamwayababmeokuh.mwamwa.domain.HashtagSearchDTO;
import com.mwamwayababmeokuh.mwamwa.service.HashtagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public HashtagDTO save(@RequestBody HashtagDTO hashtagDTO) {
        log.info("HashtagDTO: " + hashtagDTO.toString());
        return hashtagService.save(hashtagDTO);
    }

    @GetMapping("/hashtags/search")
    @ResponseBody
    public HashtagSearchDTO find(HashtagDTO hashtagDTO) {
        log.info("HashtagDTO: " + hashtagDTO.toString());
        boolean isExist = hashtagService.findByHashtag(hashtagDTO.getHashtag());
        List<HashtagDTO> result = hashtagService.find(hashtagDTO);
        return new HashtagSearchDTO(isExist, result);
    }

    @DeleteMapping("/hashtags")
    @ResponseBody
    public Map<String, String> remove(@RequestBody HashtagDTO hashtagDTO) {
        Map<String, String> result = new HashMap<>();

        result.put("result", "OK");
        return result;
    }

}
