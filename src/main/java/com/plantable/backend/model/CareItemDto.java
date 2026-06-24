package com.plantable.backend.model;

import java.time.LocalDate;

public record CareItemDto(
    Long userPlantId,
    String plantCommonName,
    String plantImageUrl,
    String nickname,
    CareType type,
    CareCategory category,
    LocalDate dueDate,
    DueStatus status
) {}
