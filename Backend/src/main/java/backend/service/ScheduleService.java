package backend.service;

import backend.exception.ResourceNotFoundException;
import backend.model.Schedule;
import backend.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // CREATE
    public Schedule createSchedule(Schedule schedule) {
        schedule.setStatus("PENDING");
        return scheduleRepository.save(schedule);
    }

    // READ ALL
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    // READ ONE
    public Schedule getScheduleById(Long id) {

        return scheduleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Schedule not found with id: " + id
                        )
                );
    }

    // UPDATE
    public Schedule updateSchedule(Long id, Schedule schedule) {

        Schedule existingSchedule = scheduleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Schedule not found with id: " + id
                        )
                );

        existingSchedule.setPostId(schedule.getPostId());
        existingSchedule.setScheduledAt(schedule.getScheduledAt());

        return scheduleRepository.save(existingSchedule);
    }

    // DELETE
    public void deleteSchedule(Long id) {

        Schedule existingSchedule = scheduleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Schedule not found with id: " + id
                        )
                );

        scheduleRepository.delete(existingSchedule);
    }
}