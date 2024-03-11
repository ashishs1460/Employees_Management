package com.ashish.departmentservice.controller;

import com.ashish.departmentservice.client.EmployeeClient;
import com.ashish.departmentservice.model.Department;
import com.ashish.departmentservice.model.Employee;
import com.ashish.departmentservice.model.FullDepartmentResponse;
import com.ashish.departmentservice.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(DepartmentController.class);
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeClient employeeClient;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Department department){
        LOGGER.info("Department add: {}", department);
        departmentService.addDepartment(department);
    }

    @GetMapping
    public ResponseEntity<List<Department>> findAllStudents(){
        LOGGER.info("Department find");
      return ResponseEntity.ok(departmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> findById(@PathVariable Long id){
        LOGGER.info("Department find: id={}",id);
        Optional<Department> optionalDepartment = departmentService.findById(id);
        if(optionalDepartment.isPresent()){
            return ResponseEntity.ok(optionalDepartment.get());
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/with-employees")
    public ResponseEntity<List<FullDepartmentResponse>> findAllWithEmployees() {
        LOGGER.info("Departments find with employees");

        List<Department> departments = departmentService.findAll();
        List<FullDepartmentResponse> departmentResponses = new ArrayList<>();

        for (Department department : departments) {
            List<Employee> employees = employeeClient.findByDepartment(department.getId());
            FullDepartmentResponse departmentResponse = FullDepartmentResponse.builder()
                    .name(department.getName())
                    .employees(employees)
                    .build();
            departmentResponses.add(departmentResponse);
        }

        return ResponseEntity.ok(departmentResponses);
    }


}
