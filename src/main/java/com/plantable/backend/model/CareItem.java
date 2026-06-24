package com.plantable.backend.model;

import java.time.LocalDate;

public record CareItem(
    CareType type,
    CareCategory category,
    LocalDate dueDate,
    DueStatus status
) {}
