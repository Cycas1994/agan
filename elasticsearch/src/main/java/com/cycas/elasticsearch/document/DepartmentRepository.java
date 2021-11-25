package com.cycas.elasticsearch.document;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface DepartmentRepository extends ElasticsearchRepository<Department, String> {

    List<Department> findAllByName(String name);

    @Query("{\"match\":{\"name\":\"?0\"}}")
    List<Department> findAllByNameUsingAnnotations(String name);
}
