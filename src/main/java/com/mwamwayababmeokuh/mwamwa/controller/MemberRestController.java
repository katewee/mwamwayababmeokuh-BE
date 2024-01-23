package com.mwamwayababmeokuh.mwamwa.controller;

import com.mwamwayababmeokuh.mwamwa.domain.Member;
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
    public Member register(Member member) {
        return memberService.save(member);
    }

    @GetMapping("/users/{id}")
    @ResponseBody
    public Member findOne(@PathVariable String id) {
        Member member = new Member();
        member.setId(Long.parseLong(id));
        Optional<Member> optionalMember = memberService.findById(member);
        return optionalMember.orElseThrow(NoSuchElementException::new);
    }

    @PutMapping("/users/{id}")
    @ResponseBody
    public Member update(@PathVariable String id, Member member) {
        return memberService.save(member);
    }

    @DeleteMapping("/users/{id}")
    @ResponseBody
    public Map<String, String> remove(@PathVariable String id) {
        Member member = new Member();
        member.setId(Long.parseLong(id));
        memberService.deleteById(member);

        Map<String, String> map = new HashMap<>();
        map.put("result", "OK");
        return map;
    }

}
