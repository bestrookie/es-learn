package com.bestrookie;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
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
public class ES_Index_Delete {
    public static void main(String[] args) throws Exception {
        RestHighLevelClient esClient = new RestHighLevelClient (
                RestClient.builder(new HttpHost("120.48.101.253",9200,"http"))
        );
        System.out.println("链接完成");
        DeleteIndexRequest request = new DeleteIndexRequest("bestrookie");
        AcknowledgedResponse delete = esClient.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
        esClient.close();
    }
}
