package com.mwamwayababmeokuh.mwamwa.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteDTO {

    private long fid;
    private long uid;
    private long aid;

    public FavoriteDTO(long uid, long aid) {
        this.uid = uid;
        this.aid = aid;
    }
}
