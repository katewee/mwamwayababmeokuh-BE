package com.mwamwayababmeokuh.mwamwa.domain;

import lombok.*;

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

}
