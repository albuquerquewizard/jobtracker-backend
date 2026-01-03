package com.example.jobtracker.controller;

import com.example.jobtracker.dto.JobtrackerAppDTO;
import com.example.jobtracker.service.JobtrackerAppService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "http://localhost:3000")
public class JobtrackerAppController {

    private final JobtrackerAppService service;

    // Constructor Injection
    public JobtrackerAppController(JobtrackerAppService service) {
        this.service = service;
    }

    // CREATE JOB APPLICATION
    @PostMapping
    public JobtrackerAppDTO createJob(@Valid @RequestBody JobtrackerAppDTO dto) {
        return service.create(dto);
    }

    // GET ALL JOB APPLICATIONS
    @GetMapping
    public List<JobtrackerAppDTO> getAllJobs() {
        return service.getAll();
    }

    // GET JOB BY ID
    @GetMapping("/{id}")
    public JobtrackerAppDTO getJobById(@PathVariable Long id) {
        return service.getById(id);
    }

    // UPDATE JOB
    @PutMapping("/{id}")
    public JobtrackerAppDTO updateJob(
            @PathVariable Long id,
            @Valid @RequestBody JobtrackerAppDTO dto) {
        return service.update(id, dto);
    }

    // DELETE JOB
    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id) {
        service.delete(id);
    }

}
