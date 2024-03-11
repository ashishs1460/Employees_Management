package com.ashish.employeeservice.service;

import com.ashish.employeeservice.model.Employee;
import com.ashish.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public void saveEmployee(Employee employee){
        repository.save(employee);
    }
    public List<Employee> findAllEmployees(){
        return  repository.findAll();
    }

    public Optional<Employee> findEmployeeById(Long id){
        return repository.findById(id);
    }




    public List<Employee> findByDepartment(Long departmentId) {
        return repository.findByDepartmentId(departmentId);
    }
}
