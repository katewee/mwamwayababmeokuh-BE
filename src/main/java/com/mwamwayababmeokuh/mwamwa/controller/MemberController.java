package com.mwamwayababmeokuh.mwamwa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/member/join")
    public String member_join() {
        return "member/memberForm";
    }

}
