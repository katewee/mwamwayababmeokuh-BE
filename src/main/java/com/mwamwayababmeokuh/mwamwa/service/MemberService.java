package com.mwamwayababmeokuh.mwamwa.service;

import com.mwamwayababmeokuh.mwamwa.domain.Member;
import com.mwamwayababmeokuh.mwamwa.domain.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    public Member save(Member member) {
        member.setRole("general");
        memberRepository.save(member);
        return member;
    }


    public Optional<Member> findById(Member member) {
        return memberRepository.findById(member.getId());
    }

    public void deleteById(Member member) {
        memberRepository.deleteById(member.getId());
    }
}
