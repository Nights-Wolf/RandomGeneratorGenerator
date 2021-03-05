package com.RandomGeneratorGenerator.repository;

import com.RandomGeneratorGenerator.model.Used;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UsedRepository extends JpaRepository<Used, Long> {


    @Query(value = "SELECT used_Tags_name FROM Used")
    ArrayList<String> getUsedTags();
}
