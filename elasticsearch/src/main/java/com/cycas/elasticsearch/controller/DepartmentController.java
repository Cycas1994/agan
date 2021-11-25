package com.cycas.elasticsearch.controller;

import com.cycas.elasticsearch.document.Department;
import com.cycas.elasticsearch.service.DepartmentService;
import com.cycas.elasticsearch.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public void save(@RequestBody final Department department) {
        departmentService.save(department);
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable final String id) {
        return departmentService.findById(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteById(@PathVariable String id) {
        departmentService.deleteEmployee(id);
        return true;
    }

    @GetMapping("/name/{name}")
    public List<Department> findAllByName(@PathVariable String name) {
        return departmentService.findByName(name);
    }

    @GetMapping("/name/{name}/annotations")
    public List<Department> findAllByNameAnnotations(@PathVariable String name) {
        return departmentService.getDepartmentByNameUsingAnnotation(name);
    }
}
