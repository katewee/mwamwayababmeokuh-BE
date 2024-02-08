package com.mwamwayababmeokuh.mwamwa.domain;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReportUserDTO {

    private int ruid;
    private long reporter;
    private long reportedUser;
    private String reason;
    private boolean isProcessed;
    private Timestamp createdAt;

}
