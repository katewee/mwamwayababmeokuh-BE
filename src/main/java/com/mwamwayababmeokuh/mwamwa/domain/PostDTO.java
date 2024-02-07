package com.mwamwayababmeokuh.mwamwa.domain;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private long pid;
    private long aid;
    private String hashtag;
    private String content;
    private double lat;
    private double lng;
    private long writer;
    private Timestamp createdAt;

}
