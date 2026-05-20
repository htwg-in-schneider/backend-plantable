package com.plantable.backend.service;

import com.plantable.backend.model.Plant;
import com.plantable.backend.repository.PlantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlantService {

    private final PlantRepository plantRepository;

    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public List<Plant> findAll() {
        return plantRepository.findAll();
    }

    public Optional<Plant> findById(Long id) {
        return plantRepository.findById(id);
    }

    public Optional<Plant> findBySlug(String slug) {
        return plantRepository.findBySlug(slug);
    }

    public Plant save(Plant plant) {
        return plantRepository.save(plant);
    }

    public void deleteById(Long id) {
        plantRepository.deleteById(id);
    }
}