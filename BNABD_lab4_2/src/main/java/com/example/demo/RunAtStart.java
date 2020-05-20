package com.example.demo;

import com.example.demo.dao.DepartmentRepository;
import com.example.demo.dao.EmployeeRepository;
import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.dao.entity.Department;
import com.example.demo.dao.entity.Employee;
import com.example.demo.dao.entity.EnumRole;
import com.example.demo.dao.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class RunAtStart {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void runAtStart() {

        Department dept1 = departmentRepository.save(new Department("Finances"));
        Department dept2 = departmentRepository.save(new Department("Production"));

        Employee emp1 = employeeRepository.save(new Employee(1L, "Euzebiusz", "Nowacki", new BigDecimal("4000.50"),
                LocalDate.of(1999, 1, 1), dept1));
        Employee emp2 = employeeRepository.save(new Employee(2L, "Eugeniusz", "Nowak", new BigDecimal("1000.00"),
                LocalDate.of(1997, 1, 1), dept2));
        Employee emp3 = employeeRepository.save(new Employee(3L, "Jan", "Wiśniewski", new BigDecimal("2500.00"),
                LocalDate.of(1998, 1, 1), dept2));
        Employee emp4 = employeeRepository.save(new Employee(4L, "Kamil", "Wiśniewski", new BigDecimal("2800.00"),
                LocalDate.of(2001, 1, 1), dept2));

        Role role1 = roleRepository.save(new Role(EnumRole.ROLE_USER));
        Role role2 = roleRepository.save(new Role(EnumRole.ROLE_ADMIN));
    }
}
