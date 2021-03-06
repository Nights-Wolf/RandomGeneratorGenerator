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


    @Query(value = "SELECT name_id FROM Used")
    ArrayList<Long> getUsedTags();

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Used WHERE name_id = :id")
    void deleteUsedTag(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Used")
    void deleteAllUsedTag();
}
