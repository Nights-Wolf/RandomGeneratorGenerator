package com.RandomGeneratorGenerator.repository;

import com.RandomGeneratorGenerator.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query(value = "SELECT tag_name FROM Tag WHERE tag_id = :id")
    String getTagsNames(@Param("id") Long id);

    @Query(value = "SELECT tag_id FROM Tag WHERE tag_name = :tag")
    long getTagByName(@Param("tag") String tagName);
}
