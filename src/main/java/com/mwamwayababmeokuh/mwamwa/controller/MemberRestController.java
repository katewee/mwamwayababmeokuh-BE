package com.mwamwayababmeokuh.mwamwa.controller;

import com.mwamwayababmeokuh.mwamwa.domain.Member;
import com.mwamwayababmeokuh.mwamwa.domain.MemberDTO;
import com.mwamwayababmeokuh.mwamwa.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Controller
public class MemberRestController {

    @Autowired
    MemberService memberService;

    @PostMapping("/auth/register")
    @ResponseBody
    public MemberDTO register(@RequestBody MemberDTO memberDTO) {
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
    public MemberDTO update(@PathVariable String uid, @RequestBody MemberDTO memberDTO) {
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
    public MemberDTO updatePassword(@RequestBody MemberDTO memberDTO) {
        return memberService.updatePassword(memberDTO);
    }

    @PostMapping("/auth/email")
    @ResponseBody
    public Map<String, String> sendMail(@RequestBody MemberDTO memberDTO) throws NoSuchAlgorithmException {
        memberService.sendCodeToEmail(memberDTO);
        Map<String, String> map = new HashMap<>();
        map.put("result", "OK");

        return map;
    }

    @PostMapping("/check-verification-code")
    @ResponseBody
    public Map<String, Boolean> verifyCode(@RequestBody String email, @RequestBody String authCode) {
        boolean result = memberService.verifyCode(email, authCode);
        Map<String, Boolean> map = new HashMap<>();
        map.put("result", result);

        return map;
    }

    @PostMapping("/auth/login")
    @ResponseBody
    public Map<String, String> login(HttpServletRequest httpServletRequest, @RequestBody MemberDTO memberDTO) {
        MemberDTO result = memberService.login(memberDTO);
        Map<String, String> map = new HashMap<>();

        if(result == null) {
            map.put("result", "fail");
            return map;
        }

        httpServletRequest.getSession().invalidate();
        HttpSession httpSession = httpServletRequest.getSession(true);
        httpSession.setAttribute("uid", result.getUid());

        map.put("result", "success");
        return map;
    }

    @GetMapping("/auth/logout")
    @ResponseBody
    public Map<String, String> logout(HttpServletRequest httpServletRequest) {
        Map<String, String> map = new HashMap<>();
        HttpSession httpSession = httpServletRequest.getSession(false);
        if(httpSession != null) {
            httpSession.invalidate();
        }

        map.put("result", "OK");
        return map;
    }

}
