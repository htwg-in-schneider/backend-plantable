package com.plantable.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "plants")
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "botanical_name", nullable = false, unique = true, length = 150)
    private String botanicalName;

    @Column(name = "common_name", nullable = false, length = 150)
    private String commonName;

    @Column(nullable = false, unique = true, length = 150)
    private String slug;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "main_image_url", length = 500)
    private String mainImageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "care_level", nullable = false, length = 20)
    private CareLevel careLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "light_requirement", nullable = false, length = 20)
    private LightRequirement lightRequirement;

    @Column(name = "is_pet_friendly", nullable = false)
    private Boolean isPetFriendly = false;

    @Column(name = "is_air_purifying", nullable = false)
    private Boolean isAirPurifying = false;

    @Column(name = "origin_region", length = 100)
    private String originRegion;

    @Column(name = "price", nullable = false)
    private Double price;

    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "plant", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<UserPlant> userPlants = new ArrayList<>();

    @Column(name = "default_watering_interval_days")
    private Integer defaultWateringIntervalDays;

    @Column(name = "default_misting_interval_days")
    private Integer defaultMistingIntervalDays;

    @Column(name = "default_fertilizing_interval_days")
    private Integer defaultFertilizingIntervalDays;

    @Column(name = "default_leaf_cleaning_interval_days")
    private Integer defaultLeafCleaningIntervalDays;

    @Column(name = "default_repotting_interval_days")
    private Integer defaultRepottingIntervalDays;

    @Column(name = "default_pruning_interval_days")
    private Integer defaultPruningIntervalDays;

    @Column(name = "default_pest_check_interval_days")
    private Integer defaultPestCheckIntervalDays;

    public Plant() {}

    public Plant(String botanicalName, String commonName, String slug,
                 String description, String mainImageUrl,
                 CareLevel careLevel, LightRequirement lightRequirement,
                 Boolean isPetFriendly, Boolean isAirPurifying,
                 String originRegion) {
        this.botanicalName = botanicalName;
        this.commonName = commonName;
        this.slug = slug;
        this.description = description;
        this.mainImageUrl = mainImageUrl;
        this.careLevel = careLevel;
        this.lightRequirement = lightRequirement;
        this.isPetFriendly = isPetFriendly;
        this.isAirPurifying = isAirPurifying;
        this.originRegion = originRegion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBotanicalName() { return botanicalName; }
    public void setBotanicalName(String botanicalName) { this.botanicalName = botanicalName; }

    public String getCommonName() { return commonName; }
    public void setCommonName(String commonName) { this.commonName = commonName; }

    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getMainImageUrl() { return mainImageUrl; }
    public void setMainImageUrl(String mainImageUrl) { this.mainImageUrl = mainImageUrl; }

    public CareLevel getCareLevel() { return careLevel; }
    public void setCareLevel(CareLevel careLevel) { this.careLevel = careLevel; }

    public LightRequirement getLightRequirement() { return lightRequirement; }
    public void setLightRequirement(LightRequirement lightRequirement) { this.lightRequirement = lightRequirement; }

    public Boolean getIsPetFriendly() { return isPetFriendly; }
    public void setIsPetFriendly(Boolean isPetFriendly) { this.isPetFriendly = isPetFriendly; }

    public Boolean getIsAirPurifying() { return isAirPurifying; }
    public void setIsAirPurifying(Boolean isAirPurifying) { this.isAirPurifying = isAirPurifying; }

    public String getOriginRegion() { return originRegion; }
    public void setOriginRegion(String originRegion) { this.originRegion = originRegion; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public List<Tag> getTags() { return tags; }
    public void setTags(List<Tag> tags) { this.tags = tags; }

    public Integer getDefaultWateringIntervalDays() { return defaultWateringIntervalDays; }
    public void setDefaultWateringIntervalDays(Integer defaultWateringIntervalDays) { this.defaultWateringIntervalDays = defaultWateringIntervalDays; }

    public Integer getDefaultMistingIntervalDays() { return defaultMistingIntervalDays; }
    public void setDefaultMistingIntervalDays(Integer defaultMistingIntervalDays) { this.defaultMistingIntervalDays = defaultMistingIntervalDays; }

    public Integer getDefaultFertilizingIntervalDays() { return defaultFertilizingIntervalDays; }
    public void setDefaultFertilizingIntervalDays(Integer defaultFertilizingIntervalDays) { this.defaultFertilizingIntervalDays = defaultFertilizingIntervalDays; }

    public Integer getDefaultLeafCleaningIntervalDays() { return defaultLeafCleaningIntervalDays; }
    public void setDefaultLeafCleaningIntervalDays(Integer defaultLeafCleaningIntervalDays) { this.defaultLeafCleaningIntervalDays = defaultLeafCleaningIntervalDays; }

    public Integer getDefaultRepottingIntervalDays() { return defaultRepottingIntervalDays; }
    public void setDefaultRepottingIntervalDays(Integer defaultRepottingIntervalDays) { this.defaultRepottingIntervalDays = defaultRepottingIntervalDays; }

    public Integer getDefaultPruningIntervalDays() { return defaultPruningIntervalDays; }
    public void setDefaultPruningIntervalDays(Integer defaultPruningIntervalDays) { this.defaultPruningIntervalDays = defaultPruningIntervalDays; }

    public Integer getDefaultPestCheckIntervalDays() { return defaultPestCheckIntervalDays; }
    public void setDefaultPestCheckIntervalDays(Integer defaultPestCheckIntervalDays) { this.defaultPestCheckIntervalDays = defaultPestCheckIntervalDays; }
}