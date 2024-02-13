package com.mwamwayababmeokuh.mwamwa.service;

import com.mwamwayababmeokuh.mwamwa.domain.Member;
import com.mwamwayababmeokuh.mwamwa.domain.MemberDTO;
import com.mwamwayababmeokuh.mwamwa.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
@Transactional
public class MemberService {

    @Autowired
    MemberRepository memberRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    MailService mailService;

    @Autowired
    RedisService redisService;

    @Value("${spring.mail.auth-code-expiration-millis}")
    private long authCodeExpirationMillis;

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
        Optional<Member> optionalMember = memberRepository.findByEmail(memberDTO.getEmail());
        Member entity = optionalMember.orElseThrow(NoSuchElementException::new);
        entity.setPw(memberDTO.getPw());
        return modelMapper.map(entity, MemberDTO.class);
    }

    public void sendCodeToEmail(MemberDTO memberDTO) throws NoSuchAlgorithmException {
        log.info("sendCodeToEmail()" + memberDTO.toString());
        String title = "Mwamwa 이메일 인증";
        String authCode = this.createCode();
        mailService.sendEmail(memberDTO.getEmail(), title, authCode);
        redisService.setValues(memberDTO.getEmail(), authCode, Duration.ofMillis(this.authCodeExpirationMillis));
    }

    public boolean verifyCode(String email, String authCode) {
        log.info("verifyCode()" + email + authCode);
        String redisAuthCode = redisService.getValues(email);
        boolean result = redisService.checkExistsValue(redisAuthCode) && redisAuthCode.equals(authCode);

        return result;
    }

    private String createCode() throws NoSuchAlgorithmException {
        int lenth = 6;
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < lenth; i++) {
                builder.append(random.nextInt(10));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            log.debug("MemberService.createCode() exception occur");
            throw e;
        }
    }

    public MemberDTO login(MemberDTO memberDTO) {
        log.info("login()" + memberDTO.toString());
        Optional<Member> optionalMember = memberRepository.findByEmailAndPw(memberDTO.getEmail(), memberDTO.getPw());

        if(optionalMember.isEmpty()){
            return null;
        }else {
            Member member = optionalMember.get();
            return modelMapper.map(member, MemberDTO.class);
        }
    }
}
