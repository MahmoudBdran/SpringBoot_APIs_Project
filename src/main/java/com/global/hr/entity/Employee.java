package com.global.hr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "employee")
@NamedQuery(name="Employee.findBySalary" , query = "select emp from Employee emp where emp.salary >= :salary")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "emp_id")
    private Long id;
    private String name;
    private Double salary;
    @ManyToOne()
    @JoinColumn(name = "department_id")
    private Department department;
    @OneToOne()
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
