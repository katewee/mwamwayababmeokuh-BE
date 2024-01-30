package com.mwamwayababmeokuh.mwamwa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HashtagController {

    @GetMapping("/hashtag/save")
    public String hashtag_save() {
        return "hashtag/hashtagForm";
    }

}
