package com.getto.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class Together implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5326039652152132572L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String name;

    private String description;

    private String place;

    @ManyToOne(fetch = FetchType.EAGER)
    private User creator;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "together", cascade = CascadeType.ALL)
    private List<Guest> guests;

}
