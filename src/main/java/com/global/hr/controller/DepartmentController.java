package com.global.hr.controller;

import com.global.hr.entity.Department;
import com.global.hr.entity.Employee;
import com.global.hr.service.DepartmentService;
import com.global.hr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
//    @GetMapping("/{id}")
//    public Department findById(@PathVariable Long id){
//        return departmentService.findById(id);
//
//    }

@GetMapping("/{id}")
public ResponseEntity<Optional<Department>> findById(@PathVariable Long id){
    return ResponseEntity.ok(departmentService.findById(id));

}
//    @GetMapping(value = {"","/"})
//    public List<Department> findAll(){
//        return departmentService.findAll();
//
//    }
@GetMapping(value = {"","/"})
public ResponseEntity<List<Department>> findAll(){
    return ResponseEntity.ok(departmentService.findAll());

}
    @PostMapping()
    public Department insert(@RequestBody Department department){
        return departmentService.insert(department);
    }
    @PutMapping()
    public Department update(@RequestBody Department department){
                return departmentService.update(department);
    }
}
