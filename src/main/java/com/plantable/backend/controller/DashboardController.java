package com.plantable.backend.controller;

import com.plantable.backend.model.CareItemDto;
import com.plantable.backend.model.DueStatus;
import com.plantable.backend.service.UserPlantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:5173")
public class DashboardController {

    private final UserPlantService userPlantService;

    public DashboardController(UserPlantService userPlantService) {
        this.userPlantService = userPlantService;
    }

    @GetMapping("/care-items")
    public ResponseEntity<Map<DueStatus, List<CareItemDto>>> getCareItems(
            @RequestHeader("X-User-Id") Long userId) {
        return ResponseEntity.ok(userPlantService.getDashboardCareItems(userId));
    }
}
