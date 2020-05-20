package com.example.demo.api;

import com.example.demo.dao.entity.Employee;
import com.example.demo.manager.EmployeeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/employees")
public class EmployeeApi {

    private EmployeeManager employeeManager;

    @Autowired
    public EmployeeApi(EmployeeManager employeeManager) {

        this.employeeManager = employeeManager;
    }

    @GetMapping("/all")
    public Iterable<Employee> getAll() {

        return employeeManager.findAllEmployees();
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Optional<Employee> getById(@RequestParam Long index) {

        return employeeManager.findEmployeeById(index);
    }


    @GetMapping("/allByDeptName")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Iterable<Employee> getAllEmployeesFromDepartment(@RequestParam(name = "departmentName") String departmentName) {

        return employeeManager.findAllEmployeesInDepartment(departmentName);
    }

    @GetMapping("/allByDeptIdAndEmployeeName")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Iterable<Employee> getAllEmployeesByDeptIdAndEmplName(@RequestParam(name = "departmentId") Long departmentId,
                                                                  @RequestParam(name = "employeeLastName") String employeeLastName) {

        return employeeManager.findAllEmployeesByDeptIdAndEmplName(departmentId, employeeLastName);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee emp = employeeManager.saveEmployee(employee);
        return  new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteEmployee(@RequestParam(name = "employeeId") Long employeeId) {
        employeeManager.deleteEmployeeById(employeeId);
        return new ResponseEntity<>("Employee with ID:" + employeeId + " deleted successfully", HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee emp = employeeManager.saveEmployee(employee);
        return  new ResponseEntity<>(emp, HttpStatus.OK);
    }

}
