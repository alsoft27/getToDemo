package com.getto.dto;

import org.dozer.Mapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuestDTO {

    private Long id;

    private String name;

    private String type;

    private Boolean answer;

    @Mapping("together.id")
    private Long together;

}
