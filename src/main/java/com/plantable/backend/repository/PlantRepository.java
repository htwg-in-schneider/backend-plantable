package com.plantable.backend.repository;
import com.plantable.backend.model.Plant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlantRepository extends JpaRepository<Plant, Long> {
    Optional<Plant> findBySlug(String slug);
}