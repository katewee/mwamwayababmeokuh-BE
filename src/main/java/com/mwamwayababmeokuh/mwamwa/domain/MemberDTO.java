package com.mwamwayababmeokuh.mwamwa.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {

    private long uid;
    private String nickname;
    private String email;
    private String pw;
    private String bio;
    private String role;

    private List<Long> aid;

    public MemberDTO(long uid, String nickname, String email, String pw, String bio, String role) {
        this.uid = uid;
        this.nickname = nickname;
        this.email = email;
        this.pw = pw;
        this.bio = bio;
        this.role = role;
    }
}
