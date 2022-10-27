package com.bestrookie;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;

/**
 * @author bestrookie
 * @version 1.0
 * @date 2022/10/27 21:16
 */
public class ES_Index_Search {
    public static void main(String[] args) throws Exception {
        RestHighLevelClient esClient = new RestHighLevelClient (
                RestClient.builder(new HttpHost("120.48.101.253",9200,"http"))
        );
        System.out.println("链接完成");
        GetIndexRequest getIndex =  new GetIndexRequest("bestrookie");
        GetIndexResponse getIndexResponse = esClient.indices().get(getIndex, RequestOptions.DEFAULT);
        System.out.println(getIndexResponse.getAliases());
        System.out.println(getIndexResponse.getMappings());
        System.out.println(getIndexResponse.getSettings());
        //关闭es客户端
        esClient.close();
    }
}
