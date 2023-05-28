package com.global.hr.repository;

import com.global.hr.HRStatisticProjection;
import com.global.hr.entity.Actor;
import com.global.hr.projection.ActorProjection;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepo extends JpaRepository<Actor,Long> {

    @Query(value = "select emp from Actor emp where (:empName is null or emp.firstName like :empName)")
    Page<Actor> filter (@Param("empName") String name, Pageable pageable);


    @Query(value = "select emp from Actor emp where (:empName is null or emp.firstName like :empName)")
    Page<ActorProjection> filterByProjection (@Param("empName") String name, Pageable pageable);




    //List<Actor> findBySalary(double salary);

    List<Actor> findByDepartmentId(Long deptId);


    List<Actor> findByFirstNameContainingAndDepartmentNameContaining(String empName,String deptName);

    Long countByFirstNameContainingAndDepartmentNameContaining(String empName, String deptName);
    @Modifying
    @Transactional
     Long deleteByFirstNameContainingAndDepartmentNameContaining(String empName, String deptName);


    @Query(value = "select (select count(*) from department) deptCount," +
            "(select COUNT(*) from employee) empCount," +
            "(select COUNT(*) from sec_users) userCount;",nativeQuery = true)
    HRStatisticProjection getHRStatistic();
}
