package com.plantable.backend.repository;

import com.plantable.backend.model.CareLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CareLogRepository extends JpaRepository<CareLog, Long> {
    List<CareLog> findByUserPlantIdOrderByLoggedAtDesc(Long userPlantId);
}
