package com.example.backend.service;

import com.example.backend.entity.Schedule;
import com.example.backend.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository repository;

    public ScheduleService(ScheduleRepository repository) {
        this.repository = repository;
    }

    public List<Schedule> getAllSchedules() {
        return repository.findAll();
    }

    public Schedule getSchedule(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
    }

    public Schedule createSchedule(Schedule schedule) {
        return repository.save(schedule);
    }

    public Schedule updateSchedule(Long id, Schedule schedule) {

        Schedule oldSchedule = getSchedule(id);

        oldSchedule.setPost(schedule.getPost());
        oldSchedule.setPlatform(schedule.getPlatform());
        oldSchedule.setScheduledTime(schedule.getScheduledTime());

        return repository.save(oldSchedule);
    }

    public void deleteSchedule(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Schedule not found");
        }

        repository.deleteById(id);
    }
}