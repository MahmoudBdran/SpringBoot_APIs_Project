package com.global.hr.controller;

import com.global.hr.HRStatisticProjection;
import com.global.hr.entity.Actor;
import com.global.hr.projection.ActorProjection;
import com.global.hr.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actor")
public class ActorController {
    @Autowired
    private ActorService actorService;
    @GetMapping("/{id}")
    public Actor findById(@PathVariable Long id){
        return actorService.findById(id);

    }
    @PostMapping()
    public Long insert(@RequestBody Actor emp){
        Actor insertEmp = actorService.insert(emp);
        return insertEmp.getId();
    }
    @PutMapping()
    public Actor update(@RequestBody Actor emp){
        return actorService.update(emp);
    }
    @GetMapping("/department/{deptId}")
    public List<Actor> findByDepartmentId(@PathVariable Long deptId){
        return actorService.findByDepartmentId(deptId);
    }


    @GetMapping("/emp-dept")
    public List<Actor> findByFirstNameContainingAndDepartmentNameContaining(@RequestParam String empName,@RequestParam String deptName){
        return actorService.findByFirstNameContainingAndDepartmentNameContaining(empName,deptName);
    }
    @GetMapping("/count-emp-dept")
    public ResponseEntity<Long> countByEmpAndDept(@RequestParam String empName, @RequestParam String deptName){
        return ResponseEntity.ok(actorService.countByEmpAndDept(empName,deptName));
    }


    @DeleteMapping("/emp-dept")
    public ResponseEntity<Long> deleteByEmpAndDept(@RequestParam String empName, @RequestParam String deptName){
        return ResponseEntity.ok(actorService.deleteByEmpAndDept(empName,deptName));
    }
//    @GetMapping("/salary")
//    public ResponseEntity<List<Actor>> findBySalary(@RequestParam double salary){
//        return ResponseEntity.ok(actorService.findBySalary(salary));
//    }
    @GetMapping("/statistic")
    public ResponseEntity<HRStatisticProjection> getHRStatistic(){
        return ResponseEntity.ok(actorService.getHRStatistic());
    }
    @GetMapping("/filter")
    public ResponseEntity<Page<Actor>> filter(@RequestParam String name,
                              @RequestParam boolean isAsc,
                              @RequestParam int pageNum,
                              @RequestParam int pageSize,
                              @RequestParam String sortCol){
        return ResponseEntity.ok(actorService.filterWithFirstName(name,pageNum,pageSize,sortCol,isAsc));

    }


    @GetMapping("/filterprojection")
    public ResponseEntity<Page<ActorProjection>> filterProjection(@RequestParam String name,
                                                                  @RequestParam boolean isAsc,
                                                                  @RequestParam int pageNum,
                                                                  @RequestParam int pageSize,
                                                                  @RequestParam String sortCol){
        return ResponseEntity.ok(actorService.filterProjectionWithFirstName(name,pageNum,pageSize,sortCol,isAsc));

    }

}
