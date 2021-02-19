package com.RandomGeneratorGenerator.repository;

import com.RandomGeneratorGenerator.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

    @Query(value = "SELECT name_id FROM Content WHERE tag_id = :id")
    ArrayList<Long> getNameById(@Param("id") Long id);

    @Query(value = "SELECT name_id FROM Content WHERE tag_id = :id AND name_id = :name_id")
    long filterName(@Param("id") Long id, @Param("name_id") Long name_id);
}