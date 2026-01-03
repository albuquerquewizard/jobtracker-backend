package com.example.jobtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jobtracker.entity.JobtrackerApp;

public interface JobtrackerAppRepository extends JpaRepository<JobtrackerApp, Long> {
    
}
