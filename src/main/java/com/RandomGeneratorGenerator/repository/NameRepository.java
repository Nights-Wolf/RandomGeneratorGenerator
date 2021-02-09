package com.RandomGeneratorGenerator.repository;

import com.RandomGeneratorGenerator.model.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface NameRepository extends JpaRepository<Name, Long> {

    @Query(value = "SELECT name_name FROM Name WHERE name_id = :id")
    String getNamesById(@Param("id") Long id);
}
