package com.mwamwayababmeokuh.mwamwa.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "artists")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AID")
    private long aid;
    private String name;

}
