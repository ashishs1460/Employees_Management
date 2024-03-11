package com.ashish.employeeservice.controller;

import com.ashish.employeeservice.model.Employee;
import com.ashish.employeeservice.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private static final Logger LOGGER =
            LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    EmployeeService employeeService;
    @PostMapping
    public void addEmployee(@RequestBody Employee employee){
        LOGGER.info("Employee add: {}",employee);
        employeeService.saveEmployee(employee);
    }
    @GetMapping
    public ResponseEntity<List<Employee>>findAll(){
        LOGGER.info("Employee find");
       return ResponseEntity.ok(employeeService.findAllEmployees());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Employee> findById(@PathVariable Long id){
        LOGGER.info("Employee find : id={}",id);
        Optional<Employee> optionalEmployee = employeeService.findEmployeeById(id);
        if(optionalEmployee.isPresent()){
            return ResponseEntity.ok(optionalEmployee.get());
        }
        return ResponseEntity.notFound()
                .build();
    }
    @GetMapping("/department/{departmentId}")
    public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId) {
        LOGGER.info("Employee find: departmentId={}", departmentId);
        return employeeService.findByDepartment(departmentId);
    }
}
