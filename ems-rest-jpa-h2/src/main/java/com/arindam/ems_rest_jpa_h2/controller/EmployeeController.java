package com.arindam.ems_rest_jpa_h2.controller;

import com.arindam.ems_rest_jpa_h2.model.Employee;
import com.arindam.ems_rest_jpa_h2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(service.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int id) {
        Employee employee = service.getEmployeeById(id);
        if (employee != null)
            return new ResponseEntity<>(employee, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/employee/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        try {
            Employee newEmployee = service.addEmployee(employee);
            return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/employee/edit")
    public  ResponseEntity<String> updateEmployee(@RequestBody Employee employee) {
        Employee updatedEmployee = service.updateEmployee(employee);
        if (updatedEmployee != null)
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        Employee employee = service.getEmployeeById(id);
        if (employee != null) {
            service.deleteEmployee(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else
            return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/employee/keyword/{keyword}")
    public List<Employee> searchEmployee(@PathVariable("keyword") String keyword) {
        return service.searchEmployee(keyword);
    }
}
