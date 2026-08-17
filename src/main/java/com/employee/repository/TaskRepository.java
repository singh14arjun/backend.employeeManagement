package com.employee.repository;

import com.employee.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {



}
