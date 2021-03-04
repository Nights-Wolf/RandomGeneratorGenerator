package com.RandomGeneratorGenerator.repository;

import com.RandomGeneratorGenerator.model.Used;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsedRepository extends JpaRepository<Used, Long> {
}
