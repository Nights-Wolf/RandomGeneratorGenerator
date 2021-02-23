package com.RandomGeneratorGenerator.repository;

import com.RandomGeneratorGenerator.model.KitsContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
public interface KitsContentRepository extends JpaRepository<KitsContent, Long> {

    @Query(value = "SELECT name_id FROM KitsContent WHERE kitsName_id = :id")
    ArrayList<Long> getSetsById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM KitsContent WHERE name_id = :id")
    void removeContentFromSet(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM KitsContent WHERE kitsName_id = :id")
    void removeWholeSet(@Param("id") Long id);
}
