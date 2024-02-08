package com.mwamwayababmeokuh.mwamwa.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "likes")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LID")
    private long lid;
    private long uid;
    private long pid;
    @Column(name = "CREATEDAT", insertable = false, updatable = false)
    private Timestamp createdAt;

}
