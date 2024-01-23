package com.mwamwayababmeokuh.mwamwa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Member extends BaseTimeEntity {

    @Id
    @Column(name = "UID")
    private int id;
    private String nickname;
    private String email;
    private String pw;
    private String profileImg;
    private String bio;
    private String role;

}
