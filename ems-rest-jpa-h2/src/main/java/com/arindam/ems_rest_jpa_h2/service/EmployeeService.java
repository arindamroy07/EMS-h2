package com.arindam.ems_rest_jpa_h2.service;

import com.arindam.ems_rest_jpa_h2.model.Employee;
import com.arindam.ems_rest_jpa_h2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getEmployeeById(int id) {
        return repository.findById(id).get();
    }

    public Employee addEmployee(Employee employee) {
        return repository.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        return repository.save(employee);
    }

    public void deleteEmployee(int id) {
        repository.deleteById(id);
    }

    public List<Employee> searchEmployee(String keyword) {
        return repository.findByNameContainingOrDepartmentContaining(keyword, keyword);
    }
}
