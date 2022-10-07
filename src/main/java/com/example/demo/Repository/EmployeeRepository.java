package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
