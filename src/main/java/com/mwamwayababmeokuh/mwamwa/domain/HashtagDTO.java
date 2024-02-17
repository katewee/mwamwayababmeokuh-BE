package com.mwamwayababmeokuh.mwamwa.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HashtagDTO {

    private long hid;
    private String hashtag;
    private long cnt;

    public HashtagDTO(long hid, String hashtag) {
        this.hid = hid;
        this.hashtag = hashtag;
    }

    public HashtagDTO(String hashtag, long cnt) {
        this.hashtag = hashtag;
        this.cnt = cnt;
    }
}
