package com.bestrookie;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;

/**
 * @author bestrookie
 * @version 1.0
 * @date 2022/10/27 21:42
 */
public class ES_Doc_Query_Count {
    public static void main(String[] args) throws Exception{
        //创建es客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("120.48.101.253",9200,"http"))
        );
        SearchRequest request = new SearchRequest();
        request.indices("bestrookie");
        SearchSourceBuilder query = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        query.from(2);
        query.size(4);
        String [] excludes= {"age"};
        String [] includes = {};
        query.sort("age", SortOrder.DESC);
        query.fetchSource(includes,excludes);
        request.source(query);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());
        System.out.println(response.getTook());

        for (SearchHit hit : hits) {
            System.out.println(hit.getSourceAsString());
        }
        //关闭es客户端
        esClient.close();
    }
}
