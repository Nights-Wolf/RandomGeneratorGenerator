package com.RandomGeneratorGenerator.repository;

import com.RandomGeneratorGenerator.model.KitsName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
public interface KitsNameRepository extends JpaRepository<KitsName, Long> {

    @Query(value = "SELECT kitsName_id FROM KitsName WHERE kitsName_name = :kitsName")
    long getKitsNameIdByName(@Param("kitsName") String kitsName);

    @Query(value = "SELECT kitsName_id FROM KitsName")
    ArrayList<Long> getKitsNameId();

    @Query(value = "SELECT kitsName_name FROM KitsName WHERE kitsName_id = :id")
    String getKitsNames(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM KitsName WHERE kitsName_id = :id")
    void removeSetName(@Param("id") Long id);
}
