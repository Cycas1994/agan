package com.cycas.elasticsearch.controller;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Aggregation {

    public static void main(String[] args) {


    }

    /**
     * GET classindex/_search
     * {
     *   "size": 0,
     *   "aggs": {
     *     "sum": {
     *       "sum": {
     *         "field": "fees"
     *       }
     *     },
     *     "avg": {
     *       "avg": {
     *         "field": "fees"
     *       }
     *     },
     *     "min": {
     *       "min": {
     *         "field": "fees"
     *       }
     *     },
     *     "max": {
     *       "max": {
     *         "field": "fees"
     *       }
     *     },
     *     "cardinality": {
     *       "cardinality": {
     *         "field": "fees"
     *       }
     *     },
     *     "count": {
     *       "value_count": {
     *         "field": "fees"
     *       }
     *     }
     *   }
     * }
     */
    private static void agg1() {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "you52100"));
        RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "http"));
        // 认证
        builder.setHttpClientConfigCallback(HttpAsyncClientBuilder -> {
            HttpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            return HttpAsyncClientBuilder;
        });
        RestHighLevelClient client = new RestHighLevelClient(builder);


        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("coachingclass");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        searchSourceBuilder.aggregation(AggregationBuilders.sum("sum").field("fees"));
        searchSourceBuilder.aggregation(AggregationBuilders.avg("avg").field("fees"));
        searchSourceBuilder.aggregation(AggregationBuilders.min("min").field("fees"));
        searchSourceBuilder.aggregation(AggregationBuilders.max("max").field("fees"));
        searchSourceBuilder.aggregation(AggregationBuilders.cardinality("cardinality").field("fees"));
        searchSourceBuilder.aggregation(AggregationBuilders.count("count").field("fees"));

        searchRequest.source(searchSourceBuilder);
        Map<String, Object> map = null;

        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            if (searchResponse.getHits().getTotalHits().value > 0) {
                SearchHit[] searchHit = searchResponse.getHits().getHits();
                for (SearchHit hit : searchHit) {
                    map = hit.getSourceAsMap();
                    System.out.println("Index data:" + Arrays.toString(map.entrySet().toArray()));
                }
            }

            Sum sum = searchResponse.getAggregations().get("sum");
            double result = sum.getValue();
            System.out.println("aggs Sum:" + result);
            Avg aggAvg = searchResponse.getAggregations().get("avg");
            double valueAvg = aggAvg.getValue();
            System.out.println("aggs Avg:" + valueAvg);
            Min aggMin = searchResponse.getAggregations().get("min");
            double valueMin = aggMin.getValue();
            System.out.println("aggs Min:" + valueMin);
            Max aggMax = searchResponse.getAggregations().get("max");
            double valueMax = aggMax.getValue();
            System.out.println("aggs Max:" + valueMax);
            Cardinality aggCardinality = searchResponse.getAggregations().get("cardinality");
            long valueCardinality = aggCardinality.getValue();
            System.out.println("aggs Cardinality:" + valueCardinality);
            ValueCount aggCount = searchResponse.getAggregations().get("count");
            long valueCount = aggCount.getValue();
            System.out.println("aggs Count:" + valueCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void agg2() {

        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "you52100"));
        RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "http"));
        // 认证
        builder.setHttpClientConfigCallback(HttpAsyncClientBuilder -> {
            HttpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            return HttpAsyncClientBuilder;
        });
        RestHighLevelClient client = new RestHighLevelClient(builder);

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("coachingclass");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.aggregation(AggregationBuilders.terms("DISTINCT_VALUES").field("instructor.keyword")).size(10);
        searchRequest.source(searchSourceBuilder);

        Map<String, Object> map = null;
        SearchResponse searchResponse = null;
        try {
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            if (searchResponse.getHits().getTotalHits().value > 0) {
                SearchHit[] searchHit = searchResponse.getHits().getHits();
                for (SearchHit hit : searchHit) {
                    map = hit.getSourceAsMap();
                    System.out.println("map:" + Arrays.toString(map.entrySet().toArray()));
                }
            }
            Aggregations aggregations = searchResponse.getAggregations();
            List<String> list = new ArrayList<>();
            Terms aggTerms = aggregations.get("DISTINCT_VALUES");
            List<? extends Terms.Bucket> buckets = aggTerms.getBuckets();
            for (Terms.Bucket bucket : buckets) {
                list.add(bucket.getKeyAsString());
            }
            System.out.println("DISTINCT list values:" + list.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
