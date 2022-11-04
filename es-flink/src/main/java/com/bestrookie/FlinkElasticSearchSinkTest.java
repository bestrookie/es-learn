package com.bestrookie;

import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.elasticsearch.ElasticsearchSinkFunction;
import org.apache.flink.streaming.connectors.elasticsearch.RequestIndexer;
import org.apache.flink.streaming.connectors.elasticsearch7.ElasticsearchSink;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Requests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bestrookie
 * @date 2022/11/4 11:42
 */
public class FlinkElasticSearchSinkTest {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //Source：数据的输入
        DataStreamSource<String> source = env.socketTextStream("localhost",9999);

        //使用ES builder构建输出
        List<HttpHost> hosts = new ArrayList<>();
        hosts.add(new HttpHost("120.48.101.253",9200,"http"));
        ElasticsearchSink.Builder<String> build = new ElasticsearchSink.Builder<>(hosts, new ElasticsearchSinkFunction<String>() {
            @Override
            public void process(String s, RuntimeContext runtimeContext, RequestIndexer requestIndexer) {

                Map<String, String> jsonMap = new HashMap<>();
                jsonMap.put("data", s);
                IndexRequest indexRequest = Requests.indexRequest();
                indexRequest.index("flink-index");
                indexRequest.id("9001");
                indexRequest.source(jsonMap);

                requestIndexer.add(indexRequest);

            }
        });
        //Source数据的输出
        build.setBulkFlushMaxActions(1);
        source.addSink(build.build());

        env.execute("flink-es");
    }
}
