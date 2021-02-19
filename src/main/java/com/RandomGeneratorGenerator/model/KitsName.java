package com.RandomGeneratorGenerator.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class KitsName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long kitsName_id;
    private String kitsName_name;
}
