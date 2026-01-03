package com.example.jobtracker.service.impl;

import com.example.jobtracker.dto.JobtrackerAppDTO;
import com.example.jobtracker.entity.JobtrackerApp;
import com.example.jobtracker.exception.ResourceNotFoundException;
import com.example.jobtracker.repository.JobtrackerAppRepository;
import com.example.jobtracker.service.JobtrackerAppService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobtrackerAppServiceImpl implements JobtrackerAppService {

    private final JobtrackerAppRepository repository;

    // Constructor Injection (BEST PRACTICE)
    public JobtrackerAppServiceImpl(JobtrackerAppRepository repository) {
        this.repository = repository;
    }

    // CREATE
    @Override
    public JobtrackerAppDTO create(JobtrackerAppDTO dto) {
        JobtrackerApp entity = mapToEntity(dto);
        JobtrackerApp saved = repository.save(entity);
        return mapToDTO(saved);
    }

    // READ - ALL
    @Override
    public List<JobtrackerAppDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // READ - BY ID
    @Override
    public JobtrackerAppDTO getById(Long id) {
        JobtrackerApp entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException());
        return mapToDTO(entity);
    }

    // UPDATE
    @Override
    public JobtrackerAppDTO update(Long id, JobtrackerAppDTO dto) {
        JobtrackerApp entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job application not found with id: " + id));

        entity.setCompanyName(dto.getCompanyName());
        entity.setRole(dto.getRole());

        if (dto.getStatus() != null) {
            entity.setStatus(com.example.jobtracker.entity.JobStatus.valueOf(dto.getStatus()));
        }

        entity.setAppliedDate(dto.getAppliedDate());

        JobtrackerApp updated = repository.save(entity);
        return mapToDTO(updated);
    }

    // DELETE
    @Override
    public void delete(Long id) {
        JobtrackerApp entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job application not found with id: " + id));
        repository.delete(entity);
    }

    // =====================
    // MAPPING METHODS
    // =====================

    private JobtrackerApp mapToEntity(JobtrackerAppDTO dto) {
        JobtrackerApp entity = new JobtrackerApp();
        entity.setCompanyName(dto.getCompanyName());
        entity.setRole(dto.getRole());
        if (dto.getStatus() != null) {
            entity.setStatus(com.example.jobtracker.entity.JobStatus.valueOf(dto.getStatus()));
        }
        entity.setAppliedDate(dto.getAppliedDate());
        return entity;
    }

    private JobtrackerAppDTO mapToDTO(JobtrackerApp entity) {
        JobtrackerAppDTO dto = new JobtrackerAppDTO();
        dto.setId(entity.getId());
        dto.setCompanyName(entity.getCompanyName());
        dto.setRole(entity.getRole());
        if (entity.getStatus() != null) {
            dto.setStatus(entity.getStatus().name());
        }
        dto.setAppliedDate(entity.getAppliedDate());
        return dto;
    }
}
