package com.getto.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TogetherDTO {

    private Long id;

    private String name;

    private String description;

    private String place;

    private UserDTO creator;

    private List<GuestDTO> guests;

    private Long answerer;

    private String answer;

}
