package com.mwamwayababmeokuh.mwamwa.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Member{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UID")
    private long id;
    private String nickname;
    private String email;
    private String pw;
    @Column(name = "PROFILEIMG")
    @ColumnDefault("defaultImg.jpg")
    private String profileImg;
    private String bio;
    private String role;

}
