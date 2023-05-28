package com.global.hr.service;

import com.global.hr.HRStatisticProjection;
import com.global.hr.entity.Employee;
import com.global.hr.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;

    public Employee findById(Long id){
        return employeeRepo.findById(id).orElseThrow();

    }
    public List<Employee> findAll(){
        return employeeRepo.findAll();

    }


//    public List<Employee> filter(String name){
//        return employeeRepo.filterNative(name);
//
//    }

    public Employee insert(Employee emp){
        return employeeRepo.save(emp);
    }
    public Employee update(Employee emp){
       Employee current= employeeRepo.findById(emp.getId()).get();
       current.setName(emp.getName());
       current.setSalary(emp.getSalary());
       current.setDepartment(emp.getDepartment());
        return employeeRepo.save(current);
    }
    public List<Employee> findByDepartmentId(Long deptId){
        return employeeRepo.findByDepartmentId(deptId);
    }

//    public List<Employee> findByDepartment(Long deptId){
//        return employeeRepo.findByDepartment(deptId);
//    }

    public List<Employee> findByNameContainingAndDepartmentNameContaining(String empName,String deptName){
        return employeeRepo.findByNameContainingAndDepartmentNameContaining(empName,deptName);
    }



    public Long countByEmpAndDept(String empName, String deptName){
        return employeeRepo.countByNameContainingAndDepartmentNameContaining(empName,deptName);
    }

    public Long deleteByEmpAndDept(String empName, String deptName){
        return employeeRepo.deleteByNameContainingAndDepartmentNameContaining(empName,deptName);
    }


    public List<Employee> findBySalary(double salary){
        return employeeRepo.findBySalary(salary);
    }
    public HRStatisticProjection getHRStatistic(){
        return employeeRepo.getHRStatistic();
    }

}
