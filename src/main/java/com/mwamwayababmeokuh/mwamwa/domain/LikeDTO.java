package com.mwamwayababmeokuh.mwamwa.domain;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LikeDTO {

    private long lid;
    private long uid;
    private long pid;
    private Timestamp createdAt;

}
