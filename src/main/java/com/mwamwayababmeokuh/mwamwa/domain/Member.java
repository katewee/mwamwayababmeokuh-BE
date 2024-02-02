package com.mwamwayababmeokuh.mwamwa.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class Member{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UID")
    private long uid;
    private String nickname;
    private String email;
    private String pw;
    @Column(name = "PROFILEIMG")
    @ColumnDefault("defaultImg.jpg")
    private String profileImg;
    private String bio;
    private String role;

}
