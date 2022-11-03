package com.bestrookie;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;

import java.io.IOException;

/**
 * @author bestrookie
 * @version 1.0
 * @date 2022/10/27 20:58
 */
public class ES_Client {
    public static void main(String[] args) throws IOException {
        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("120.48.101.253",9200,"http"))
        );
        System.out.println("链接完成");

        CreateIndexRequest request = new CreateIndexRequest("shopping");
        CreateIndexResponse createIndexResponse = esClient.indices().create(request, RequestOptions.DEFAULT);
        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println("索引操作:"+acknowledged);
        //关闭es客户端
        esClient.close();
    }
}
