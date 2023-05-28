package com.global.hr.repository;

import com.global.hr.HRStatisticProjection;
import com.global.hr.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Long> {
//        @Query("select emp from Employee emp where ")
//       List<Employee> filter(@Param("empName") String name);

    List<Employee> findBySalary(double salary);

    List<Employee> findByDepartmentId(Long deptId);
//    @Query("select emp from employee emp join emp.department dept where dept.id  = :deptId")
//    List<Employee> findByDepartment(Long deptId);


    List<Employee> findByNameContainingAndDepartmentNameContaining(String empName,String deptName);

    Long countByNameContainingAndDepartmentNameContaining(String empName, String deptName);
    @Modifying
    @Transactional
     Long deleteByNameContainingAndDepartmentNameContaining(String empName, String deptName);


    @Query(value = "select (select count(*) from department) deptCount," +
            "(select COUNT(*) from employee) empCount," +
            "(select COUNT(*) from sec_users) userCount;",nativeQuery = true)
    HRStatisticProjection getHRStatistic();
}
