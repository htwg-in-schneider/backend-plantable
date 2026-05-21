package com.plantable.backend.controller;

import com.plantable.backend.model.Plant;
import com.plantable.backend.service.PlantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plants")
@CrossOrigin(origins = "http://localhost:5173")
public class PlantController {

    private final PlantService plantService;

    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping
    public List<Plant> getAllPlants() {
        return plantService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plant> getPlantById(@PathVariable Long id) {
        return plantService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<Plant> getPlantBySlug(@PathVariable String slug) {
        return plantService.findBySlug(slug)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Plant createPlant(@RequestBody Plant plant) {
        if (plant.getTags() != null) {
            plant.getTags().forEach(tag -> tag.setPlant(plant));
        }
        return plantService.save(plant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plant> updatePlant(@PathVariable Long id, @RequestBody Plant updatedPlant) {
        return plantService.findById(id)
                .map(existing -> {
                    existing.setBotanicalName(updatedPlant.getBotanicalName());
                    existing.setCommonName(updatedPlant.getCommonName());
                    existing.setSlug(updatedPlant.getSlug());
                    existing.setDescription(updatedPlant.getDescription());
                    existing.setMainImageUrl(updatedPlant.getMainImageUrl());
                    existing.setCareLevel(updatedPlant.getCareLevel());
                    existing.setLightRequirement(updatedPlant.getLightRequirement());
                    existing.setIsPetFriendly(updatedPlant.getIsPetFriendly());
                    existing.setIsAirPurifying(updatedPlant.getIsAirPurifying());
                    existing.setOriginRegion(updatedPlant.getOriginRegion());
                    existing.getTags().clear();
                    if (updatedPlant.getTags() != null) {
                        updatedPlant.getTags().forEach(tag -> {
                            tag.setPlant(existing);
                            existing.getTags().add(tag);
                        });
                    }
                    return ResponseEntity.ok(plantService.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable Long id) {
        plantService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}