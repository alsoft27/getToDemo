package com.getto.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "together", includeFieldNames = true)
public class Guest implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -4219576530160907817L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String name;

    private String type;

    private Boolean answer;

    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "together_id", referencedColumnName = "id")
    private Together together;
}
