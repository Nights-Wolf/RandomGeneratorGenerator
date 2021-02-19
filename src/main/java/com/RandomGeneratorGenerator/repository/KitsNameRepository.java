package com.RandomGeneratorGenerator.repository;

import com.RandomGeneratorGenerator.model.KitsName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface KitsNameRepository extends JpaRepository<KitsName, Long> {

    @Query(value = "SELECT kitsName_id FROM KitsName WHERE kitsName_name = :kitsName")
    long getKitsNameIdByName(@Param("kitsName") String kitsName);

    @Query(value = "SELECT kitsName_name FROM KitsName WHERE kitsName_id = :id")
    String getKitsNames(@Param("id") Long id);
}
