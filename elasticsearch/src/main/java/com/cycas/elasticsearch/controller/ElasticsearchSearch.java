package com.cycas.elasticsearch.controller;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ElasticsearchSearch {

    private static String INDEX_NAME = "twitter";
    private static RestHighLevelClient client = null;

    private static synchronized RestHighLevelClient makeConnection() {
        final BasicCredentialsProvider basicCredentialsProvider = new BasicCredentialsProvider();
        basicCredentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "you52100"));

        if (client == null) {
            client = new RestHighLevelClient(
                    RestClient.builder(new HttpHost("localhost", 9200, "http"))
                            .setHttpClientConfigCallback(httpAsyncClientBuilder -> {
                                httpAsyncClientBuilder.disableAuthCaching();
                                return httpAsyncClientBuilder.setDefaultCredentialsProvider(basicCredentialsProvider);
                            })
            );
        }

        return client;
    }

    public static void main(String[] args) {
        client = makeConnection();

        // Search 1: Search for all documents
        System.out.println("Search 1");
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(INDEX_NAME);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);
        Map<String, Object> map = null;

        try {
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            if (searchResponse.getHits().getTotalHits().value > 0) {
                SearchHit[] searchHits = searchResponse.getHits().getHits();
                for (SearchHit hit : searchHits) {
                    map = hit.getSourceAsMap();
                    System.out.println("map:" + Arrays.toString(map.entrySet().toArray()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Search 2:
        System.out.println("Search 2");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.postFilter(QueryBuilders.rangeQuery("age").from(25).to(30));

        SearchRequest searchRequest2 = new SearchRequest();
        searchRequest2.indices(INDEX_NAME);
        searchRequest2.searchType(SearchType.DFS_QUERY_THEN_FETCH);
        searchRequest2.source(builder);

        try {
            SearchResponse searchResponse = client.search(searchRequest2, RequestOptions.DEFAULT);
            if (searchResponse.getHits().getTotalHits().value > 0) {
                SearchHit[] searchHits = searchResponse.getHits().getHits();
                for (SearchHit hit : searchHits) {
                    map = hit.getSourceAsMap();
                    System.out.println("map:" + Arrays.toString(map.entrySet().toArray()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Search 3:
        System.out.println("Search 3");
        SearchSourceBuilder builder3 = new SearchSourceBuilder();
        builder3.from(0);
        builder3.size(2);
        builder3.timeout(new TimeValue(60, TimeUnit.SECONDS));
        builder3.query(QueryBuilders.matchQuery("user", "朝阳"));

        SearchRequest searchRequest3 = new SearchRequest();
        searchRequest3.indices(INDEX_NAME);
        searchRequest3.searchType(SearchType.DFS_QUERY_THEN_FETCH);
        searchRequest3.source(builder3);

        try {
            SearchResponse searchResponse = client.search(searchRequest3, RequestOptions.DEFAULT);
            if (searchResponse.getHits().getTotalHits().value > 0) {
                SearchHit[] searchHits = searchResponse.getHits().getHits();
                for (SearchHit hit : searchHits) {
                    map = hit.getSourceAsMap();
                    System.out.println("map:" + Arrays.toString(map.entrySet().toArray()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Search 4:
        System.out.println("Search 4");
        SearchSourceBuilder builder4 = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        boolQueryBuilder.must(QueryBuilders.matchQuery("user", "朝阳"));
        boolQueryBuilder.must(QueryBuilders.matchQuery("address", "北京"));
        boolQueryBuilder.should(QueryBuilders.rangeQuery("age").from(25).to(30));
        builder4.query(boolQueryBuilder);
        builder4.from(0);
        builder4.size(2);
        builder4.timeout(new TimeValue(60, TimeUnit.SECONDS));

        SearchRequest searchRequest4 = new SearchRequest();
        searchRequest4.indices(INDEX_NAME);
        searchRequest4.searchType(SearchType.DFS_QUERY_THEN_FETCH);
        searchRequest4.source(builder4);
        try {
            SearchResponse searchResponse = client.search(searchRequest4, RequestOptions.DEFAULT);
            if (searchResponse.getHits().getTotalHits().value > 0) {
                SearchHit[] searchHits = searchResponse.getHits().getHits();
                for (SearchHit hit : searchHits) {
                    map = hit.getSourceAsMap();
                    System.out.println("map:" + Arrays.toString(map.entrySet().toArray()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
