package com.bestrookie;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

/**
 * @author bestrookie
 * @date 2022/11/8 20:06
 */
public class test {
    public static void main(String[] args) throws IOException {

        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "921477258Asd"));

        RestClient restClient = RestClient.builder(new HttpHost("120.48.101.253", 9200, "https"))
                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)).build();

        ElasticsearchTransport transport = new RestClientTransport( restClient, new JacksonJsonpMapper());

        ElasticsearchClient client = new ElasticsearchClient(transport);
        client.indices().exists(a->a.index("bestrookie"));
    }
}
