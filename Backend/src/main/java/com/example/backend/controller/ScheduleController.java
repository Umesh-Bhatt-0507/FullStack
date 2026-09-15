package com.example.backend.controller;

import com.example.backend.dto.ApiResponse;
import com.example.backend.entity.Schedule;
import com.example.backend.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService service;

    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Schedule>>> getAllSchedules() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Schedules fetched successfully",
                        service.getAllSchedules()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Schedule>> getSchedule(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Schedule fetched successfully",
                        service.getSchedule(id)
                )
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Schedule>> createSchedule(
            @Valid @RequestBody Schedule schedule) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        new ApiResponse<>(
                                true,
                                "Schedule created successfully",
                                service.createSchedule(schedule)
                        )
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Schedule>> updateSchedule(
            @PathVariable Long id,
            @Valid @RequestBody Schedule schedule) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Schedule updated successfully",
                        service.updateSchedule(id, schedule)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSchedule(
            @PathVariable Long id) {

        service.deleteSchedule(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Schedule deleted successfully",
                        null
                )
        );
    }
}