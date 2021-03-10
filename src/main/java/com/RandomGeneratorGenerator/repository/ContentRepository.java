package com.RandomGeneratorGenerator.repository;

import com.RandomGeneratorGenerator.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

    @Query(value = "SELECT name_id FROM Content WHERE tag_id = :id")
    ArrayList<Long> getNameById(@Param("id") Long id);

    @Query(value = "SELECT name_id FROM Content WHERE tag_id = :id")
     long getSingleNameByTagId(@Param("id") Long id);

    @Query(value = "SELECT tag_id FROM Content WHERE name_id = :id")
    ArrayList<Long> getSingleTagByNameId(@Param("id") Long id);

    @Query(value = "SELECT name_id FROM Content WHERE tag_id = :id AND name_id = :name_id")
    long filterName(@Param("id") Long id, @Param("name_id") Long name_id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Content WHERE name_id = :id")
    void deleteNameFromContent(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Content WHERE tag_id = :tag_id AND name_id = :name_id")
    void deleteFromContent(@Param("tag_id") Long tagId, @Param("name_id") Long nameId);
}