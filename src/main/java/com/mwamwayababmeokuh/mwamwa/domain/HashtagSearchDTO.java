package com.mwamwayababmeokuh.mwamwa.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HashtagSearchDTO {

    private boolean isExist;

    private List<HashtagDTO> result;

}
