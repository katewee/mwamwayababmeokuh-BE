package com.mwamwayababmeokuh.mwamwa.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class LoginResultDTO {

    private MemberDTO memberDTO;
    private List<ArtistDTO> artistDTOList;

}
