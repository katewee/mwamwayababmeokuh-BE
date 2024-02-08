package com.mwamwayababmeokuh.mwamwa.domain;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReportPostDTO {

    private int rpid;
    private long reporter;
    private long reportedPost;
    private String reason;
    private boolean isProcessed;
    private Timestamp createdAt;

}
