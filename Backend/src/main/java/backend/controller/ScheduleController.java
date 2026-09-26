package backend.controller;

import backend.dto.ScheduleRequest;
import backend.model.Schedule;
import backend.response.ApiResponse;
import backend.service.ScheduleService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<Schedule>> createSchedule(
            @Valid @RequestBody ScheduleRequest request) {

        Schedule schedule = new Schedule(
                request.getPostId(),
                request.getScheduledAt(),
                "PENDING"
        );

        Schedule createdSchedule = scheduleService.createSchedule(schedule);

        ApiResponse<Schedule> response = new ApiResponse<>(
                true,
                "Schedule created successfully",
                createdSchedule
        );

        return ResponseEntity.ok(response);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<ApiResponse<List<Schedule>>> getAllSchedules() {

        List<Schedule> schedules = scheduleService.getAllSchedules();

        ApiResponse<List<Schedule>> response = new ApiResponse<>(
                true,
                "Schedules fetched successfully",
                schedules
        );

        return ResponseEntity.ok(response);
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Schedule>> getScheduleById(
            @PathVariable Long id) {

        Schedule schedule = scheduleService.getScheduleById(id);

        ApiResponse<Schedule> response = new ApiResponse<>(
                true,
                "Schedule fetched successfully",
                schedule
        );

        return ResponseEntity.ok(response);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Schedule>> updateSchedule(
            @PathVariable Long id,
            @Valid @RequestBody ScheduleRequest request) {

        Schedule schedule = new Schedule(
                request.getPostId(),
                request.getScheduledAt(),
                "PENDING"
        );

        Schedule updatedSchedule =
                scheduleService.updateSchedule(id, schedule);

        ApiResponse<Schedule> response = new ApiResponse<>(
                true,
                "Schedule updated successfully",
                updatedSchedule
        );

        return ResponseEntity.ok(response);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSchedule(
            @PathVariable Long id) {

        scheduleService.deleteSchedule(id);

        ApiResponse<Void> response = new ApiResponse<>(
                true,
                "Schedule deleted successfully",
                null
        );

        return ResponseEntity.ok(response);
    }
}