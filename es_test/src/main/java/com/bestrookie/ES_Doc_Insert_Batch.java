package com.bestrookie;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * @author bestrookie
 * @version 1.0
 * @date 2022/10/27 21:42
 */
public class ES_Doc_Insert_Batch {
    public static void main(String[] args) throws Exception{
        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("120.48.101.253",9200,"http"))
        );
        System.out.println("链接完成");


        BulkRequest request = new BulkRequest();
        ObjectMapper mapper = new ObjectMapper();
        IndexRequest insert = new IndexRequest();
        IndexRequest index = new IndexRequest();
        String str = null;
        insert.index("bestrookie").id("1002");
        insert.source(mapper.writeValueAsString(BestRookie.builder().name("李四").age(12).sex("女女").build()),XContentType.JSON);
        request.add(insert);
        index.index("bestrookie").id("1003");
        index.source(mapper.writeValueAsString(BestRookie.builder().name("王五").age(15).sex("女男").build()),XContentType.JSON);
        request.add(index);
        request.add(new IndexRequest().index("bestrookie").id("1004").source(XContentType.JSON,"name","张五","age",16,"sex","男"));
        request.add(new IndexRequest().index("bestrookie").id("1005").source(XContentType.JSON,"name","张溜","age",15,"sex","女"));
        request.add(new IndexRequest().index("bestrookie").id("1006").source(XContentType.JSON,"name","张器","age",14,"sex","女"));
        request.add(new IndexRequest().index("bestrookie").id("1007").source(XContentType.JSON,"name","张吧","age",13,"sex","男"));
        BulkResponse bulk = esClient.bulk(request, RequestOptions.DEFAULT);
        System.out.println(bulk.getTook());
        System.out.println(bulk.getItems());
        //关闭es客户端
        esClient.close();
    }
}
