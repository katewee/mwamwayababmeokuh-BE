package com.mwamwayababmeokuh.mwamwa.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "hashtags")
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long hid;
    private String hashtag;

}
