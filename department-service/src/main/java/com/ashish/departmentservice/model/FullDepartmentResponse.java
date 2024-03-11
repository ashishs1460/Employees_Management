package com.ashish.departmentservice.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FullDepartmentResponse {

    private String name;
    List<Employee> employees;
}

