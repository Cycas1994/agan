package com.cycas.elasticsearch.controller;

import com.alibaba.fastjson.JSON;
import com.cycas.elasticsearch.pojo.dmo.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContent;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建索引，写入数据
 */
public class ElasticsearchJavaWrite {

    private static RestHighLevelClient client = null;

    private static synchronized RestHighLevelClient makeConnection() {
        final BasicCredentialsProvider basicCredentialsProvider = new BasicCredentialsProvider();
        basicCredentialsProvider
                .setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "you52100"));

        if (client == null) {
            client = new RestHighLevelClient(
                    RestClient.builder(new HttpHost("localhost", 9200, "http"))
                            .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                                @Override
                                public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                                    httpClientBuilder.disableAuthCaching();
                                    return httpClientBuilder.setDefaultCredentialsProvider(basicCredentialsProvider);
                                }
                            })
            );
        }

        return client;
    }

    private static void createIndex() throws IOException {
        client = makeConnection();
        String mappings = "{\n" +
                "  \"properties\": {\n" +
                "    \"id\": {\n" +
                "      \"type\": \"keyword\"\n" +
                "    },\n" +
                "    \"name\": {\n" +
                "      \"type\": \"text\"\n" +
                "    }\n" +                "  }\n" +
                "}";
        System.out.println("mapping is as follows: ");
        System.out.println(mappings);

        CreateIndexRequest request = new CreateIndexRequest("employees");
        request.settings(Settings.builder()
                .put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 0)
        );
        request.mapping(mappings, XContentType.JSON);
        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println("response id:" + createIndexResponse.index());
    }

    private static void writeIndex() throws IOException {
        client = makeConnection();
        IndexRequest request = new IndexRequest("employees");
        request.id("1");
        String jsonString = "{" +
                "\"id\":\"1\"," +
                "\"name\":\"liuxg\"" +
                "}";
        request.source(jsonString, XContentType.JSON);

        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        System.out.println("response id:" + indexResponse.getId());
        System.out.println("response name:" + indexResponse.getResult().name());
    }


    private static void writeIndex2() throws IOException {
        client = makeConnection();
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("id", 2);
        jsonMap.put("name", "Nancy");
        IndexRequest indexRequest = new IndexRequest("employees").id("2").source(jsonMap);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println("response id:" + indexResponse.getId());
        System.out.println("response name:" + indexResponse.getResult().name());
    }

    private static void writeIndex3() throws IOException {
        client = makeConnection();
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        builder.field("id", "3");
        builder.field("name", "Jason");
        builder.endObject();
        IndexRequest indexRequest = new IndexRequest("employees").id("3").source(builder);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println("response id:" + indexResponse.getId());
        System.out.println("response name:" + indexResponse.getResult().name());
    }

    private static void writeIndex4() throws IOException {
        client = makeConnection();
        IndexRequest indexRequest = new IndexRequest("employees").id("4").source("id", "4", "name", "Mark");
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println("response id:" + indexResponse.getId());
        System.out.println("response name:" + indexResponse.getResult().name());
    }

    private static void writeIndex5() throws IOException {
        client = makeConnection();
        Employee employee = new Employee();
        employee.setName("Martin");
        employee.setSex("1");
        IndexRequest indexRequest = new IndexRequest("employees").id("5").source(new ObjectMapper().writeValueAsString(employee), XContentType.JSON);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println("response id:" + indexResponse.getId());
        System.out.println("response name:" + indexResponse.getResult().name());
    }

    private static void writeIndex6() throws IOException {
        client = makeConnection();
        Employee employee = new Employee();
        employee.setName("Jack");
        employee.setSex("1");
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.index("employees");
        indexRequest.id("6");
        indexRequest.source(JSON.toJSONString(employee), XContentType.JSON);
        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println("response id:" + indexResponse.getId());
        System.out.println("response name:" + indexResponse.getResult().name());
    }

    private static void writeIndex7() throws IOException {
        client = makeConnection();
        Employee employee = new Employee();
        employee.setName("Cycas");
        employee.setSex("1");
        IndexRequest indexRequest = new IndexRequest();
        indexRequest.index("employees");
        indexRequest.id("7");
        indexRequest.source(JSON.toJSONString(employee), XContentType.JSON);
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.add(indexRequest);
        BulkResponse bulkResponse = client.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    public static void main(String[] args) throws IOException {

        writeIndex7();
    }

}
