package com.RandomGeneratorGenerator.repository;

import com.RandomGeneratorGenerator.model.Name;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NamesRepository extends JpaRepository<Name, Long> {
}
