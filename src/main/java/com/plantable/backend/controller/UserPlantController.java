package com.plantable.backend.controller;

import com.plantable.backend.model.CareAction;
import com.plantable.backend.model.CareLog;
import com.plantable.backend.model.CareType;
import com.plantable.backend.model.UserPlant;
import com.plantable.backend.service.UserPlantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/user-plants")
@CrossOrigin(origins = "http://localhost:5173")
public class UserPlantController {

    private final UserPlantService userPlantService;

    public UserPlantController(UserPlantService userPlantService) {
        this.userPlantService = userPlantService;
    }

    @PostMapping
    public ResponseEntity<UserPlant> addPlant(
            @RequestHeader("X-User-Id") Long userId,
            @RequestBody AddPlantRequest request) {
        UserPlant up = userPlantService.addPlantToDashboard(
            userId, request.plantId(), request.nickname(), request.acquiredAt());
        return ResponseEntity.ok(up);
    }

    @GetMapping("/me")
    public ResponseEntity<List<UserPlant>> getMyPlants(@RequestHeader("X-User-Id") Long userId) {
        return ResponseEntity.ok(userPlantService.getUserPlants(userId));
    }

    @PostMapping("/{id}/care")
    public ResponseEntity<?> logCare(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long userId,
            @RequestBody CareRequest request) {
        try {
            CareLog log = userPlantService.logCareAction(id, userId, request.careType(), request.action());
            return ResponseEntity.ok(log);
        } catch (IllegalAccessError e) {
            return ResponseEntity.status(403).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserPlant> updatePlant(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long userId,
            @RequestBody UserPlantService.UpdateRequest request) {
        return userPlantService.updateUserPlant(id, userId, request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlant(
            @PathVariable Long id,
            @RequestHeader("X-User-Id") Long userId) {
        if (userPlantService.deleteUserPlant(id, userId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    record AddPlantRequest(Long plantId, String nickname, LocalDate acquiredAt) {}

    record CareRequest(CareType careType, CareAction action) {}
}
