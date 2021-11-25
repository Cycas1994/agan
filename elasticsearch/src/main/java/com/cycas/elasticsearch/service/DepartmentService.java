package com.cycas.elasticsearch.service;

import com.cycas.elasticsearch.document.Department;
import com.cycas.elasticsearch.document.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    @Autowired
    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public void save(final Department department) {
        repository.save(department);
    }

    public Department findById(final String id) {
        return repository.findById(id).orElse(null);
    }

    public List<Department> findByName(final String name) {
        return repository.findAllByName(name);
    }

    public List<Department> getDepartmentByNameUsingAnnotation(String name) {
        return repository.findAllByNameUsingAnnotations(name);
    }

    public void deleteEmployee(String id) {
        repository.deleteById(id);
    }

}
