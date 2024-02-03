package com.mwamwayababmeokuh.mwamwa.service;

import com.mwamwayababmeokuh.mwamwa.domain.Member;
import com.mwamwayababmeokuh.mwamwa.domain.MemberDTO;
import com.mwamwayababmeokuh.mwamwa.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class MemberService {

    @Autowired
    MemberRepository memberRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public MemberDTO save(MemberDTO memberDTO) {
        log.info("save()" + memberDTO.toString());
        Member entity = modelMapper.map(memberDTO, Member.class);
        Member result = memberRepository.save(entity);
        return modelMapper.map(result, MemberDTO.class);
    }

    public MemberDTO validateEmail(MemberDTO memberDTO) {
        log.info("validateEmail()" + memberDTO.toString());
        Optional<Member> optionalMember = memberRepository.findByEmail(memberDTO.getEmail());
        return modelMapper.map(optionalMember.orElseThrow(NoSuchElementException::new), MemberDTO.class);
    }

    public MemberDTO findById(MemberDTO memberDTO) {
        log.info("findById()" + memberDTO.toString());
        Member entity = modelMapper.map(memberDTO, Member.class);
        Optional<Member> optionalMember = memberRepository.findById(entity.getUid());
        return modelMapper.map(optionalMember.orElseThrow(NoSuchElementException::new), MemberDTO.class);
    }

    public void deleteById(MemberDTO memberDTO) {
        log.info("deleteById()" + memberDTO.toString());
        Member entity = modelMapper.map(memberDTO, Member.class);
        memberRepository.deleteById(entity.getUid());
    }

    public MemberDTO updatePassword(MemberDTO memberDTO) {
        log.info("updatePassword()" + memberDTO.toString());
        Optional<Member> optionalMember = memberRepository.findById(memberDTO.getUid());
        Member entity = optionalMember.orElseThrow(NoSuchElementException::new);
        entity.setPw(memberDTO.getPw());
        return modelMapper.map(entity, MemberDTO.class);
    }
}
