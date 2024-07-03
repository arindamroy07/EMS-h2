package com.arindam.ems_rest_jpa_h2.repository;

import com.arindam.ems_rest_jpa_h2.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByNameContainingOrDepartmentContaining(String name, String department);
}
