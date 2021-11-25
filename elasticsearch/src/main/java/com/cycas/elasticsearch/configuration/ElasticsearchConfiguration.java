package com.cycas.elasticsearch.configuration;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 比较全面的es配置类
 */
@Configuration
public class ElasticsearchConfiguration {

    private final Logger logger = LoggerFactory.getLogger(ElasticsearchConfiguration.class);

    private static final String HOST = "localhost";
    private static final int PORT_ONE = 9200;
    private static final int PORT_TWO = 9201;
    private static final String SCHEME = "http";
    private static final String user = "elastic";
    private static final String password = "you52100";

    public static int CONNECT_TIMEOUT_MILLIS = 10000;
    public static int SOCKET_TIMEOUT_MILLIS = 60000;
    public static int CONNECTION_REQUEST_TIMEOUT_MILLIS = 1000;
    public static int MAX_CONN_PER_ROUTE = 30;
    public static int MAX_CONN_TOTAL = 50;

    @Bean(name = "restHighLevelClient")
    public RestHighLevelClient restHighLevelClient() {

        return buildClient();
    }

    private RestHighLevelClient buildClient() {
        RestHighLevelClient restHighLevelClient = null;
        try {
            // 设置ip，端口，认证
            RestClientBuilder builder = RestClient.builder(
                    new HttpHost(HOST, PORT_ONE, SCHEME));
            // 配置连接时间延时
            this.setConnectTimeOutConfig(builder);
            // 使用异步httpclient时设置并发连接数
            this.setConnectMaxConfig(builder);
            restHighLevelClient = new RestHighLevelClient(builder);
            logger.info("---------------------------restHighLevelClient001-------------------------:{}", restHighLevelClient);
        } catch (Exception e) {
            logger.error("elastic buildClient error!", e);
        }
        return restHighLevelClient;
    }

    // 配置连接时间延时
    public void setConnectTimeOutConfig(RestClientBuilder builder) {
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(CONNECT_TIMEOUT_MILLIS);
            requestConfigBuilder.setSocketTimeout(SOCKET_TIMEOUT_MILLIS);
            requestConfigBuilder.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT_MILLIS);
            return requestConfigBuilder;
        });
    }

    // 使用异步httpclient时设置并发连接数
    public void setConnectMaxConfig(RestClientBuilder builder) {
        //认证
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(user, password));
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(MAX_CONN_TOTAL);
            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            httpClientBuilder.setMaxConnPerRoute(MAX_CONN_PER_ROUTE);
            return httpClientBuilder;
        });
    }
}
