package com.example.demo.dao;

import com.example.demo.dao.entity.Department;
import com.example.demo.dao.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findByDepartment(Department department);
    List<Employee> findByLastNameAndDepartment(String lastName, Department department);
}
