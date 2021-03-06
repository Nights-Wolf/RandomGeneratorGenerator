package com.RandomGeneratorGenerator.repository;

import com.RandomGeneratorGenerator.model.Used;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
public interface UsedRepository extends JpaRepository<Used, Long> {


    @Query(value = "SELECT used_Tags_name FROM Used")
    ArrayList<String> getUsedTags();

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Used WHERE used_Tags_name = :name")
    void deleteUsedTag(@Param("name") String name);
}
