package com.mwamwayababmeokuh.mwamwa.controller;

import com.mwamwayababmeokuh.mwamwa.domain.*;
import com.mwamwayababmeokuh.mwamwa.service.ArtistService;
import com.mwamwayababmeokuh.mwamwa.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Controller
public class MemberRestController {

    @Autowired
    MemberService memberService;

    @Autowired
    ArtistService artistService;

    @PostMapping("/auth/register")
    @ResponseBody
    public LoginResultDTO register(HttpServletRequest httpServletRequest, @RequestBody MemberDTO memberDTO) {
        MemberDTO result = memberService.save(memberDTO);
        LoginResultDTO loginResultDTO = new LoginResultDTO();

        FavoriteDTO favoriteDTO = new FavoriteDTO();
        favoriteDTO.setUid(memberDTO.getUid());
        List<ArtistDTO> artistDTOList = artistService.searchFavorites(favoriteDTO);
        loginResultDTO.setMemberDTO(result);
        loginResultDTO.setArtistDTOList(artistDTOList);

        httpServletRequest.getSession().invalidate();
        HttpSession httpSession = httpServletRequest.getSession(true);
        httpSession.setAttribute("uid", result.getUid());

        return loginResultDTO;
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
    public LoginResultDTO findOne(@PathVariable String uid) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUid(Long.parseLong(uid));
        MemberDTO result = memberService.findById(memberDTO);
        LoginResultDTO loginResultDTO = new LoginResultDTO();

        FavoriteDTO favoriteDTO = new FavoriteDTO();
        favoriteDTO.setUid(result.getUid());
        List<ArtistDTO> artistDTOList = artistService.searchFavorites(favoriteDTO);
        loginResultDTO.setMemberDTO(result);
        loginResultDTO.setArtistDTOList(artistDTOList);

        return loginResultDTO;
    }

    @PutMapping("/users/{uid}")
    @ResponseBody
    public LoginResultDTO update(@PathVariable String uid, @RequestBody MemberDTO memberDTO) {
        LoginResultDTO loginResultDTO = new LoginResultDTO();
        MemberDTO result = memberService.updateMember(memberDTO);
        FavoriteInsertDTO favoriteInsertDTO = new FavoriteInsertDTO(memberDTO.getUid(), memberDTO.getAid());
        List<ArtistDTO> artistDTOList = artistService.updateFavorites(favoriteInsertDTO);
        loginResultDTO.setMemberDTO(result);
        loginResultDTO.setArtistDTOList(artistDTOList);

        return loginResultDTO;
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
    public LoginResultDTO login(HttpServletRequest httpServletRequest, @RequestBody MemberDTO memberDTO) {
        MemberDTO result = memberService.login(memberDTO);
        LoginResultDTO loginResultDTO = new LoginResultDTO();

        FavoriteDTO favoriteDTO = new FavoriteDTO();
        favoriteDTO.setUid(result.getUid());
        List<ArtistDTO> artistDTOList = artistService.searchFavorites(favoriteDTO);
        loginResultDTO.setMemberDTO(result);
        loginResultDTO.setArtistDTOList(artistDTOList);

        httpServletRequest.getSession().invalidate();
        HttpSession httpSession = httpServletRequest.getSession(true);
        httpSession.setAttribute("uid", result.getUid());

        return loginResultDTO;
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
