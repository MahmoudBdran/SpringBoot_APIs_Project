package com.global.hr.service;

import com.global.hr.entity.Department;
import com.global.hr.entity.Employee;
import com.global.hr.repository.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepo departmentRepo;

    public Optional<Department> findById(Long id){
        return departmentRepo.findById(id);

    }
    public Optional<Department> findByName(String name){
        return departmentRepo.findByName(name);

    }

    public List<Department> findAll(){
        return departmentRepo.findAll();

    }
    public Department insert(Department department){
        return departmentRepo.save(department);
    }
    public Department update(Department department){
        Department current= departmentRepo.findById(department.getId()).orElseThrow();
        current.setName(department.getName());
        return departmentRepo.save(current);
    }
}
