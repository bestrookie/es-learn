package com.bestrookie;

import com.bestrookie.dto.Product;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author bestrookie
 * @date 2022/11/3 16:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataESIndexTest {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    public void createIndex(){
        System.out.println("创建索引");
    }
    @Test
    public void deleteIndex(){
        boolean flg = elasticsearchRestTemplate.indexOps(Product.class).delete();
        System.out.println("删除索引: "+flg);
    }
}
