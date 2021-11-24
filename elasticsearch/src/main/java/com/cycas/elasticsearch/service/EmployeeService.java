package com.cycas.elasticsearch.service;

import com.cycas.elasticsearch.pojo.dmo.Employee;
import com.cycas.elasticsearch.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployeeInfo() {
        return employeeRepository.findAllEmployeeDetailsFromES();
    }

    public List<Employee> getEmployeesByName(String name) {
        return employeeRepository.findAllUserDataByNameFromES(name);
    }

    public List<Employee> getEmployeesByNameAndOccupation(String name, String occupation) {
        return employeeRepository.findAllUserDataByNameAndOccupationFromES(name, occupation);
    }
}
