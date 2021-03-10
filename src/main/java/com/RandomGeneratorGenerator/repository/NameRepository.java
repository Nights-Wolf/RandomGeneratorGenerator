package com.RandomGeneratorGenerator.repository;

import com.RandomGeneratorGenerator.model.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
public interface NameRepository extends JpaRepository<Name, Long> {

    @Query(value = "SELECT name_name FROM Name WHERE name_id = :id")
    String getNamesById(@Param("id") Long id);

    @Query(value = "SELECT name_name FROM Name")
    ArrayList<String> getNames();

    @Query(value = "SELECT name_id FROM Name WHERE name_name = :Name")
    long getNameIdByName(@Param("Name") String name);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Name WHERE name_id = :id")
    void deleteName(@Param("id") Long id);
}
