package com.mwamwayababmeokuh.mwamwa.controller;

import com.mwamwayababmeokuh.mwamwa.domain.Member;
import com.mwamwayababmeokuh.mwamwa.domain.MemberDTO;
import com.mwamwayababmeokuh.mwamwa.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class MemberRestController {

    @Autowired
    MemberService memberService;

    @PostMapping("/auth/register")
    @ResponseBody
    public MemberDTO register(MemberDTO memberDTO) {
        return memberService.save(memberDTO);
    }

    @GetMapping("/check-email")
    @ResponseBody
    public Map<String, String> validateEmail(MemberDTO memberDTO) {
        Map<String, String> map = new HashMap<>();
        try {
            MemberDTO memberDTO1 = memberService.validateEmail(memberDTO);
        } catch (NoSuchElementException e) {
            map.put("result", "OK");
            return map;
        }
        map.put("result", "existing email");
        return map;
    }

    @GetMapping("/users/{uid}")
    @ResponseBody
    public MemberDTO findOne(@PathVariable String uid) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUid(Long.parseLong(uid));
        return memberService.findById(memberDTO);
    }

    @PutMapping("/users/{uid}")
    @ResponseBody
    public MemberDTO update(@PathVariable String uid, MemberDTO memberDTO) {
        return memberService.save(memberDTO);
    }

    @DeleteMapping("/users/{uid}")
    @ResponseBody
    public Map<String, String> remove(@PathVariable String uid) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUid(Long.parseLong(uid));
        memberService.deleteById(memberDTO);

        Map<String, String> map = new HashMap<>();
        map.put("result", "OK");
        return map;
    }

    @PostMapping("/update-password")
    @ResponseBody
    public MemberDTO updatePassword(MemberDTO memberDTO) {
        return memberService.updatePassword(memberDTO);
    }

    @PostMapping("/auth/email")
    @ResponseBody
    public Map<String, String> sendMail(MemberDTO memberDTO) {
        memberService.sendCodeToEmail(memberDTO);
        Map<String, String> map = new HashMap<>();
        map.put("result", "OK");

        return map;
    }

}
