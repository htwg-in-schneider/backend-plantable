package com.plantable.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_plants")
public class UserPlant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "plant_id", nullable = false)
    @JsonIgnoreProperties({"tags", "hibernateLazyInitializer", "handler"})
    private Plant plant;

    @Column(length = 200)
    private String nickname;

    @Column(name = "acquired_at", nullable = false)
    private LocalDate acquiredAt;

    @Column(name = "watering_interval_days", nullable = false)
    private Integer wateringIntervalDays;

    @Column(name = "misting_interval_days")
    private Integer mistingIntervalDays;

    @Column(name = "fertilizing_interval_days")
    private Integer fertilizingIntervalDays;

    @Column(name = "leaf_cleaning_interval_days")
    private Integer leafCleaningIntervalDays;

    @Column(name = "repotting_interval_days")
    private Integer repottingIntervalDays;

    @Column(name = "pruning_interval_days")
    private Integer pruningIntervalDays;

    @Column(name = "pest_check_interval_days")
    private Integer pestCheckIntervalDays;

    @Column(name = "last_watered_at")
    private LocalDate lastWateredAt;

    @Column(name = "last_misted_at")
    private LocalDate lastMistedAt;

    @Column(name = "last_fertilized_at")
    private LocalDate lastFertilizedAt;

    @Column(name = "last_leaf_cleaned_at")
    private LocalDate lastLeafCleanedAt;

    @Column(name = "last_repotted_at")
    private LocalDate lastRepottedAt;

    @Column(name = "last_pruned_at")
    private LocalDate lastPrunedAt;

    @Column(name = "last_pest_checked_at")
    private LocalDate lastPestCheckedAt;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @OneToMany(mappedBy = "userPlant", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<CareLog> careLogs = new ArrayList<>();

    public UserPlant() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Plant getPlant() { return plant; }
    public void setPlant(Plant plant) { this.plant = plant; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public LocalDate getAcquiredAt() { return acquiredAt; }
    public void setAcquiredAt(LocalDate acquiredAt) { this.acquiredAt = acquiredAt; }

    public Integer getWateringIntervalDays() { return wateringIntervalDays; }
    public void setWateringIntervalDays(Integer wateringIntervalDays) { this.wateringIntervalDays = wateringIntervalDays; }

    public Integer getMistingIntervalDays() { return mistingIntervalDays; }
    public void setMistingIntervalDays(Integer mistingIntervalDays) { this.mistingIntervalDays = mistingIntervalDays; }

    public Integer getFertilizingIntervalDays() { return fertilizingIntervalDays; }
    public void setFertilizingIntervalDays(Integer fertilizingIntervalDays) { this.fertilizingIntervalDays = fertilizingIntervalDays; }

    public Integer getLeafCleaningIntervalDays() { return leafCleaningIntervalDays; }
    public void setLeafCleaningIntervalDays(Integer leafCleaningIntervalDays) { this.leafCleaningIntervalDays = leafCleaningIntervalDays; }

    public Integer getRepottingIntervalDays() { return repottingIntervalDays; }
    public void setRepottingIntervalDays(Integer repottingIntervalDays) { this.repottingIntervalDays = repottingIntervalDays; }

    public Integer getPruningIntervalDays() { return pruningIntervalDays; }
    public void setPruningIntervalDays(Integer pruningIntervalDays) { this.pruningIntervalDays = pruningIntervalDays; }

    public Integer getPestCheckIntervalDays() { return pestCheckIntervalDays; }
    public void setPestCheckIntervalDays(Integer pestCheckIntervalDays) { this.pestCheckIntervalDays = pestCheckIntervalDays; }

    public LocalDate getLastWateredAt() { return lastWateredAt; }
    public void setLastWateredAt(LocalDate lastWateredAt) { this.lastWateredAt = lastWateredAt; }

    public LocalDate getLastMistedAt() { return lastMistedAt; }
    public void setLastMistedAt(LocalDate lastMistedAt) { this.lastMistedAt = lastMistedAt; }

    public LocalDate getLastFertilizedAt() { return lastFertilizedAt; }
    public void setLastFertilizedAt(LocalDate lastFertilizedAt) { this.lastFertilizedAt = lastFertilizedAt; }

    public LocalDate getLastLeafCleanedAt() { return lastLeafCleanedAt; }
    public void setLastLeafCleanedAt(LocalDate lastLeafCleanedAt) { this.lastLeafCleanedAt = lastLeafCleanedAt; }

    public LocalDate getLastRepottedAt() { return lastRepottedAt; }
    public void setLastRepottedAt(LocalDate lastRepottedAt) { this.lastRepottedAt = lastRepottedAt; }

    public LocalDate getLastPrunedAt() { return lastPrunedAt; }
    public void setLastPrunedAt(LocalDate lastPrunedAt) { this.lastPrunedAt = lastPrunedAt; }

    public LocalDate getLastPestCheckedAt() { return lastPestCheckedAt; }
    public void setLastPestCheckedAt(LocalDate lastPestCheckedAt) { this.lastPestCheckedAt = lastPestCheckedAt; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
