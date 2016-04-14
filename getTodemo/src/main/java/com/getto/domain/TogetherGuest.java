package com.getto.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(includeFieldNames = true)
public class TogetherGuest implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1465867573125770232L;

    private Long guest;

    private Long together;
}
