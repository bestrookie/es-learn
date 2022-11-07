package com.bestrookie;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.nio.file.*;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

/**
 * @author bestrookie
 * @version 1.0
 * @date 2022/11/7 20:45
 */
public class ESClient {
    public static void main(String[] args) throws Exception {
        initESConnection();

    }

    private static void initESConnection() throws Exception {
        CredentialsProvider credentialsProvider =
                new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("elastic", "921477258Asd"));
//        第一步生成的证书所在的位置
        Path caCertificatePath = Paths.get("es8-learn/ca/http_ca.crt");
        CertificateFactory factory =
                CertificateFactory.getInstance("X.509");
        Certificate trustedCa;
        try (InputStream is = Files.newInputStream(caCertificatePath)) {
            trustedCa = factory.generateCertificate(is);
        }
        KeyStore trustStore = KeyStore.getInstance("pkcs12");
        trustStore.load(null, null);
        trustStore.setCertificateEntry("ca", trustedCa);
        SSLContextBuilder sslContextBuilder = SSLContexts.custom()
                .loadTrustMaterial(trustStore, null);
        SSLContext sslContext = sslContextBuilder.build();
        RestClientBuilder builder = RestClient.builder(
                        new HttpHost("120.48.101.253", 9200, "https"))
                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder
                        // 设置ssl
                        .setSSLContext(sslContext)
                        .setDefaultCredentialsProvider(credentialsProvider));
        RestClient restClient = builder.build();
        ElasticsearchTransport elasticsearchTransport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        ElasticsearchClient client = new ElasticsearchClient(elasticsearchTransport);
        ElasticsearchAsyncClient asyncClient = new ElasticsearchAsyncClient(elasticsearchTransport);
        new ElasticsearchClient(elasticsearchTransport);
        elasticsearchTransport.close();
        System.out.println("连接完成");

    }
}