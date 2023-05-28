package com.global.hr.repository;

import com.global.hr.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Long> {

public Optional<Department> findByName(String name);
}
