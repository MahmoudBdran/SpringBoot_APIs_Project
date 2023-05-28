package com.global.hr.controller;

import com.global.hr.HRStatisticProjection;
import com.global.hr.entity.Employee;
import com.global.hr.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/hello")
    ResponseEntity<String> hello(@RequestParam("name")String name) {
        if(name.contains("e")){
            return //new ResponseEntity<>("Contains e in "+name, HttpStatus.OK);
            ResponseEntity.ok()
                    .header("Custom-Header", "foo")
                    .body("Contains e in "+name);
        }else{

            return //new ResponseEntity<>("There is no e in name : "+name, HttpStatus.BAD_REQUEST);
            ResponseEntity.badRequest()
                    .header("Custom-Header", "foo")
                    .body("There is no e in name : "+name);
        }
    }
    @GetMapping("/customHeader")
    ResponseEntity<String> customHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "foo");
        return ResponseEntity.ok()
                .header("Custom-Header", "foo")
                .body("custom header set");
//        return new ResponseEntity<>(
//                "Custom header set", headers, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public Employee findById(@PathVariable Long id){
        return employeeService.findById(id);

    }

    @GetMapping("")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }
    @PostMapping()
    public Long insert(@RequestBody Employee emp){
        Employee insertEmp = employeeService.insert(emp);
        return insertEmp.getId();
    }
    @PutMapping()
    public Employee update(@RequestBody Employee emp){
        return employeeService.update(emp);
    }
    @GetMapping("/department/{deptId}")
    public List<Employee> findByDepartmentId(@PathVariable Long deptId){
        return employeeService.findByDepartmentId(deptId);
    }


    @GetMapping("/emp-dept")
    public List<Employee> findByNameContainingAndDepartmentNameContaining(@RequestParam String empName,@RequestParam String deptName){
        return employeeService.findByNameContainingAndDepartmentNameContaining(empName,deptName);
    }
    @GetMapping("/count-emp-dept")
    public ResponseEntity<Long> countByEmpAndDept(@RequestParam String empName, @RequestParam String deptName){
        return ResponseEntity.ok(employeeService.countByEmpAndDept(empName,deptName));
       // return new ResponseEntity<Long>(employeeService.countByEmpAndDept(empName,deptName), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/emp-dept")
    public ResponseEntity<Long> deleteByEmpAndDept(@RequestParam String empName, @RequestParam String deptName){
        return ResponseEntity.ok(employeeService.deleteByEmpAndDept(empName,deptName));
    }
    @GetMapping("/salary")
    public ResponseEntity<List<Employee>> findBySalary(@RequestParam double salary){
        return ResponseEntity.ok(employeeService.findBySalary(salary));
    }
    @GetMapping("/statistic")
    public ResponseEntity<HRStatisticProjection> getHRStatistic(){
        return ResponseEntity.ok(employeeService.getHRStatistic());
    }

}
