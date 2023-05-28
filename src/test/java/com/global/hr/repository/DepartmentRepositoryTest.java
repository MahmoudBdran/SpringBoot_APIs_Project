package com.global.hr.repository;

import com.global.hr.entity.Department;
import com.global.hr.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class DepartmentRepositoryTest {
@Mock
DepartmentRepo departmentRepo;

@InjectMocks
DepartmentService departmentService;
//    @Test
//    void FindByIdNotFoundTest(){
//        Optional<Department> deptParam=  Optional.of(new Department(123L,"OS"));
//        Mockito.when(departmentRepo.findById(Mockito.anyLong())).thenReturn(deptParam);
//        Optional<Department> department = departmentService.findById(123L);
//        assertEquals(true,department.isPresent());
//        assertEquals("OS",department.get().getName());
//    }


    @Test
    void SaveDeptTest(){
        Department deptParam=  new Department(123L,"OS");
        Mockito.when(departmentRepo.save(deptParam)).thenReturn(deptParam);
        Department department = departmentService.insert(deptParam);
        assertNotNull(department);
        assertEquals(deptParam,department);
        assertEquals(true,department.getName()=="OS");


    }



}
