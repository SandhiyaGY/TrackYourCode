package com.trackyourcode.repository;

import com.trackyourcode.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository
        extends JpaRepository<Task, Long> {
}
