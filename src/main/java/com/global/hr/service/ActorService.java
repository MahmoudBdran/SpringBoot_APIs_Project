package com.global.hr.service;

import com.global.hr.HRStatisticProjection;
import com.global.hr.entity.Actor;
import com.global.hr.entity.Actor;
import com.global.hr.projection.ActorProjection;
import com.global.hr.repository.ActorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ActorService {
    @Autowired
    private ActorRepo actorRepo;

    public Actor findById(Long id){
        return actorRepo.findById(id).orElseThrow();

    }
    public Page<Actor> filterWithFirstName(String name, int pageNum, int pageSize, String sortCol , boolean isAsc){
        if(name.isEmpty()|| name.isBlank() ||name==null){
            name=null;
        }
        Pageable page = PageRequest.of(pageNum,pageSize,Sort.by(isAsc? Sort.Direction.ASC: Sort.Direction.DESC,sortCol));
        return actorRepo.filter(name, page);

    }


    public Page<ActorProjection> filterProjectionWithFirstName(String name, int pageNum , int pageSize , String sortCol , boolean isAsc){
        if(name.isEmpty()|| name.isBlank() ||name==null){
            name=null;
        }
        Pageable page = PageRequest.of(pageNum,pageSize,Sort.by(isAsc? Sort.Direction.ASC: Sort.Direction.DESC , sortCol));
        return actorRepo.filterByProjection(name,page);
    }
    public Actor insert(Actor emp){
        return actorRepo.save(emp);
    }
    public Actor update(Actor emp){
       Actor current= actorRepo.findById(emp.getId()).get();
       current.setFirstName(emp.getFirstName());
       current.setLastName(emp.getLastName());
       current.setFirstName(emp.getFirstName());
       current.setLastupdate(emp.getLastupdate());
       current.setDepartment(emp.getDepartment());
        return actorRepo.save(current);
    }
    public List<Actor> findByDepartmentId(Long deptId){
        return actorRepo.findByDepartmentId(deptId);
    }

//    public List<Actor> findByDepartment(Long deptId){
//        return ActorRepo.findByDepartment(deptId);
//    }

    public List<Actor> findByFirstNameContainingAndDepartmentNameContaining(String empName,String deptName){
        return actorRepo.findByFirstNameContainingAndDepartmentNameContaining(empName,deptName);
    }

    public Long countByEmpAndDept(String empName, String deptName){
        return actorRepo.countByFirstNameContainingAndDepartmentNameContaining(empName,deptName);
    }

    public Long deleteByEmpAndDept(String empName, String deptName){
        return actorRepo.deleteByFirstNameContainingAndDepartmentNameContaining(empName,deptName);
    }


//    public List<Actor> findBySalary(double salary){
//        return actorRepo.findBySalary(salary);
//    }
    public HRStatisticProjection getHRStatistic(){
        return actorRepo.getHRStatistic();
    }

}
