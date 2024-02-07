package com.mwamwayababmeokuh.mwamwa.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "posts")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid")
    private long pid;
    private long aid;
    private String hashtag;
    private String content;
    private double lat;
    private double lng;
    private long writer;
    @Column(name = "CREATEDAT", insertable = false, updatable = false)
    private Timestamp createdAt;

}
