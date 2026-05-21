package com.plantable.backend.model;

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

    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Tag> tags = new ArrayList<>();

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

    public List<Tag> getTags() { return tags; }
    public void setTags(List<Tag> tags) { this.tags = tags; }
}