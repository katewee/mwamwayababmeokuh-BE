package com.mwamwayababmeokuh.mwamwa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class PostSearchDTO {

    private List<PostDTO> byHashtag;
    private List<PostDTO> byArtist;

}
