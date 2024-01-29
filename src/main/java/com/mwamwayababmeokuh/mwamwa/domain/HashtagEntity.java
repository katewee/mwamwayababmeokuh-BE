package com.mwamwayababmeokuh.mwamwa.domain;

import javax.persistence.*;

@Entity
@Table(name = "hashtags")
public class HashtagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hid;
    private String hashtag;

}
