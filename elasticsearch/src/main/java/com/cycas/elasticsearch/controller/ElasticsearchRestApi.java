package com.cycas.elasticsearch.controller;

import com.cycas.elasticsearch.pojo.dmo.Employee;
import com.cycas.elasticsearch.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * rest Api操作es，推荐
 */
@RestController
@RequestMapping("/employee")
public class ElasticsearchRestApi {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value ="/allemployees", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployeeInfo();
    }

    @GetMapping(value ="/allemployees/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getUserByName(@PathVariable String name){
        return employeeService.getEmployeesByName(name);
    }

    @GetMapping(value ="/allemployees/{name}/{address}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getUserByNameAndAddress(@PathVariable String name, @PathVariable String address){
        return employeeService.getEmployeesByNameAndOccupation(name, address);
    }
}
