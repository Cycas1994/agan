package com.cycas.elasticsearch.controller;

import com.alibaba.fastjson.JSON;
import com.cycas.elasticsearch.pojo.dmo.Person;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 客户端api操作es
 */
@RequestMapping("/operate")
@RestController
public class ElasticsearchApi {

    private static final String INDEX = "persondata";

    @Autowired
    private RestHighLevelClient restHighLevelClient;



    @RequestMapping("/insertPerson")
    public Person insertPerson(@RequestBody Person person) {

        person.setPersonId(UUID.randomUUID().toString());
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", person.getName());
        dataMap.put("number", person.getNumber());
        IndexRequest indexRequest = new IndexRequest(INDEX).id(person.getPersonId()).source(dataMap);
        try {
            IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return person;
    }

    @RequestMapping("/getPersonById")
    public Person getPersonById(String id) {

        GetRequest getRequest = new GetRequest(INDEX, id);
        GetResponse getResponse = null;
        try {
            getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getResponse != null ? JSON.parseObject(getResponse.getSourceAsString(), Person.class) : null;
    }

    @RequestMapping("/updatePersonById")
    public Person updatePersonById(@RequestParam("id") String id, @RequestBody Person person) {

        UpdateRequest updateRequest = new UpdateRequest(INDEX, id).fetchSource(true);
        try {
            String personJson = JSON.toJSONString(person);
            updateRequest.doc(personJson, XContentType.JSON);
            UpdateResponse updateResponse = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
            return JSON.parseObject(updateResponse.getGetResult().sourceAsString(), Person.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/deletePersonById")
    public void deletePersonById(String id) {

        DeleteRequest deleteRequest = new DeleteRequest(INDEX, id);
        try {
            restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
