package com.mwamwayababmeokuh.mwamwa.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "reports_post")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReportPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rpid;
    private long reporter;
    @Column(name = "REPORTEDPost")
    private long reportedPost;
    private String reason;
    @Column(name = "ISPROCESSED")
    private boolean isProcessed;
    @Column(name = "CREATEDAT", insertable = false, updatable = false)
    private Timestamp createdAt;

}
