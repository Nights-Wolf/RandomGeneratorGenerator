package com.RandomGeneratorGenerator.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@Component
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long content_id;
    private long tag_id;
    private long name_id;

}
