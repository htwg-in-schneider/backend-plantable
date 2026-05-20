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
        return plantService.save(plant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(@PathVariable Long id) {
        plantService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}