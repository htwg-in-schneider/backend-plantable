package com.plantable.backend.service;

import com.plantable.backend.model.*;
import com.plantable.backend.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class UserPlantService {

    private final UserPlantRepository userPlantRepository;
    private final UserRepository userRepository;
    private final PlantRepository plantRepository;
    private final CareLogRepository careLogRepository;

    public UserPlantService(UserPlantRepository userPlantRepository,
                            UserRepository userRepository,
                            PlantRepository plantRepository,
                            CareLogRepository careLogRepository) {
        this.userPlantRepository = userPlantRepository;
        this.userRepository = userRepository;
        this.plantRepository = plantRepository;
        this.careLogRepository = careLogRepository;
    }

    public UserPlant addPlantToDashboard(Long userId, Long plantId, String nickname, LocalDate acquiredAt) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
        Plant plant = plantRepository.findById(plantId)
                .orElseThrow(() -> new IllegalArgumentException("Plant not found: " + plantId));

        UserPlant up = new UserPlant();
        up.setUser(user);
        up.setPlant(plant);
        up.setNickname(nickname);
        up.setAcquiredAt(acquiredAt != null ? acquiredAt : LocalDate.now());

        // Copy defaults from Plant; watering falls back to 7 days if plant has no default set
        up.setWateringIntervalDays(
            plant.getDefaultWateringIntervalDays() != null ? plant.getDefaultWateringIntervalDays() : 7);
        up.setMistingIntervalDays(plant.getDefaultMistingIntervalDays());
        up.setFertilizingIntervalDays(plant.getDefaultFertilizingIntervalDays());
        up.setLeafCleaningIntervalDays(plant.getDefaultLeafCleaningIntervalDays());
        up.setRepottingIntervalDays(plant.getDefaultRepottingIntervalDays());
        up.setPruningIntervalDays(plant.getDefaultPruningIntervalDays());
        up.setPestCheckIntervalDays(plant.getDefaultPestCheckIntervalDays());

        return userPlantRepository.save(up);
    }

    @Transactional(readOnly = true)
    public List<UserPlant> getUserPlants(Long userId) {
        return userPlantRepository.findByUserId(userId);
    }

    public List<CareItem> buildCareItems(UserPlant up) {
        LocalDate today = LocalDate.now();
        List<CareItem> items = new ArrayList<>();
        addItem(items, CareType.WATERING,      up.getWateringIntervalDays(),      up.getLastWateredAt(),      up.getAcquiredAt(), today);
        addItem(items, CareType.MISTING,       up.getMistingIntervalDays(),       up.getLastMistedAt(),       up.getAcquiredAt(), today);
        addItem(items, CareType.FERTILIZING,   up.getFertilizingIntervalDays(),   up.getLastFertilizedAt(),   up.getAcquiredAt(), today);
        addItem(items, CareType.LEAF_CLEANING, up.getLeafCleaningIntervalDays(),  up.getLastLeafCleanedAt(),  up.getAcquiredAt(), today);
        addItem(items, CareType.REPOTTING,     up.getRepottingIntervalDays(),     up.getLastRepottedAt(),     up.getAcquiredAt(), today);
        addItem(items, CareType.PRUNING,       up.getPruningIntervalDays(),       up.getLastPrunedAt(),       up.getAcquiredAt(), today);
        addItem(items, CareType.PEST_CHECK,    up.getPestCheckIntervalDays(),     up.getLastPestCheckedAt(),  up.getAcquiredAt(), today);
        return items;
    }

    private void addItem(List<CareItem> items, CareType type, Integer intervalDays,
                         LocalDate lastDoneAt, LocalDate acquiredAt, LocalDate today) {
        if (intervalDays == null) return;
        LocalDate base = lastDoneAt != null ? lastDoneAt : acquiredAt;
        LocalDate dueDate = base.plusDays(intervalDays);
        DueStatus status;
        if (dueDate.isBefore(today))      status = DueStatus.OVERDUE;
        else if (dueDate.isEqual(today))  status = DueStatus.DUE;
        else                              status = DueStatus.UPCOMING;
        items.add(new CareItem(type, CareTypeClassifier.categoryOf(type), dueDate, status));
    }

    @Transactional(readOnly = true)
    public Map<DueStatus, List<CareItemDto>> getDashboardCareItems(Long userId) {
        List<UserPlant> userPlants = userPlantRepository.findByUserId(userId);
        Map<DueStatus, List<CareItemDto>> result = new EnumMap<>(DueStatus.class);
        for (DueStatus s : DueStatus.values()) result.put(s, new ArrayList<>());

        for (UserPlant up : userPlants) {
            String plantName = up.getPlant().getCommonName();
            String plantImage = up.getPlant().getMainImageUrl();
            for (CareItem item : buildCareItems(up)) {
                result.get(item.status()).add(new CareItemDto(
                    up.getId(), plantName, plantImage, up.getNickname(),
                    item.type(), item.category(), item.dueDate(), item.status()
                ));
            }
        }
        return result;
    }

    public CareLog logCareAction(Long userPlantId, Long userId, CareType careType, CareAction action) {
        UserPlant up = userPlantRepository.findById(userPlantId)
                .orElseThrow(() -> new IllegalArgumentException("UserPlant not found: " + userPlantId));

        if (!up.getUser().getId().equals(userId)) {
            throw new IllegalAccessError("Diese Pflanze gehört nicht zum angegebenen Nutzer.");
        }

        if (CareTypeClassifier.categoryOf(careType) == CareCategory.TASK && action == CareAction.SKIPPED) {
            throw new IllegalArgumentException("Tasks (WATERING, MISTING, FERTILIZING, LEAF_CLEANING) können nicht übersprungen werden.");
        }

        // Per Spec: Skip setzt last...At genauso wie Done
        updateLastAt(up, careType);
        userPlantRepository.save(up);

        CareLog log = new CareLog();
        log.setUserPlant(up);
        log.setCareType(careType);
        log.setAction(action);
        log.setLoggedAt(LocalDateTime.now());
        return careLogRepository.save(log);
    }

    private void updateLastAt(UserPlant up, CareType type) {
        LocalDate today = LocalDate.now();
        switch (type) {
            case WATERING      -> up.setLastWateredAt(today);
            case MISTING       -> up.setLastMistedAt(today);
            case FERTILIZING   -> up.setLastFertilizedAt(today);
            case LEAF_CLEANING -> up.setLastLeafCleanedAt(today);
            case REPOTTING     -> up.setLastRepottedAt(today);
            case PRUNING       -> up.setLastPrunedAt(today);
            case PEST_CHECK    -> up.setLastPestCheckedAt(today);
        }
    }

    public Optional<UserPlant> updateUserPlant(Long id, Long userId, UpdateRequest req) {
        return userPlantRepository.findById(id)
                .filter(up -> up.getUser().getId().equals(userId))
                .map(up -> {
                    if (req.nickname() != null)                up.setNickname(req.nickname());
                    if (req.notes() != null)                   up.setNotes(req.notes());
                    if (req.wateringIntervalDays() != null)    up.setWateringIntervalDays(req.wateringIntervalDays());
                    if (req.mistingIntervalDays() != null)     up.setMistingIntervalDays(req.mistingIntervalDays());
                    if (req.fertilizingIntervalDays() != null) up.setFertilizingIntervalDays(req.fertilizingIntervalDays());
                    if (req.leafCleaningIntervalDays() != null)up.setLeafCleaningIntervalDays(req.leafCleaningIntervalDays());
                    if (req.repottingIntervalDays() != null)   up.setRepottingIntervalDays(req.repottingIntervalDays());
                    if (req.pruningIntervalDays() != null)     up.setPruningIntervalDays(req.pruningIntervalDays());
                    if (req.pestCheckIntervalDays() != null)   up.setPestCheckIntervalDays(req.pestCheckIntervalDays());
                    return userPlantRepository.save(up);
                });
    }

    public boolean deleteUserPlant(Long id, Long userId) {
        return userPlantRepository.findById(id)
                .filter(up -> up.getUser().getId().equals(userId))
                .map(up -> { userPlantRepository.delete(up); return true; })
                .orElse(false);
    }

    public record UpdateRequest(
        String nickname,
        String notes,
        Integer wateringIntervalDays,
        Integer mistingIntervalDays,
        Integer fertilizingIntervalDays,
        Integer leafCleaningIntervalDays,
        Integer repottingIntervalDays,
        Integer pruningIntervalDays,
        Integer pestCheckIntervalDays
    ) {}
}
