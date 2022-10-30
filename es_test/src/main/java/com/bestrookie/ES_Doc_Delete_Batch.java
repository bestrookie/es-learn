package com.bestrookie;

import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author bestrookie
 * @version 1.0
 * @date 2022/10/27 21:42
 */
public class ES_Doc_Delete_Batch {
    public static void main(String[] args) throws Exception{
        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("120.48.101.253",9200,"http"))
        );

        BulkRequest request = new BulkRequest();
        request.add(new DeleteRequest().index("bestrookie").id("1002"));
        request.add(new DeleteRequest().index("bestrookie").id("1003"));
        BulkResponse bulk = esClient.bulk(request, RequestOptions.DEFAULT);
        System.out.println(bulk.getTook());
        System.out.println(bulk.getItems());

        //关闭es客户端
        esClient.close();
    }
}
