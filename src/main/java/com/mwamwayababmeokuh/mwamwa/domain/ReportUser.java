package com.mwamwayababmeokuh.mwamwa.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "reports_user")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReportUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ruid;
    private long reporter;
    @Column(name = "REPORTEDUSER")
    private long reportedUser;
    private String reason;
    @Column(name = "ISPROCESSED")
    private boolean isProcessed;
    @Column(name = "CREATEDAT", insertable = false, updatable = false)
    private Timestamp createdAt;

}
