package com.mwamwayababmeokuh.mwamwa.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid")
    private long id;
    private long aid;
    private String hashtag;
    private String content;
    private String pimg;
    private double lat;
    private double lng;
    private long writer;
    @Column(name = "CREATEDAT")
    private Timestamp createdAt;

}
