package com.ashish.departmentservice.service;

import com.ashish.departmentservice.model.Department;
import com.ashish.departmentservice.model.Employee;
import com.ashish.departmentservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public void addDepartment(Department department){
      departmentRepository.save(department);
    }

    public Optional<Department> findById(Long id){
        return departmentRepository.findById(id);
    }

    public List<Department> findAll(){
        return departmentRepository.findAll();
    }



}
