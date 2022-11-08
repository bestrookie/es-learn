package com.bestrookie.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author bestrookie
 * @date 2022/11/8 18:33
 */
@Service
public class EsServiceImpl implements EsService{
    @Resource
    private ElasticsearchClient elasticsearchClient;
    @Override
    public void addIndex(String name) throws IOException {
        elasticsearchClient.indices().create(a -> a.index(name));

    }

    @Override
    public boolean indexExists(String name) throws IOException {
        return elasticsearchClient.indices().exists(a -> a.index(name)).value();
    }
}
