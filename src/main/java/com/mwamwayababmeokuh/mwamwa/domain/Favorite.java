package com.mwamwayababmeokuh.mwamwa.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "favorites")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fid;
    private long uid;
    private long aid;

}
