package com.example.jobtracker.service;

import java.util.List;

import com.example.jobtracker.dto.JobtrackerAppDTO;

public interface JobtrackerAppService {
    
    JobtrackerAppDTO create(JobtrackerAppDTO dto);

    List<JobtrackerAppDTO> getAll();

    JobtrackerAppDTO getById(Long id);

    JobtrackerAppDTO update(Long id, JobtrackerAppDTO dto);

    void delete(Long id);
}
