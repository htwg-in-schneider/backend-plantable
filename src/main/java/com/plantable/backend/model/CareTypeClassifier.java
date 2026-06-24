package com.plantable.backend.model;

import java.util.Set;

public final class CareTypeClassifier {

    private static final Set<CareType> TASKS = Set.of(
        CareType.WATERING, CareType.MISTING,
        CareType.FERTILIZING, CareType.LEAF_CLEANING
    );

    private CareTypeClassifier() {}

    public static CareCategory categoryOf(CareType type) {
        return TASKS.contains(type) ? CareCategory.TASK : CareCategory.REMINDER;
    }
}
