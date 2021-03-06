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
public class Used {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long usedTags_id;
    private long name_id;

}
