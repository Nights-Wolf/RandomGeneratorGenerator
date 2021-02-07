package com.RandomGeneratorGenerator.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class Name {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long name_id;
    private String name_name;

}
