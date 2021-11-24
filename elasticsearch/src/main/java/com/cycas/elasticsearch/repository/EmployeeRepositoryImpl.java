package com.cycas.elasticsearch.repository;

import com.cycas.elasticsearch.pojo.dmo.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private static RestHighLevelClient client = null;

    @Autowired
    private ObjectMapper objectMapper;

    public EmployeeRepositoryImpl() {
        makeConnection();
    }

    private static synchronized RestHighLevelClient makeConnection() {
        final BasicCredentialsProvider basicCredentialsProvider = new BasicCredentialsProvider();
        basicCredentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "you52100"));

        if (client == null) {
            client = new RestHighLevelClient(
                    RestClient.builder(new HttpHost("localhost", 9200, "http"))
                            .setHttpClientConfigCallback(httpClientBuilder -> {
                                httpClientBuilder.disableAuthCaching();
                                return httpClientBuilder.setDefaultCredentialsProvider(basicCredentialsProvider);
                            })
            );
        }
        return client;
    }

    private static synchronized void closeConnection() throws IOException {
        client.close();
        client = null;
    }

    @Override
    public List<Employee> findAllEmployeeDetailsFromES() {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("employees");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        List<Employee> list = new ArrayList<>();
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            if (searchResponse.getHits().getTotalHits().value > 0) {
                SearchHit[] searchHit = searchResponse.getHits().getHits();
                for (SearchHit hit : searchHit) {
                    Map<String, Object> map = hit.getSourceAsMap();
                    list.add(objectMapper.convertValue(map, Employee.class));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Employee> findAllUserDataByNameFromES(String name) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("employees");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("name.keyword", name));
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        List<Employee> list = new ArrayList<>();

        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            if (searchResponse.getHits().getTotalHits().value > 0) {
                SearchHit[] searchHits = searchResponse.getHits().getHits();
                for (SearchHit hit : searchHits) {
                    Map<String, Object> map = hit.getSourceAsMap();
                    list.add(objectMapper.convertValue(map, Employee.class));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Employee> findAllUserDataByNameAndOccupationFromES(String name, String occupation) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("employees");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("name.keyword", name));
        boolQueryBuilder.must(QueryBuilders.matchQuery("occupation", occupation));
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        List<Employee> list = new ArrayList<>();

        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            if (searchResponse.getHits().getTotalHits().value > 0) {
                SearchHit[] searchHits = searchResponse.getHits().getHits();
                for (SearchHit hit : searchHits) {
                    Map<String, Object> map = hit.getSourceAsMap();
                    list.add(objectMapper.convertValue(map, Employee.class));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
